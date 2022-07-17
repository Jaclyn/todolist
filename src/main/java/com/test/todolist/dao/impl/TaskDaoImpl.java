package com.test.todolist.dao.impl;

import java.util.List;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.test.todolist.dao.TaskDao;
import com.test.todolist.db.bean.Task;
import com.test.todolist.mapper.TaskRowMapper;

@Repository
public class TaskDaoImpl implements TaskDao{

	NamedParameterJdbcTemplate template;  
	
	public TaskDaoImpl(NamedParameterJdbcTemplate template) {  
        this.template = template;  
	}  
	
	@Override
	public List<Task> findAll(String owner) {
		if(owner != null) {
			final String sql = "select * from task where owner = :owner";
			
			SqlParameterSource param = new MapSqlParameterSource()
			.addValue("owner", owner);
			
			return template.query(sql, param, new TaskRowMapper());
		}else {
			
			return template.query("select * from task", new TaskRowMapper());
		}
		
		//return template.query("select * from task where owner=", new TaskRowMapper());
	}
	

	@Override
	public int addTask(Task task) {
		if(task.getOwner() != null) {
			final String sql = "insert into task(id,description,creation_date,last_updated_date,status,owner) values (nextval('task_id'),:description,now(),now(),'A',:owner)";
	
	        KeyHolder holder = new GeneratedKeyHolder();
	        SqlParameterSource param = new MapSqlParameterSource()
			.addValue("description", task.getDescription())
			.addValue("owner", task.getOwner());

	        return template.update(sql,param, holder);
		}else {
			final String sql = "insert into task(id,description,creation_date,last_updated_date,status) values (nextval('task_id'),:description,now(),now(),'A')";
			
	        KeyHolder holder = new GeneratedKeyHolder();
	        SqlParameterSource param = new MapSqlParameterSource()
			.addValue("description", task.getDescription());

	        return template.update(sql,param, holder);
		}

	}
	
	@Override
	public int markCompleted(Task task) {
		if(task.getOwner() != null) {
			final String sql = "update task set status = 'C',last_updated_date=now() where id= :id and owner=:owner";
	
	        KeyHolder holder = new GeneratedKeyHolder();
	        SqlParameterSource param = new MapSqlParameterSource()
			.addValue("id", task.getId())
			.addValue("owner", task.getOwner());
	        return template.update(sql,param, holder);
		}else {
			final String sql = "update task set status = 'C',last_updated_date=now() where id= :id";
			
	        KeyHolder holder = new GeneratedKeyHolder();
	        SqlParameterSource param = new MapSqlParameterSource()
			.addValue("id", task.getId());
	        return template.update(sql,param, holder);
		}

	}

	@Override
	public Task findById(Task task) {
		if(task.getOwner() != null) {
			final String sql = "select * from task where id = :id  and owner=:owner";
			
			SqlParameterSource param = new MapSqlParameterSource()
					.addValue("id", task.getId())
					.addValue("owner", task.getOwner());
			return template.query(sql, param, new TaskRowMapper()).get(0);
		}else {
			final String sql = "select * from task where id = :id";
			
			SqlParameterSource param = new MapSqlParameterSource()
					.addValue("id", task.getId());
			return template.query(sql, param, new TaskRowMapper()).get(0);
		}
	}

	@Override
	public int delete(Task task) {
		if(task.getOwner() != null) {
			final String sql = "delete from task where id= :id  and owner=:owner";
	
	        KeyHolder holder = new GeneratedKeyHolder();
	        SqlParameterSource param = new MapSqlParameterSource()
	        		.addValue("id", task.getId())
	        		.addValue("owner", task.getOwner());
	        return template.update(sql,param, holder);
		}else {
			final String sql = "delete from task where id= :id";
			
	        KeyHolder holder = new GeneratedKeyHolder();
	        SqlParameterSource param = new MapSqlParameterSource()
	        		.addValue("id", task.getId());
	        return template.update(sql,param, holder);
		}
	}
	
	
}
