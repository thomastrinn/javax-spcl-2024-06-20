package careerservice.skill.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class AssignedSkill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private long employeeId;

    private int level;

    @ManyToOne
    private Skill skill;

}
