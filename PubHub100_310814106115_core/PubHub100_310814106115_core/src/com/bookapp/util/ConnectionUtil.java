package com.bookapp.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

public class ConnectionUtil {
		public static BasicDataSource dataSource() {

			BasicDataSource ds = new BasicDataSource();
			ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
			ds.setUrl("jdbc:mysql://localhost:3306/bookapp");
			ds.setUsername("root");
			ds.setPassword("password");
			return ds;

		}

		public static JdbcTemplate getJdbcTemplate() {
			JdbcTemplate jdbcTemplate = new JdbcTemplate();
			jdbcTemplate.setDataSource(dataSource());
			return jdbcTemplate;
		}
	}



