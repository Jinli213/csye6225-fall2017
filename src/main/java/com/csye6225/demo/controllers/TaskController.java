package com.csye6225.demo.controllers;

import com.csye6225.demo.pojo.Task;
import com.csye6225.demo.pojo.TaskRepository;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class TaskController {

    @Autowired(required = false)
    private TaskRepository taskRepository;

    @RequestMapping(value = "/tasks", method = RequestMethod.GET)
    @ResponseBody
    public String displayTask(){

        JsonArray jsonArray = new JsonArray();

        List<Task> taskList = (List<Task>) taskRepository.findAll();
        for (Task t : taskList){
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("id",t.getTaskID());
            jsonObject.addProperty("description",t.getDescription());
            jsonArray.add(jsonObject);
        }
        return jsonArray.toString();
    }

    @RequestMapping(value = "/tasks", method = RequestMethod.POST)
    @ResponseBody
    public String addTask(@RequestBody Task task){

        Task t = new Task();
        t.setDescription(task.getDescription());
        taskRepository.save(t);
        return "task created successfully";
    }

    @RequestMapping(value = "/tasks/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public String updateTask(@PathVariable String id){

        Task t = taskRepository.findOne(id);

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

    @RequestMapping(value = "/tasks/{id}/attachments", method = RequestMethod.GET)
    @ResponseBody
    public String displayFile(@PathVariable String id){

        Task t = taskRepository.findOne(id);

        JsonArray jsonArray = new JsonArray();

        return jsonArray.toString();
    }

}
