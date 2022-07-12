package com.test.todolist.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.test.todolist.bean.Task;

public class TaskRowMapper implements RowMapper<Task> {

	@Override
	public Task mapRow(ResultSet rs, int arg1) throws SQLException {
		Task task = new Task();
		task.setId(rs.getInt("id"));
		task.setDescription(rs.getString("description"));
		task.setStatus(rs.getString("status"));
		task.setCreationDate(rs.getDate("creation_date"));
		task.setLastUpdatedDate(rs.getDate("last_updated_date"));
		return task;
	}


}
