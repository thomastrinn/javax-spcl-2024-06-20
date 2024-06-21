package careerservice.enrollment.service;

public record EnrollResponse(
        long employeeId,
        long courseId,
        EnrollResult enrollResult) {

    public enum EnrollResult {SUCCESS, COULD_NOT_ENROLL_COURSE_IS_FULL}
}
