package com.test.todolist.bean;

import com.test.todolist.db.bean.Task;

public class TaskBean extends ResponseBean{

	private Task task;

	public Task getTask() {
		return task;
	}

	public void setTask(Task task) {
		this.task = task;
	}
	
}
