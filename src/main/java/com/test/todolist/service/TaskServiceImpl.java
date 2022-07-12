package com.test.todolist.service;

import java.util.List;

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
import com.test.todolist.bean.Task;
import com.test.todolist.bean.Token;
import com.test.todolist.dao.TaskDao;

@Service
public class TaskServiceImpl implements TaskService {
	
	 @Autowired
	 private TaskDao taskDao;
	 
	 @Autowired
    private RestTemplate restTemplate;
	 
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
	
	public boolean isTokenAccessValid2(String accessToken) {
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
				if(token.getEmail_verified().equals("true"))return true;
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
		return false;
	}

}
