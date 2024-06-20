package careerservice.assignedskils.command;

import careerservice.assignedskils.model.LeveledSkill;
import jakarta.validation.constraints.NotEmpty;

import java.util.Set;

public record AssignSkillsToEmployeeCommand(long employeeId, @NotEmpty Set<LeveledSkill> skills) {
}
