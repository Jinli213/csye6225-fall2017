package com.csye6225.demo.controllers;

import com.csye6225.demo.pojo.*;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class TaskController {

    @Autowired(required = false)
    private TaskRepository taskRepository;

    @Autowired(required = false)
    private AccountRepository accountRepository;

    @RequestMapping(value = "/tasks", method = RequestMethod.GET)
    @ResponseBody
    public String displayTask(){

        JsonArray jsonArray = new JsonArray();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        List<Account> accounts = (List<Account>) accountRepository.findAll();
        for (Account  a : accounts){
            if (a.getEmail().equals(username)){
                for(Task t :a.getTasks()){
                    JsonObject jsonObject = new JsonObject();
                    jsonObject.addProperty("id", t.getTaskID());
                    jsonObject.addProperty("description", t.getDescription());
                    for(File f :t.getFiles()) {
                        JsonObject jsonObject1 = new JsonObject();
                        jsonObject1.addProperty("id", f.getFileID());
                        jsonObject1.addProperty("url", f.getUrl());
                        jsonArray.add(jsonObject1);
                    }
                    jsonArray.add(jsonObject);
                }
            }
        }

        return jsonArray.toString();
    }

    @RequestMapping(value = "/tasks", method = RequestMethod.POST)
    @ResponseBody
    public String addTask(@RequestBody Task task){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Task t = new Task();
        List<Account> accounts = (List<Account>) accountRepository.findAll();
        for (Account  a : accounts){
            if (a.getEmail().equals(username)){
                t.setAccount(a);
            }
        }
        t.setCreatedBy(username);
        t.setDescription(task.getDescription());
        taskRepository.save(t);
        return "task created successfully";
    }

    @RequestMapping(value = "/tasks/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public String updateTask(@PathVariable String id, @RequestBody Task task){

        Task t = taskRepository.findOne(id);
        t.setDescription(task.getDescription());
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("description",t.getDescription());

        return jsonObject.toString();
    }

    @RequestMapping(value = "/tasks/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public String deleteTask(@PathVariable String id){

        taskRepository.delete(id);
        return "task deleted successfully";
    }


}
