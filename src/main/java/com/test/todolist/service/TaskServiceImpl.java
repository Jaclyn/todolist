package com.test.todolist.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.todolist.bean.Task;
import com.test.todolist.dao.TaskDao;

@Service
public class TaskServiceImpl implements TaskService {
	
	 @Autowired
	 private TaskDao taskDao;
	 
	public List<Task> findAll() {
		return taskDao.findAll();
	}
	
	public int add(Task task) {
		return taskDao.addTask(task);
	}

	@Override
	public int markCompleted(int id) {
		return taskDao.markCompleted(id);
	}

	@Override
	public int delete(int id) {
		return taskDao.delete(id);
	}

}
