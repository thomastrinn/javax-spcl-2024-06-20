package careerservice.skill.command;

import careerservice.skill.model.AssignedSkill;
import jakarta.persistence.ElementCollection;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AssignSkillsToEmployeeCommand {

    private long employeeId;

    private List<LeveledSkill> skills;
}
