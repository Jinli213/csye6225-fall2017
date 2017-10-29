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
    @JoinColumn(name = "user_id", referencedColumnName = "accountID")
    private Account account;

    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL)
    private Set<File> files;

    @Transient
    private String createdBy;

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

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Set<File> getFiles() {
        return files;
    }

    public void setFiles(Set<File> files) {
        this.files = files;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Task(String description, Account account) {
        this.description = description;
        this.account = account;
    }

}
