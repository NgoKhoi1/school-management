package com.example.schoolmanagementproject.controller_test;

import com.example.schoolmanagementproject.controller.StudentController;
import com.example.schoolmanagementproject.entity.Class;
import com.example.schoolmanagementproject.entity.Student;
import com.example.schoolmanagementproject.exception.NotFoundException;
import com.example.schoolmanagementproject.service.StudentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.CoreMatchers;
import org.json.JSONException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultHandler;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@ExtendWith(MockitoExtension.class)
//@WebMvcTest(controllers = StudentController.class)
@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class StudentControllerTest {
    @MockBean
    private StudentService studentService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private Student student;

    private Class aClassl;

    private List<Student> studentList;

    @BeforeEach
    public void init(){
        aClassl = new Class("SE1704","abc");
        aClassl.setId(10);
        student = new Student("Khoi", new Date(2003,06,03),true,aClassl);
        studentList = new ArrayList<>();
        studentList.add(student);
    }

    @Test
    public void testGetStudentById() throws Exception{
        when(studentService.findById(anyInt())).thenReturn(student);
        ResultActions response = mockMvc.perform(get("/students/1"));
        response.andExpect(MockMvcResultMatchers.status().is2xxSuccessful()).
                andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(student))).
                andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void testGetAllStudent() throws Exception{
        when(studentService.findAll()).thenReturn(studentList);
        ResultActions resultActions = mockMvc.perform(get("/students"));
        resultActions.andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(studentList)))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void testAddStudent() throws Exception{
        when(studentService.save(student)).thenReturn(student);
        String json = objectMapper.writeValueAsString(student);
        ResultActions resultActions = mockMvc.perform(post("/students").
                contentType(MediaType.APPLICATION_JSON).
                characterEncoding("UTF-8").
                content(json));
        resultActions.andExpect(MockMvcResultMatchers.status().isCreated())
//                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(student)))
                .andDo(MockMvcResultHandlers.print());
    }


    @Test
    public void testUpdateStudent() throws Exception{
        student.setId(8);
        when(studentService.save(student)).thenReturn(student);
        String json = objectMapper.writeValueAsString(student);
        ResultActions resultActions = mockMvc.perform(put("/students").
                contentType(MediaType.APPLICATION_JSON).
                characterEncoding("UTF-8").
                content(json));
        resultActions.andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(student)))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void testDeleteStudent() throws Exception{
//        doNothing().when(studentService.deleteById(anyInt()));
        String json = objectMapper.writeValueAsString(student);
        ResultActions resultActions = mockMvc.perform(delete("/students").
                contentType(MediaType.APPLICATION_JSON).
                characterEncoding("UTF-8").param("id","1"));
        resultActions.andExpect(MockMvcResultMatchers.status().isNoContent())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void testStudentNotFound() throws Exception{
        when(studentService.findById(0)).thenThrow(new NotFoundException("studen not found id-"+0));
        ResultActions resultActions = mockMvc.perform(get("/students/0"));
        resultActions.andExpect(MockMvcResultMatchers.status().isNotFound()).
                andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void testBadInput() throws Exception{
        ResultActions resultActions = mockMvc.perform(get("/students/abc"));
        resultActions.andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andDo(MockMvcResultHandlers.print());
    }
}
