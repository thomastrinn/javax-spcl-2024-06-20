package careerservice.skill.service;

import careerservice.skill.model.Skill;
import careerservice.skill.view.SkillView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SkillRepository extends JpaRepository<Skill, Long> {

}
