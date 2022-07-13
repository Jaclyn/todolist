package com.test.todolist.dao;

import java.util.List;

import com.test.todolist.db.bean.Task;

public interface TaskDao {
	
	List<Task> findAll(String owner);
	
	int addTask(Task task);
	
	Task findById(Task task);
	
	int markCompleted(Task task);
	
	int delete(Task task);
}
