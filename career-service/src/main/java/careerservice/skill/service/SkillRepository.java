package careerservice.skill.service;

import careerservice.skill.model.Skill;
import org.springframework.data.jpa.repository.JpaRepository;

interface SkillRepository extends JpaRepository<Skill, Long> {

}
