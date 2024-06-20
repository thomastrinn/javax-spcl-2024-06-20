package careerservice.enrollment.service;

import careerservice.enrollment.view.EnrollmentView;
import careerservice.enrollment.model.EnrollCommand;
import careerservice.enrollment.model.Enrollment;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class EnrollmentService {

    private EnrollmentRepository enrollmentRepository;

    private EnrollmentMapper enrollmentMapper;

    public List<EnrollmentView> findAllByEmployee(long employeeId) {
        return enrollmentMapper.toViews(enrollmentRepository.findAllByEmployeeId(employeeId));
    }

    public EnrollmentView enrollToCourse(EnrollCommand command) {
        var mayBeEnrolled = enrollmentRepository.findByCourseIdAndEmployeeId(command.courseId(), command.employeeId());
        if (mayBeEnrolled.isPresent()) {
            return enrollmentMapper.toView(mayBeEnrolled.get());
        }
        else {
            var enrollment = Enrollment.enrollToCourse(command);
            enrollmentRepository.save(enrollment);
            return enrollmentMapper.toView(enrollment);
        }
    }

}
