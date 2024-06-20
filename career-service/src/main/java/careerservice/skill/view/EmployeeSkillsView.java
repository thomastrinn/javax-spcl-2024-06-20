package careerservice.skill.view;

import careerservice.skill.command.LeveledSkill;
import lombok.Data;

import java.util.List;

@Data
public class EmployeeSkillsView {

    private long employeeId;

    private List<LeveledSkill> skills;
}
