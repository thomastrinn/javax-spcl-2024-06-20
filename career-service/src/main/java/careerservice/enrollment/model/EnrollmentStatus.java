package careerservice.enrollment.model;

public enum EnrollmentStatus {

    STARTED {
        @Override
        public EnrollmentStatus complete() {
            return COMPLETED;
        }

        @Override
        public EnrollmentStatus cancel() {
            return CANCELLED;
        }
    },
    COMPLETED,
    CANCELLED,
    ;

    public EnrollmentStatus complete() {
        throw new IllegalArgumentException("Cannot complete Enrollment Status");
    }

    public EnrollmentStatus cancel() {
        throw new IllegalArgumentException("Cannot cancel Enrollment Status");
    }
}
