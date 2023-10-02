package com.example.schoolmanagementproject.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name ="student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "dob")
    private Date dob;

    @Column(name = "sex")

    private boolean sex;

    @ManyToOne
    @JoinColumn(name = "classid", referencedColumnName = "id")
    private Class aClass;

    public Student() {
    }

    public Student(String name, Date dob, boolean sex, Class aClass) {
        this.name = name;
        this.dob = dob;
        this.sex = sex;
        this.aClass = aClass;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public boolean isSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    public Class getaClass() {
        return aClass;
    }

    public void setaClass(Class aClass) {
        this.aClass = aClass;
    }
}
