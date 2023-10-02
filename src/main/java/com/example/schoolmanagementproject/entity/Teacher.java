package com.example.schoolmanagementproject.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "teacher")
public class Teacher {
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
    @JoinColumn(name ="professionalid", referencedColumnName = "id")
    private Professional professional;

    public Teacher() {
    }

    public Teacher(String name, Date dob, boolean sex, Professional professional) {
        this.name = name;
        this.dob = dob;
        this.sex = sex;
        this.professional = professional;
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

    public Professional getProfessional() {
        return professional;
    }

    public void setProfessional(Professional professional) {
        this.professional = professional;
    }
}
