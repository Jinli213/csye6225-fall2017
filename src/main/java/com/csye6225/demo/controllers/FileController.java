package com.csye6225.demo.controllers;

import com.csye6225.demo.pojo.File;
import com.csye6225.demo.pojo.FileRepository;
import com.csye6225.demo.pojo.Task;
import com.csye6225.demo.pojo.TaskRepository;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@Controller
public class FileController {

    @Autowired(required = false)
    private TaskRepository taskRepository;

    @Autowired(required = false)
    private FileRepository fileRepository;

    @RequestMapping(value = "/tasks/{id}/attachments", method = RequestMethod.GET)
    @ResponseBody
    public String displayFile(@PathVariable String id){

        Task t = taskRepository.findOne(id);
        Set<File> files =t.getFiles();

        JsonArray jsonArray = new JsonArray();

        for (File file : files){
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("Taskid",t.getTaskID());
            jsonObject.addProperty("Fileid",file.getFileID());
            jsonObject.addProperty("url",file.getUrl());
            jsonArray.add(jsonObject);
        }

        return jsonArray.toString();
    }

    @RequestMapping(value = "/tasks/{id}/attachments", method = RequestMethod.POST)
    @ResponseBody
    public String attachFile(@PathVariable String id, @RequestBody File file){

        File f = new File();
        f.setTask(taskRepository.findOne(id));
        f.setUrl(file.getUrl());
        fileRepository.save(f);
        return "File attached successfully";
    }

    @RequestMapping(value = "/tasks/{id}/attachments/{idAttachments}", method = RequestMethod.DELETE)
    @ResponseBody
    public String deleteFile(@PathVariable String id, @PathVariable Long idAttachments){

        fileRepository.delete(idAttachments);
        return "File deleted successfully";
    }
}
