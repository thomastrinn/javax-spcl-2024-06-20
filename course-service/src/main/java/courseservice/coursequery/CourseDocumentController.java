package courseservice.coursequery;

import courseservice.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/course-documents")
@AllArgsConstructor
class CourseDocumentController {

    private CourseDocumentRepository documentRepository;

    @GetMapping("/{id}")
    CourseDocument getCourseById(@PathVariable long id) {
        return documentRepository.findById(id).orElseThrow(() -> new NotFoundException("Course not found"));
    }

    @GetMapping
    List<CourseDocument> findByWord(@RequestParam String word) {
        return documentRepository.findByWord(word);
    }
}
