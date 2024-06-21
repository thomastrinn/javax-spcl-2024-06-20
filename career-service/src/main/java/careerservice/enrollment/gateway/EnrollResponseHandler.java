package careerservice.enrollment.gateway;

import careerservice.enrollment.service.EnrollResponse;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration
public class EnrollResponseHandler {

    @Bean
    Consumer<EnrollResponse> handleEnrollResponse(ApplicationEventPublisher publisher) {
        return publisher::publishEvent;
    }
}
