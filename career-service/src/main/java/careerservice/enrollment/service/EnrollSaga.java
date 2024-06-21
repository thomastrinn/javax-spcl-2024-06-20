package careerservice.enrollment.service;

import careerservice.enrollment.model.EnrollCommand;
import careerservice.enrollment.view.EnrollmentView;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class EnrollSaga {

    private ApplicationEventPublisher eventPublisher;
    private EnrollmentService enrollmentService;

    public EnrollmentView enroll(EnrollCommand enrollCommand) {
        EnrollmentView result = enrollmentService.enrollToCourse(enrollCommand);
        eventPublisher.publishEvent(enrollCommand);
        return result;
    }

    @EventListener
    void handleEnrollResponse(EnrollResponse response) {
        switch (response.enrollResult()) {
            case SUCCESS -> enrollmentService.complete(response.courseId(), response.employeeId());
            case COULD_NOT_ENROLL_COURSE_IS_FULL ->
                    enrollmentService.cancel(response.courseId(), response.employeeId());
            default -> throw new IllegalStateException("Unexpected value: " + response.enrollResult());
        }
    }
}
