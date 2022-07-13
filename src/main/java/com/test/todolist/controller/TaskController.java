package com.test.todolist.controller;


import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.SessionScope;

import com.test.todolist.bean.ResponseBean;
import com.test.todolist.bean.TaskListBean;
import com.test.todolist.common.CommonConstant;
import com.test.todolist.db.bean.Task;
import com.test.todolist.service.TaskService;

@RestController
public class TaskController {

    @Autowired
    private JdbcTemplate jdbcTemplate;
   
    @Autowired
    private TaskService taskService;
    
    @PostMapping("/verifyToken")
	public String verifyToken(@RequestBody String accessToken) {
    	String rtnMsg = "token invalid";
    	try {
			if(taskService.isTokenAccessValid(accessToken)) {
				rtnMsg= "token valid";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			rtnMsg = e.getMessage();
		}
		return rtnMsg;
	}
    
	
	@GetMapping("/list")
	public TaskListBean listTask(@RequestHeader("Authorization") String auth) {
		TaskListBean resp = new TaskListBean();
		try {
			if(taskService.isTokenAccessValid(auth)) {
				resp = taskService.findAll();
			}else {
				resp.setErrorFlag(CommonConstant.ERROR);
				resp.setRtnMsg("Not authorized transaction");
			}
		} catch (Exception e) {
			resp.setErrorFlag(CommonConstant.ERROR);
			resp.setRtnMsg(e.getMessage());
		}
		
		return resp;
	}
	
	@PostMapping("/add")
	public ResponseBean addTask(@RequestHeader("Authorization") String auth, @RequestBody String description) {
		ResponseBean resp = new ResponseBean();
		try {
			if(taskService.isTokenAccessValid(auth)) {
				resp = taskService.add(new Task(description));
			}else {
				resp.setErrorFlag(CommonConstant.ERROR);
				resp.setRtnMsg("Not authorized transaction");
			}
		} catch (Exception e) {
			resp.setErrorFlag(CommonConstant.ERROR);
			resp.setRtnMsg(e.getMessage());
		}
		return resp;
	}
	
	@PostMapping("/delete")
	public ResponseBean delete(@RequestHeader("Authorization") String auth,@RequestBody int id) {
		ResponseBean resp = new ResponseBean();
		try {
			if(taskService.isTokenAccessValid(auth)) {
				resp = taskService.delete(new Task(id));
			}else {
				resp.setErrorFlag(CommonConstant.ERROR);
				resp.setRtnMsg("Not authorized transaction");
			}
		} catch (Exception e) {
			resp.setErrorFlag(CommonConstant.ERROR);
			resp.setRtnMsg(e.getMessage());
		}
		return resp;
	}
	
	@PostMapping("/markComplete")
	public ResponseBean updateTask(@RequestHeader("Authorization") String auth,@RequestBody int id, HttpSession session) {
		ResponseBean resp = new ResponseBean();
		try {
			if(taskService.isTokenAccessValid(auth)) {
				resp = taskService.markCompleted(new Task(id));
			}else {
				resp.setErrorFlag(CommonConstant.ERROR);
				resp.setRtnMsg("Not authorized transaction");
			}
		} catch (Exception e) {
			resp.setErrorFlag(CommonConstant.ERROR);
			resp.setRtnMsg(e.getMessage());
		}
		return resp;
	}
}
