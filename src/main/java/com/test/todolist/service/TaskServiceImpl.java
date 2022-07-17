package com.test.todolist.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.todolist.bean.ResponseBean;
import com.test.todolist.bean.TaskBean;
import com.test.todolist.bean.TaskListBean;
import com.test.todolist.bean.Token;
import com.test.todolist.common.CommonConstant;
import com.test.todolist.dao.TaskDao;
import com.test.todolist.db.bean.Task;

@Service
public class TaskServiceImpl implements TaskService {
	
	 @Autowired
	 private TaskDao taskDao;
	 
	 @Autowired
    private RestTemplate restTemplate;
    
    @Autowired
	ObjectFactory<HttpSession> httpSessionFactory;
	
	private String getOwner(){
		return (String) httpSessionFactory.getObject().getAttribute(CommonConstant.OWNER);
	}
	
	private void setOwner(String owner){
		httpSessionFactory.getObject().setAttribute(CommonConstant.OWNER, owner);
	}
    
	 
	public TaskListBean findAll() {
		List<Task> tasks = taskDao.findAll(getOwner());
		TaskListBean bean = new TaskListBean();
		bean.setStatusCode(HttpStatus.OK.value());
		bean.setTask(tasks);
		bean.setRtnMsg("Successfully retrieve list of task.");
		bean.setErrorFlag(CommonConstant.NOT_ERROR);
		return bean;
	}
	
	public ResponseBean add(Task task) {
		task.setOwner(getOwner());
		int update = taskDao.addTask(task);
		ResponseBean bean = new TaskListBean();
		bean.setStatusCode(HttpStatus.OK.value());
		if(update > 0) {
			bean.setRtnMsg("Successfully add task.");
			bean.setErrorFlag(CommonConstant.NOT_ERROR);
		}else {
			bean.setRtnMsg("Failed to add task.");
			bean.setErrorFlag(CommonConstant.ERROR);
		}
		
		return bean;
	}

	@Override
	public ResponseBean markCompleted(Task task) {
		int update = taskDao.markCompleted(task);
		ResponseBean bean = new TaskListBean();
		bean.setStatusCode(HttpStatus.OK.value());
		if(update > 0) {
			bean.setRtnMsg("Successfully mark task as complete.");
			bean.setErrorFlag(CommonConstant.NOT_ERROR);
		}else {
			bean.setRtnMsg("Failed to mark task as complete.");
			bean.setErrorFlag(CommonConstant.ERROR);
		}
		
		return bean;
	}

	@Override
	public ResponseBean delete(Task task) {
		int update = taskDao.delete(task);
		ResponseBean bean = new TaskListBean();
		bean.setStatusCode(HttpStatus.OK.value());
		if(update > 0) {
			bean.setRtnMsg("Successfully delete task.");
			bean.setErrorFlag(CommonConstant.NOT_ERROR);
		}else {
			bean.setRtnMsg("Failed to delete task");
			bean.setErrorFlag(CommonConstant.ERROR);
		}
		
		return bean;
	}
	
	public ResponseBean verifyToken(String accessToken) {
		String url = "https://www.googleapis.com/oauth2/v3/tokeninfo?access_token="+accessToken;
		//Token response = restTemplate.getForObject(url, Token.class);
		//if(response.getEmailVerified().equals("true"))return true;
		ResponseBean bean = new ResponseBean();
		bean.setStatusCode(HttpStatus.OK.value());
		bean.setRtnMsg("Token is invalid");
		
		try {
			if(isTokenAccessValid(accessToken)) {
				bean.setRtnMsg("Token is valid");
				bean.setErrorFlag(CommonConstant.NOT_ERROR);
			}
		} catch (Exception e) {
			bean.setRtnMsg("Token is invalid");
			bean.setErrorFlag(CommonConstant.ERROR);
		}
		return bean;
	}
	
	public boolean isTokenAccessValid(String accessToken) throws Exception {
		String url = "https://www.googleapis.com/oauth2/v3/tokeninfo?access_token="+accessToken;
		//Token response = restTemplate.getForObject(url, Token.class);
		//if(response.getEmailVerified().equals("true"))return true;
		
		 HttpHeaders headers = new HttpHeaders();
         headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);

         HttpEntity entity = new HttpEntity(headers);

		ResponseEntity<String> responseEntity = restTemplate.exchange(url,
                 HttpMethod.GET, entity,
                 String.class);

	    if (responseEntity.getStatusCode().equals(HttpStatus.OK)) {
	       ObjectMapper mapper = new ObjectMapper();
	       try {
				Token token = mapper.readValue(responseEntity.getBody(), Token.class);
				if(token.getEmail_verified().equals("true")){
					setOwner(token.getEmail());
					return true;
				}
			} catch (JsonProcessingException e) {
				throw new Exception("Token is not recognised");
			}
	    }
		return false;
	}


}
