package com.test.todolist;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import com.test.todolist.bean.ResponseBean;
import com.test.todolist.bean.TaskListBean;

@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
class TodolistApplicationTests {

	@Autowired
	private TestRestTemplate restTemplate;
	private String token = "ya29.A0AVA9y1vjNC-R1zLQ2C7FSPjLtfReh3EsGIZJgLUd79mWBufsl3hC0V8YQkLJt4b6aZeFdrCX_fVE6raXKIQxhm0iy5oBQwtxaZjwMmLgDqsGej-rhXMS4WUA4jGIII_chHbMV1eYCFjUDhenM61HHaJMI6B5YUNnWUtBVEFTQVRBU0ZRRTY1ZHI4YkJjdUtURHZhSzVZdUJZdzlnYlVfUQ0163";
	
	@Test
	void contextLoads() {
	}
	
	@Test
	public void 
	testVerifyToken_whenRequestIsExecuted_thenTellVerifyTokenStatusCodeIs200()
	  throws ClientProtocolException, IOException {
	 
	   // Given
	   int expectedValue = 200;
	
       HttpHeaders headers = new HttpHeaders();

       HttpEntity<String> request = new HttpEntity<>(token, headers);
      // System.out.println(request.toString());
       
       // When
	   ResponseEntity<ResponseBean> result = restTemplate.postForEntity( "http://localhost:8080/todolist/verifyToken" , request, ResponseBean.class);

	   // Then
	   int actualValue = result.getBody().getStatusCode();
	   assertEquals( expectedValue, actualValue );
	}
	
	@Test
	public void 
	testListAll_whenRequestIsExecuted_thenListAllStatusCodeIs200()
	  throws ClientProtocolException, IOException {
	 
	   // Given
	   int expectedValue = 200;
	
       HttpHeaders headers = new HttpHeaders();
       headers.add("Authorization", token);

       HttpEntity<String> request = new HttpEntity<>(null, headers);
       
       // When
	   ResponseEntity<TaskListBean> result = restTemplate.exchange( "http://localhost:8080/todolist/list" ,HttpMethod.GET, request, TaskListBean.class);

	   // Then
	   int actualValue =  result.getBody().getStatusCode();
	   assertEquals( expectedValue, actualValue );
	}
	
	@Test
	public void 
	testAddTask_whenRequestIsExecuted_thenAddTaskStusCodeIs200()
	  throws ClientProtocolException, IOException {
	 
	   // Given
	   int expectedValue = 200;
	
       HttpHeaders headers = new HttpHeaders();
       headers.add("Authorization", token);

       HttpEntity<String> request = new HttpEntity<>("Dancing", headers);
       
       // When
	   ResponseEntity<ResponseBean> result = restTemplate.postForEntity( "http://localhost:8080/todolist/add" , request, ResponseBean.class);

	   // Then
	   int actualValue =  result.getBody().getStatusCode();
	   assertEquals( expectedValue, actualValue );
	}
	
	@Test
	public void 
	testDeleteTask_whenRequestIsExecuted_thenDeleteTaskStusCodeIs200()
	  throws ClientProtocolException, IOException {
	 
	   // Given
	   int expectedValue = 200;
	
       HttpHeaders headers = new HttpHeaders();
       headers.add("Authorization", token);

       HttpEntity<Integer> request = new HttpEntity<>(1, headers);
       
       // When
	   ResponseEntity<ResponseBean> result = restTemplate.postForEntity( "http://localhost:8080/todolist/delete" , request, ResponseBean.class);

	   // Then
	   int actualValue =  result.getBody().getStatusCode();
	   assertEquals( expectedValue, actualValue );
	}
	
	@Test
	public void 
	testMarkComplete_whenRequestIsExecuted_thenMarkCompleteStusCodeIs200()
	  throws ClientProtocolException, IOException {
	 
	   // Given
	   int expectedValue = 200;
	
       HttpHeaders headers = new HttpHeaders();
       headers.add("Authorization", token);

       HttpEntity<Integer> request = new HttpEntity<>(1, headers);
       
       // When
	   ResponseEntity<ResponseBean> result = restTemplate.postForEntity( "http://localhost:8080/todolist/markComplete" , request, ResponseBean.class);

	   // Then
	   int actualValue =  result.getBody().getStatusCode();
	   assertEquals( expectedValue, actualValue );
	}
	

}
