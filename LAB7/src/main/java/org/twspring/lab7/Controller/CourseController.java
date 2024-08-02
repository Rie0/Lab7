package org.twspring.lab7.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.twspring.lab7.Api.ApiResponse;
import org.twspring.lab7.Model.Course;
import org.twspring.lab7.Service.CourseService;

@RestController
@RequestMapping("api/v1/course")
@RequiredArgsConstructor
public class CourseController {
    private final CourseService courseService;


    //================================GET=================================
    @GetMapping("/get/all") //Get all courses
    public ResponseEntity getAllCourses() {
        if (courseService.getCourses().isEmpty()) {
            return ResponseEntity.status(404).body(new ApiResponse("No courses found"));
        }
        return ResponseEntity.status(200).body(courseService.getCourses());
    }

    @GetMapping("/get/by_id/{id}") //Get one course by id
    public ResponseEntity getCourseById(@PathVariable int id) {
        if (courseService.getCourseById(id) == null) {
            return ResponseEntity.status(404).body(new ApiResponse("No course found"));
        }
        return ResponseEntity.status(200).body(courseService.getCourseById(id));
    }

    @GetMapping("/get/by_minimal_duration/{minimalDuration}") //get all courses that have the given duration or more
    public ResponseEntity getCourseByMinimalDuration(@PathVariable int minimalDuration) {
        if(courseService.getCoursesByMinimalDuration(minimalDuration) == null) {
            return ResponseEntity.status(404).body(new ApiResponse("No course found"));
        }
        return ResponseEntity.status(200).body(courseService.getCoursesByMinimalDuration(minimalDuration));
    }

    //================================Post=================================
    @PostMapping("/add")
    public ResponseEntity addCourse(@Valid @RequestBody Course course, Errors errors) {
        if (errors.hasErrors()) {
            String message =errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message));
        }
        courseService.addCourse(course);
        return ResponseEntity.status(201).body(new ApiResponse("Course added"));
    }

    //================================UPDATE=================================
    @PutMapping("/update/{id}")
    public ResponseEntity updateCourse(@PathVariable int id, @Valid @RequestBody Course course, Errors errors) {

        if (errors.hasErrors()) {
            String message =errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message));
        }
        boolean updated = courseService.updateCourse(id, course);
        if (updated) {
            courseService.updateCourse(id, course);
            return ResponseEntity.status(201).body(new ApiResponse("Course updated"));
        }
        return ResponseEntity.status(404).body(new ApiResponse("Course not found"));
    }

    //================================DELETE=================================
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteCourse(@PathVariable int id) {
        boolean isDeleted = courseService.deleteCourse(id);
        if (isDeleted) {
            return ResponseEntity.status(201).body(new ApiResponse("Course deleted"));
        }
        return ResponseEntity.status(404).body(new ApiResponse("Course not found"));
    }


}
