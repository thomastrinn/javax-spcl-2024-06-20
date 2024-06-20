package careerservice.skill.controller;

import careerservice.skill.SkillServicePort;
import careerservice.skill.command.CreateSkillCommand;
import careerservice.skill.view.SkillView;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class SkillController {

    private SkillServicePort skillServicePort;

    @GetMapping("/api/skills")
    public List<SkillView> listSkills() {
        return skillServicePort.listSkills();
    }

    @PostMapping("/api/skills")
    public SkillView create(@RequestBody CreateSkillCommand command) {
        return skillServicePort.create(command);
    }

}
