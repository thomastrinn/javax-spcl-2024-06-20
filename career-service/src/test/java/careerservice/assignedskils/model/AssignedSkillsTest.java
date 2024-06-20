package careerservice.assignedskils.model;

import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class AssignedSkillsTest {

    @Test
    void test_first_assign_skills() {
        AssignedSkills assignedSkills = AssignedSkills.firstAssignSkills(1L,
                Set.of(
                        new LeveledSkill(1L, 4),
                        new LeveledSkill(2L, 3)
                ));

        assignedSkills.assignSkills(Set.of(new LeveledSkill(1L, 5)));

        assertThat(assignedSkills.getLeveledSkills())
                .contains(new LeveledSkill(1L, 5), new LeveledSkill(2L, 3));
    }
}