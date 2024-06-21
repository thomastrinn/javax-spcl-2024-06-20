package careerservice.enrollment.gateway;

import careerservice.enrollment.model.EnrollCommand;
import lombok.AllArgsConstructor;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
class CourseGateway {

    private StreamBridge streamBridge;

    @EventListener
    void handleEnrollCommand(EnrollCommand event) {
        streamBridge.send("courses-request", event);
    }
}
