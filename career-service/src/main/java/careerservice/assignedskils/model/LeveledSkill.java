package careerservice.assignedskils.model;

import jakarta.persistence.Embeddable;
import org.springframework.util.Assert;

@Embeddable
public record LeveledSkill(
        long skillId,
        int level // akár lehetne value-object vagy akár enum is
) {

    public LeveledSkill levelUp(LeveledSkill other) {
        Assert.isTrue(skillId == other.skillId, () -> "Different skills");
        if (level < other.level) {
            return new LeveledSkill(other.skillId, other.level);
        }
        return this;
    }

    @Override
    public int hashCode() {
        return Long.hashCode(skillId);
    }
}
