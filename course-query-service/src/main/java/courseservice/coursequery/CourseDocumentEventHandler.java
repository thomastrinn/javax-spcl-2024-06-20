package courseservice.coursequery;

import lombok.AllArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
class CourseDocumentEventHandler {

    private final CourseDocumentRepository courseDocumentRepository;
    private final CourseDocumentMapper courseDocumentMapper;

    @EventListener
    void handleCourseHasBenCreatedEvent(CourseHasBeenCreatedEvent event) {
        var mapped = courseDocumentMapper.toCourseDocument(event);
        courseDocumentRepository.save(mapped);
    }
}
