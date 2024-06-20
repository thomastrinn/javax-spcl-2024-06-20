package careerservice.assignedskils.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.Assert;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Entity
@Getter
// bár IntelliJ panaszkodik, hogy kéne public vagy protected no-arg constructor, de hibernate tudja kezelni
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AssignedSkills {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private long employeeId;

    @ElementCollection
    private Set<LeveledSkill> leveledSkills;

    private AssignedSkills(long employeeId, Set<LeveledSkill> leveledSkills) {
        Assert.notNull(leveledSkills, () -> "LeveledSkills must not be null");
        Assert.notEmpty(leveledSkills, () -> "LeveledSkills must not be empty");
        this.employeeId = employeeId;
        this.leveledSkills = new HashSet<>(leveledSkills);
    }

    public static AssignedSkills firstAssignSkills(long employeeId, Set<LeveledSkill> leveledSkills) {
        return new AssignedSkills(employeeId, leveledSkills);
    }

    public void assignSkills(Set<LeveledSkill> skillsToAdd) {
        var skillsById = leveledSkills.stream().collect(Collectors.toMap(LeveledSkill::skillId, Function.identity()));

        for (LeveledSkill skillToAdd : skillsToAdd) {
            var existingSkill = skillsById.get(skillToAdd.skillId());
            if (existingSkill == null) {
                leveledSkills.add(skillToAdd);
            } else {
                leveledSkills.remove(existingSkill);
                leveledSkills.add(existingSkill.levelUp(skillToAdd));
            }
        }
    }
}
