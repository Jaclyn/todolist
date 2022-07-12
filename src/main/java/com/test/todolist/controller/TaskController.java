package com.test.todolist.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.test.todolist.auth.Authenticator;
import com.test.todolist.bean.Task;
import com.test.todolist.service.TaskService;

@RestController
public class TaskController {

    @Autowired
    private JdbcTemplate jdbcTemplate;
   
    @Autowired
    private TaskService taskService;
	
	@GetMapping("/loginTask")
	public GoogleAuthorizationCodeFlow login() {
		return Authenticator.newFlow();
	}
	
	@GetMapping("/list")
	public List<Task> listTask() {
		List<Task> tasks = taskService.findAll();
		return tasks;
	}
	
	@PostMapping("/add")
	public int addTask(@RequestBody String description) {
		return taskService.add(new Task(description));
	}
	
	@PostMapping("/delete")
	public int delete(@RequestBody int id) {
		return taskService.delete(id);
	}
	
	@PostMapping("/markComplete")
	public int updateTask(@RequestBody int id) {
		return taskService.markCompleted(id);
	}
}
