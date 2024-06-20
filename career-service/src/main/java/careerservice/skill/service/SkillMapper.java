package careerservice.skill.service;

import careerservice.skill.model.Skill;
import careerservice.skill.view.SkillView;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SkillMapper {
    SkillView toView(Skill skill);

    List<SkillView> toViews(List<Skill> skills);
}
