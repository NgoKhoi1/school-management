package com.example.schoolmanagementproject.service;

import com.example.schoolmanagementproject.entity.Class;

import java.util.List;
import java.util.Optional;

public interface ClassService {
    List<Class> findAll();
    Class findById(Integer id);
    Class save(Class cls);
    void deleteById(Integer id);
}
