package com.rootgrouptechnologies.api.controllers;

import com.rootgrouptechnologies.api.db.TaskRepository;
import com.rootgrouptechnologies.api.db.entities.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TodoController {
    @Autowired
    TaskRepository taskRepository;

    @GetMapping("/todos")
    public List getAllTasks() {
        return taskRepository.findAll();
    }

    @PostMapping("/todos")
    public Task createTask(@RequestBody Task task) {
        return taskRepository.save(task);
    }

}
