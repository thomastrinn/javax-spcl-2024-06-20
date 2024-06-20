package careerservice.skill.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LeveledSkill {

    private long skillId;

    private int level;

}
