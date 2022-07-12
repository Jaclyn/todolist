package com.test.todolist.service;

import java.util.List;

import com.test.todolist.bean.Task;

public interface TaskService {
	
	List<Task> findAll();
	
	int add(Task task);
	
	int markCompleted(int id);
	
	int delete(int id);
	
	boolean isTokenAccessValid2(String accessToken);

}
