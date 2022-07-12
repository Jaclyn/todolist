package com.test.todolist.dao.impl;

import java.util.List;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.test.todolist.bean.Task;
import com.test.todolist.dao.TaskDao;
import com.test.todolist.mapper.TaskRowMapper;

@Repository
public class TaskDaoImpl implements TaskDao{

	NamedParameterJdbcTemplate template;  
	
	public TaskDaoImpl(NamedParameterJdbcTemplate template) {  
        this.template = template;  
	}  
	
	@Override
	public List<Task> findAll() {
		return template.query("select * from task", new TaskRowMapper());
	}
	

	@Override
	public int addTask(Task task) {
		final String sql = "insert into task(id,description,creation_date,last_updated_date,status) values (nextval('task_id'),:description,now(),now(),'A')";

        KeyHolder holder = new GeneratedKeyHolder();
        SqlParameterSource param = new MapSqlParameterSource()
		.addValue("description", task.getDescription());
        return template.update(sql,param, holder);

	}
	
	@Override
	public int markCompleted(int id) {
		final String sql = "update task set status = 'C',last_updated_date=now() where id= :id";

        KeyHolder holder = new GeneratedKeyHolder();
        SqlParameterSource param = new MapSqlParameterSource()
		.addValue("id", id);
        return template.update(sql,param, holder);

	}

	@Override
	public Task findById(int id) {
		final String sql = "select * from task where id = :id";
		
		SqlParameterSource param = new MapSqlParameterSource()
		.addValue("id", id);
		return template.query(sql, param, new TaskRowMapper()).get(0);
	}

	@Override
	public int delete(int id) {
		final String sql = "delete from task where id= :id";

        KeyHolder holder = new GeneratedKeyHolder();
        SqlParameterSource param = new MapSqlParameterSource()
		.addValue("id", id);
        return template.update(sql,param, holder);
	}
	
	
}
