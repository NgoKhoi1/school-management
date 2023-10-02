package com.example.schoolmanagementproject.dao;

import com.example.schoolmanagementproject.entity.Class;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassDAO extends JpaRepository<Class,Integer> {
}
