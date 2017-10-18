package com.csye6225.demo.pojo;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class User{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userID", unique = true, nullable = false)
    private Long userID;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private Set<Task> tasks;

    private String email;
    private String password;

    public User(){}

    public User(String email, String password){
        this.email = email;
        this.password = password;
    }

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

    public Set<Task> getTasks() {
        return tasks;
    }

    public void setTasks(Set<Task> tasks) {
        this.tasks = tasks;
    }
}