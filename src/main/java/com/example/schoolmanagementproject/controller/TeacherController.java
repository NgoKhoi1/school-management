package com.example.schoolmanagementproject.controller;

import com.example.schoolmanagementproject.entity.Teacher;
import com.example.schoolmanagementproject.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teachers")
public class TeacherController {
    private TeacherService teacherService;

    @Autowired
    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @GetMapping("")
    public List<Teacher> getAllTeacher(){
        return teacherService.findAll();
    }

    @GetMapping("/{teacherid}")
    public Teacher getTeacherById(@PathVariable int teacherid){
        return teacherService.findById(teacherid);
    }

    @PostMapping("")
    public Teacher addNewTeacher(@RequestBody Teacher teacher){
        teacher.setId(0);
        return teacherService.save(teacher);
    }

    @PutMapping("")
    public Teacher updateTeacher(@RequestBody Teacher teacher){
        return teacherService.save(teacher);
    }

    @DeleteMapping("")
    public void deleteTeacher(@RequestParam(value = "id") int teacherid){
        teacherService.deleteById(teacherid);
    }
}
