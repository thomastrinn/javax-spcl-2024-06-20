package careerservice.assignedskils.service;

import careerservice.assignedskils.model.AssignedSkills;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

interface AssignedSkillsRepository extends JpaRepository<AssignedSkills, Long> {

    @EntityGraph(attributePaths = "leveledSkills")
    Optional<AssignedSkills> findByEmployeeId(Long employeeId);
}
