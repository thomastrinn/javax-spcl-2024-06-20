package courseservice.coursequery;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration
@AllArgsConstructor
@Slf4j
class CourseDocumentEventHandler {

    private final CourseDocumentRepository courseDocumentRepository;
    private final CourseDocumentMapper courseDocumentMapper;

    @Bean
    Consumer<CourseHasBeenCreatedEvent> eventHandler() {
        return this::handleCourseHasBenCreatedEvent;
    }

    void handleCourseHasBenCreatedEvent(CourseHasBeenCreatedEvent event) {
        log.info("handleCourseHasBenCreatedEvent: {}", event);
        var mapped = courseDocumentMapper.toCourseDocument(event);
        courseDocumentRepository.save(mapped);
    }
}
