package careerservice.assignedskils.controller;

import careerservice.assignedskils.command.AssignSkillsToEmployeeCommand;
import careerservice.assignedskils.service.AssignedSkillService;
import careerservice.assignedskils.view.EmployeeSkillsView;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/assigned-skills")
@AllArgsConstructor
class AssignedSkillController {

    private final AssignedSkillService assignedSkillService;

    @PostMapping
    EmployeeSkillsView assignSkillsToEmployee(@RequestBody AssignSkillsToEmployeeCommand command) {
        return assignedSkillService.assignSkillsToEmployee(command);
    }

    @GetMapping
    EmployeeSkillsView getAssignedSkills(@RequestParam int employeeId) {
        return assignedSkillService.getAssignedSkills(employeeId);
    }
}
