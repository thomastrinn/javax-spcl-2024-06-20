package careerservice.skill.service;

import careerservice.skill.model.AssignedSkill;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AssignedSkillRepository extends JpaRepository<AssignedSkill, Long> {
    List<AssignedSkill> findByEmployeeId(long employeeId);
}
