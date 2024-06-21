package courseservice.course.gateway;

import courseservice.course.CourseHasBeenCreatedEvent;
import lombok.AllArgsConstructor;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
class ApplicationGateway {

    private StreamBridge streamBridge;

    @EventListener
    void handleCourseHasBeenCreatedEvent(CourseHasBeenCreatedEvent event) {
        streamBridge.send("courses-events", event);
    }
}
