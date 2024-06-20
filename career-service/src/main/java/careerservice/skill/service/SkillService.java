package careerservice.skill.service;

import careerservice.skill.SkillHasBeanCreatedEvent;
import careerservice.skill.SkillServicePort;
import careerservice.skill.command.CreateSkillCommand;
import careerservice.skill.model.Skill;
import careerservice.skill.view.SkillView;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
class SkillService implements SkillServicePort {

    private SkillRepository skillRepository;
    private ApplicationEventPublisher eventPublisher;

    private SkillMapper skillMapper;

    @Override
    public SkillView create(CreateSkillCommand command) {
        var skill = new Skill(command.getName());
        skillRepository.save(skill);
        eventPublisher.publishEvent(new SkillHasBeanCreatedEvent(skill.getId(), skill.getName()));
        return skillMapper.toView(skill);
    }

    @Override
    public List<SkillView> listSkills() {
        return skillMapper.toViews(skillRepository.findAll());
    }

}
