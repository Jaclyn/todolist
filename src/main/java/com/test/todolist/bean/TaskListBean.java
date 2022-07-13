package com.test.todolist.bean;

import java.util.List;

import com.test.todolist.db.bean.Task;

public class TaskListBean extends ResponseBean{

	private List<Task> task;

	public List<Task> getTask() {
		return task;
	}

	public void setTask(List<Task> task) {
		this.task = task;
	}


	
}
