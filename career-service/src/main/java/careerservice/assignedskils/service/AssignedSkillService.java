package careerservice.assignedskils.service;

import careerservice.NotFoundException;
import careerservice.assignedskils.command.AssignSkillsToEmployeeCommand;
import careerservice.assignedskils.model.AssignedSkills;
import careerservice.assignedskils.view.EmployeeSkillsView;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class AssignedSkillService {
    private final AssignedSkillsRepository assignedSkillsRepository;

    @Transactional
    public EmployeeSkillsView assignSkillsToEmployee(AssignSkillsToEmployeeCommand command) {
        Optional<AssignedSkills> mayBeAssignedSkills = assignedSkillsRepository.findByEmployeeId(command.employeeId());
        if (mayBeAssignedSkills.isEmpty()) {
            AssignedSkills affectedSkills = AssignedSkills.firstAssignSkills(command.employeeId(), command.skills());
            assignedSkillsRepository.save(affectedSkills);
        } else {
            mayBeAssignedSkills.get().assignSkills(command.skills());
        }
        return getAssignedSkills(command.employeeId());
    }

    @Transactional(readOnly = true)
    public EmployeeSkillsView getAssignedSkills(long employeeId) {
        Optional<AssignedSkills> assignedSkills = assignedSkillsRepository.findByEmployeeId(employeeId);
        if (assignedSkills.isEmpty()) {
            throw new NotFoundException("Employee with id " + employeeId + " does not exist");
        }

        return new EmployeeSkillsView(
                employeeId,
                Collections.unmodifiableSet(assignedSkills.get().getLeveledSkills()));
    }
}
