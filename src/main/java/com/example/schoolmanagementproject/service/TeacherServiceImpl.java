package com.example.schoolmanagementproject.service;


import com.example.schoolmanagementproject.dao.TeacherDAO;
import com.example.schoolmanagementproject.entity.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class TeacherServiceImpl implements TeacherService{
    private TeacherDAO teacherDAO;

    @Autowired
    public TeacherServiceImpl(TeacherDAO teacherDAO) {
        this.teacherDAO = teacherDAO;
    }

    @Override
    public List<Teacher> findAll() {
        return teacherDAO.findAll();
    }

    @Override
    public Teacher findById(int id) {
        return teacherDAO.findById(id).get();
    }

    @Override
    @Transactional
    public Teacher save(Teacher teacher) {
        return teacherDAO.save(teacher);
    }

    @Override
    @Transactional
    public void deleteById(int id) {
        teacherDAO.deleteById(id);
    }
}
