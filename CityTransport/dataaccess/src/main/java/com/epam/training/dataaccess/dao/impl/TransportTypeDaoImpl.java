package com.epam.training.dataaccess.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.epam.training.dataaccess.dao.TransportTypeDao;
import com.epam.training.dataaccess.model.TransportType;

@Repository
public class TransportTypeDaoImpl implements TransportTypeDao {

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public TransportType getByTypeName(String name) {
		return jdbcTemplate.queryForObject(
				"SELECT * FROM transport_type WHERE transport_type = ?",
				new Object[] { name },
				new BeanPropertyRowMapper<TransportType>(TransportType.class));
	}

	@Override
	public Long insert(final TransportType transportType) {
		KeyHolder keyHolder = new GeneratedKeyHolder();

		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection)
					throws SQLException {
				PreparedStatement ps = connection.prepareStatement(
						"INSERT INTO transport_type (transport_type,capacity,max_speed) VALUES (?,?,?)",
						new String[] { "id" });
				ps.setString(1, transportType.getType());
				ps.setLong(2, transportType.getCapacity());
				ps.setLong(3, transportType.getMaxSpeed());
				return ps;
			}
		}, keyHolder);
		return keyHolder.getKey().longValue();
	}

	@Override
	public void update(TransportType transportType) {
		jdbcTemplate.update(
				"UPDATE transport_type SET transport_type = ?, capacity = ?, max_speed = ? WHERE  id = ?",
				transportType.getType(), transportType.getCapacity(),
				transportType.getMaxSpeed(), transportType.getId());
	}

	@Override
	public void delete(String type) {
		jdbcTemplate.update("DELETE FROM transport_type WHERE transport_type = ?", type);
	}

	@Override
	public Long getIdByType(String type) {
		TransportType transportType =  jdbcTemplate.queryForObject(
				"SELECT * FROM transport_type WHERE transport_type = ?",
				new Object[] { type },
				new BeanPropertyRowMapper<TransportType>(TransportType.class));
		
		return transportType.getId();
	}

	@Override
	public List<TransportType> getAll() {
		return jdbcTemplate.query("SELECT * FROM transport_type",
				new BeanPropertyRowMapper<TransportType>(TransportType.class));
	}
}
