package com.csye6225.demo.pojo;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Task {

    @Id
    @GenericGenerator(name = "idGenerator", strategy = "uuid")
    @GeneratedValue(generator = "idGenerator")
    @Column(name = "taskID", length = 32)
    private String taskID;

    private String description;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "userID")
    private User user;

    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL)
    private Set<File> files;


    public Task(){

    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTaskID() {
        return taskID;
    }

    public void setTaskID(String taskID) {
        this.taskID = taskID;
    }

        public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<File> getFiles() {
        return files;
    }

    public void setFiles(Set<File> files) {
        this.files = files;
    }

    public Task(String description, User user) {
        this.description = description;
        this.user = user;
    }

}
