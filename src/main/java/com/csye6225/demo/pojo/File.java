package com.csye6225.demo.pojo;

import javax.persistence.*;

@Entity
public class File {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fileID", unique = true, nullable = false)
    private Long fileID;

    private String url;

    @ManyToOne
    @JoinColumn(name = "task_id", referencedColumnName = "taskID")
    private Task task;

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public File() {
    }

    public File(String url, Task task) {
        this.url = url;
        this.task = task;
    }
}
