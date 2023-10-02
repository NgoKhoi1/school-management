package com.example.schoolmanagementproject.service;

import com.example.schoolmanagementproject.dao.StudentDAO;
import com.example.schoolmanagementproject.entity.Student;
import com.example.schoolmanagementproject.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService{
    private StudentDAO studentDAO;

    @Autowired
    public StudentServiceImpl(StudentDAO studentDAO) {
        this.studentDAO = studentDAO;
    }

    @Override
    public List<Student> findAll() {
        return studentDAO.findAll();
    }

    @Override
    public Student findById(int id) {

        if (id <= 0 || id > findAll().size()){
            throw new NotFoundException("Student not found id - "+id);
        }
        return studentDAO.findById(id).get();
    }

    @Override
    @Transactional
    public Student save(Student student) {
        return studentDAO.save(student);
    }

    @Override
    @Transactional
    public void deleteById(int id) {
        studentDAO.deleteById(id);
    }
}
