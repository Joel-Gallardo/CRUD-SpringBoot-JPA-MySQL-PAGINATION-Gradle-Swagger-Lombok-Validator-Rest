package com.joel.crud.controller;

import com.joel.crud.model.Student;
import com.joel.crud.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping(value = "/{student}")
    public ResponseEntity<Student> saveStudent(@RequestBody Student student){
    return ResponseEntity.ok(studentService.saveStudent(student));
    }

    @GetMapping
    public ResponseEntity<Page<Student>> getAllStudent (
           @RequestParam(required = false, defaultValue = "0") Integer page,
           @RequestParam(required = false, defaultValue = "10")Integer size,
           @RequestParam(required = false, defaultValue = "false")Boolean enablePagination) {
        return ResponseEntity.ok(studentService.getAllStudent(page, size, enablePagination));
    }

    @DeleteMapping(value = "/{id}")
    public void deleteStudent(@PathVariable ("id") Long id){
        studentService.deleteStudent(id);
         ResponseEntity.ok(studentService.existById(id));
    }

    @PutMapping
    public ResponseEntity<Student> editStudent(@RequestBody Student student) {
        return null;
    }
}
