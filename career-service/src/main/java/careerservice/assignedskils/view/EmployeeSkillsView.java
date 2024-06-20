package careerservice.assignedskils.view;

import careerservice.assignedskils.model.LeveledSkill;
import jakarta.validation.constraints.NotEmpty;

import java.util.Set;

public record EmployeeSkillsView(long employeeId, @NotEmpty Set<LeveledSkill> skills) {
}
