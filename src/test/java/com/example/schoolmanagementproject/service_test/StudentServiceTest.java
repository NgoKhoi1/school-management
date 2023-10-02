package com.example.schoolmanagementproject.service_test;

import com.example.schoolmanagementproject.dao.StudentDAO;
import com.example.schoolmanagementproject.entity.Class;
import com.example.schoolmanagementproject.entity.Student;
import com.example.schoolmanagementproject.service.StudentService;
import com.example.schoolmanagementproject.service.StudentServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@SpringBootTest
public class StudentServiceTest {
    @Mock
    private StudentDAO studentDAO;

    @InjectMocks
    private StudentServiceImpl studentService;

    private Student student;

    private Class aClass;

    private List<Student> studentList;
    @Mock
    private Optional<Student> optionalStudent;


    @BeforeEach
    public void init (){
        aClass = new Class("SE1704","abc");
        aClass.setId(10);
        student = new Student("Khoi", new Date(2003,06,03),true,aClass);
        studentList = new ArrayList<>();
        studentList.add(student);
        studentList.add(new Student("A", new Date(2003,06,03),true,aClass));
        studentList.add(new Student("B", new Date(2003,06,03),true,aClass));
        studentList.add(new Student("C", new Date(2003,06,03),true,aClass));
    }

    @Test
    public void testGetAllStudent(){
        when(studentDAO.findAll()).thenReturn(studentList);
        Assertions.assertEquals(studentService.findAll(),studentList);
    }

    @Test
    public void testGetStudentById(){
        when(studentDAO.findById(1)).thenReturn(optionalStudent);
        when(studentDAO.findAll()).thenReturn(studentList);
        when(optionalStudent.get()).thenReturn(student);
        Assertions.assertEquals(studentService.findById(1),student);
    }

    @Test
    public void testAddNewStudent(){
        when(studentDAO.save(student)).thenAnswer(invocationOnMock -> invocationOnMock.getArgument(0));
        Assertions.assertEquals(studentService.save(student),student);
    }

    @Test
    public void testSaveStudent(){
        when(studentDAO.save(student)).thenAnswer(invocationOnMock -> invocationOnMock.getArgument(0));
        Assertions.assertEquals(studentService.save(student),student);
    }


}
