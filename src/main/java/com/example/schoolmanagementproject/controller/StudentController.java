package com.example.schoolmanagementproject.controller;

import com.example.schoolmanagementproject.entity.Student;
import com.example.schoolmanagementproject.exception.BadInputException;
import com.example.schoolmanagementproject.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {
    private StudentService studentService;

    @Autowired
    private StudentController(StudentService studentService){
        this.studentService = studentService;
    }

    @GetMapping()
    public List<Student> getAllStudent(){
        return studentService.findAll();
    }

    @GetMapping("/{studentid}")
    public Student getStudentById(@PathVariable String studentid){
        int id = 0;
       if(studentid.matches("\\d")){
           id = Integer.parseInt(studentid);
       }else {
           throw new BadInputException("Failed to convert "+studentid+" to int");
       }
        return studentService.findById(id);
    }

    @PostMapping()
    public ResponseEntity<Student> addNewStudent(@RequestBody Student student){
        student.setId(0);
        Student student1 = studentService.save(student);
        return new ResponseEntity<>(student1, HttpStatus.CREATED);
    }

    @PutMapping()
    public Student updateStudent(@RequestBody Student student){
        return studentService.save(student);
    }

    @DeleteMapping()
    public ResponseEntity<Student> deleteStudent(@RequestParam(value = "id") int id){
        studentService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
