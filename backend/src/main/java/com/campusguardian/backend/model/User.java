package com.campusguardian.backend.model;

public class User {

    private String email;
    private String password; // In production, hash this!
    private String name;
    private String studentClass;

    public User() {}

    public User(String email, String password, String name, String studentClass) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.studentClass = studentClass;
    }

    // Getters and Setters
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getStudentClass() {
        return studentClass;
    }
    public void setStudentClass(String studentClass) {
        this.studentClass = studentClass;
    }
}