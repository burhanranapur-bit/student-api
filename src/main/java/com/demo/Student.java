package com.demo;

public class Student {

    private Long id;
    private String name;
    private String email;

    // Constructor
    public Student(Long id, String name, String email) {
        this.id    = id;
        this.name  = name;
        this.email = email;
    }

    // Getters (Spring needs these to convert to JSON)
    public Long   getId()    { return id; }
    public String getName()  { return name; }
    public String getEmail() { return email; }

}
