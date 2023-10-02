package com.example.schoolmanagementproject.dao;

import com.example.schoolmanagementproject.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherDAO extends JpaRepository<Teacher,Integer> {
}
