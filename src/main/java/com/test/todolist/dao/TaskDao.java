package com.test.todolist.dao;

import java.util.List;

import com.test.todolist.bean.Task;

public interface TaskDao {
	
	List<Task> findAll();
	
	int addTask(Task task);
	
	Task findById(int id);
	
	int markCompleted(int id);
	
	int delete(int id);
}
