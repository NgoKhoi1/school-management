package com.example.schoolmanagementproject.service;

import com.example.schoolmanagementproject.dao.ClassDAO;
import com.example.schoolmanagementproject.entity.Class;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class ClassServiceImpl implements ClassService{
    private ClassDAO classDAO;

    @Autowired
    public ClassServiceImpl(ClassDAO classDAO){
        this.classDAO = classDAO;
    }

    @Override
    public List<Class> findAll() {
        return classDAO.findAll();
    }

    @Override
    public Class findById(Integer id) {
        return classDAO.findById(id).get();
    }

    @Override
    public Class save(Class cls) {
        return classDAO.save(cls);
    }

    @Override
    public void deleteById(Integer id) {
        classDAO.deleteById(id);
    }
}
