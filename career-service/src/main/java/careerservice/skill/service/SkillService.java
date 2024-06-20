package careerservice.skill.service;

import careerservice.skill.command.AssignSkillsToEmployeeCommand;
import careerservice.skill.command.CreateSkillCommand;
import careerservice.skill.command.LeveledSkill;
import careerservice.skill.model.AssignedSkill;
import careerservice.skill.model.Skill;
import careerservice.skill.view.EmployeeSkillsView;
import careerservice.skill.view.SkillView;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.Function;
import java.util.logging.Level;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SkillService {

    private SkillRepository skillRepository;

    private AssignedSkillRepository assignedSkillRepository;

    private SkillMapper skillMapper;

    public SkillView create(CreateSkillCommand command) {
        var skill = new Skill(command.getName());
        skillRepository.save(skill);
        return skillMapper.toView(skill);
    }

    public List<SkillView> listSkills() {
        return skillMapper.toViews(skillRepository.findAll());
    }

    @Transactional
    public EmployeeSkillsView assignSkillsToEmployee(AssignSkillsToEmployeeCommand command) {
        var assignedSkills = assignedSkillRepository.findByEmployeeId(command.getEmployeeId());
        var skillsById = assignedSkills.stream()
                .collect(Collectors.toMap(assignedSkill -> assignedSkill.getSkill().getId(), Function.identity()));

        for (var leveledSkill: command.getSkills()) {
            var existingSkill = skillsById.get(leveledSkill.getSkillId());
            if (existingSkill == null) {
                var assignedSkill = new AssignedSkill();
                assignedSkill.setEmployeeId(command.getEmployeeId());
                var skill = skillRepository.findById(leveledSkill.getSkillId())
                        .orElseThrow(() -> new NoSuchElementException("Skill not found: " + leveledSkill.getSkillId()));
                assignedSkill.setSkill(skill);
                assignedSkill.setLevel(leveledSkill.getLevel());
                assignedSkillRepository.save(assignedSkill);
            }
            else {
                if (existingSkill.getLevel() < leveledSkill.getLevel()) {
                    existingSkill.setLevel(leveledSkill.getLevel());
                }
            }
        }

        return getAssignedSkills(command.getEmployeeId());
    }

    public EmployeeSkillsView getAssignedSkills(long employeeId) {
        var assignedSkills = assignedSkillRepository.findByEmployeeId(employeeId);

        var result = new EmployeeSkillsView();
        result.setEmployeeId(employeeId);
        result.setSkills(assignedSkills.stream().map(assignedSkill -> new LeveledSkill(assignedSkill.getSkill().getId(), assignedSkill.getLevel())).toList());
        return result;
    }


}
