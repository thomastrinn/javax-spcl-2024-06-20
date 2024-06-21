package courseservice.coursequery;

public record CourseHasBeenCreatedEvent(
        long id,
        String name,
        String description,
        String syllabus
) {
}
