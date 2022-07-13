package com.test.todolist.service;

import com.test.todolist.bean.ResponseBean;
import com.test.todolist.bean.TaskListBean;
import com.test.todolist.db.bean.Task;

public interface TaskService {
	
	TaskListBean findAll();
	
	ResponseBean add(Task task);
	
	ResponseBean markCompleted(Task task);
	
	ResponseBean delete(Task task);
	
	boolean isTokenAccessValid(String accessToken) throws Exception;

}
