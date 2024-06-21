package courseservice.coursequery;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CourseDocumentMapper {

    CourseDocument toCourseDocument(CourseHasBeenCreatedEvent event);
}
