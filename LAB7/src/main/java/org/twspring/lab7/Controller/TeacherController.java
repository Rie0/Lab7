package org.twspring.lab7.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.twspring.lab7.Api.ApiResponse;
import org.twspring.lab7.Model.Teacher;
import org.twspring.lab7.Service.TeacherService;

@RestController
@RequestMapping("api/v1/teacher")
@RequiredArgsConstructor
public class TeacherController {

    private final TeacherService teacherService;

    //================================GET=================================
    @GetMapping("/get/all") //get all teachers
    public ResponseEntity getTeachers() {
        if(teacherService.getTeachers().isEmpty()){
            return ResponseEntity.status(404).body(new ApiResponse("No teachers found"));
        }
        return ResponseEntity.status(200).body(teacherService.getTeachers());
    }

    @GetMapping("/get/by_title/{title}") //get teachers holding the given title
    public ResponseEntity getTeachersByTitle(@PathVariable String title) {
        if(teacherService.getTeachersByTitle(title).isEmpty()){
            return ResponseEntity.status(404).body(new ApiResponse("No teachers with title "+title+" found"));
        }
        return ResponseEntity.status(200).body(teacherService.getTeachersByTitle(title));
    }

    @GetMapping("/get/by_name/{name}") //get a teacher by name
    public ResponseEntity getTeachersByName(@PathVariable String name) {
        if (teacherService.getTeacherByName(name)==null){
            return ResponseEntity.status(404).body(new ApiResponse("No teacher with name "+name+" found"));
        }
        return ResponseEntity.status(200).body(teacherService.getTeacherByName(name));
    }
    //================================POST=================================
    @PostMapping("/add")
    public ResponseEntity addTeacher(@Valid @RequestBody Teacher teacher, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        teacherService.addTeacher(teacher);
        return ResponseEntity.status(201).body(new ApiResponse("Teacher added successfully"));
    }
    //================================UPDATE=================================
    @PutMapping("/update/{id}")
    public ResponseEntity updateTeacher(@PathVariable int id, @Valid @RequestBody Teacher teacher, Errors errors) {
       if (errors.hasErrors()) {
           String message = errors.getFieldError().getDefaultMessage();
           return ResponseEntity.status(400).body(message);
       }

        boolean isUpdated = teacherService.updateTeacher(id, teacher);
        if(isUpdated){
            return ResponseEntity.status(201).body(new ApiResponse("Teacher updated successfully"));
        }
        return ResponseEntity.status(404).body(new ApiResponse("No teacher with id "+id+" found"));
    }
    //================================DELETE=================================

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteTeacher(@PathVariable int id) {
        boolean isDeleted = teacherService.deleteTeacher(id);
        if(isDeleted){
            return ResponseEntity.status(201).body(new ApiResponse("Teacher deleted successfully"));
        }
        return ResponseEntity.status(404).body(new ApiResponse("No teacher with id "+id+" found"));
    }

}
