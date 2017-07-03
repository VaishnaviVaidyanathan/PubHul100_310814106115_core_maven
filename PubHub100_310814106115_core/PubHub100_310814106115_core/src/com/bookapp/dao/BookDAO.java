package com.bookapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

import com.bookapp.model.Book;
import com.bookapp.util.ConnectionUtil;

public class BookDAO {

	JdbcTemplate jdbcTemplate = ConnectionUtil.getJdbcTemplate();


	public void save(Book book) {

		String sql = "INSERT INTO books(NAME,price) VALUES(?,?)";
		Object[] params = { book.getName(), book.getPrice() };
		int rows = jdbcTemplate.update(sql, params);
		System.out.println("No of rows inserted: " + rows);

	}
	public void update(Book book) {

		String sql = "UPDATE books SET NAME=? WHERE id=?";
		Object[] params = { book.getName(), book.getPrice() };
		int rows = jdbcTemplate.update(sql, params);
		System.out.println("No of rows updated: " + rows);

	}
	public void delete(Book book) {

		String sql = "DELETE FROM books WHERE name=?";
		Object[] params = { book.getName()};
		int rows = jdbcTemplate.update(sql, params);
		System.out.println("No of rows deleted: " + rows);

	}
	public List<Book> list() {

		String sql = "SELECT NAME,price FROM books ";
		List<Book> list = jdbcTemplate.query(sql, (rs, rowNum) -> {
			Book book = new Book();
			book.setName(rs.getString("name"));
			book.setPrice(rs.getInt("price"));
			return book;

		});
		return list;
	
}
	public List<Book> findByName(Book book) throws ClassNotFoundException, SQLException {
		Connection connection = ConnectionUtil.getConnection();
		PreparedStatement pst = connection.prepareStatement("SELECT name,price FROM books where name=?");
		pst.setString(1, book.getName());
		ResultSet rs = pst.executeQuery();
		List<Book> books = new ArrayList<>();
		while (rs.next()) {
			Book book1 = new Book();
			book1.setName(rs.getString(1));
			book1.setPrice(rs.getInt(2));
			books.add(book1);
		}
	}