package vnua.fita.bookstore.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import vnua.fita.bookstore.bean.Book;

public class BookDAO {
	public List<Book> listAllBook() {
		Connection connection = DBConnection.createConnection();
		String sql = "SELECT * FROM tblbook";
		List<Book> list = new ArrayList<Book>();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				Book book = new Book(resultSet.getInt("book_id"), resultSet.getString("title"),
						resultSet.getString("author"), resultSet.getInt("price"), resultSet.getInt("quantity_in_stock"),
						resultSet.getString("detail"), resultSet.getString("image_path"));
				book.setImagePath(resultSet.getString("image_path"));
				book.setDetail(resultSet.getString("detail"));
				list.add(book);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			DBConnection.closeConnection(connection);
		}
		return list;
	}
	
	public List<Book> listAllBook(String keyword) {
		Connection connection = DBConnection.createConnection();
		String sql = "SELECT * FROM tblbook where title like ?";
		List<Book> list = new ArrayList<Book>();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, keyword);

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				Book book = new Book(resultSet.getInt("book_id"), resultSet.getString("title"),
						resultSet.getString("author"), resultSet.getInt("price"), resultSet.getInt("quantity_in_stock"),
						resultSet.getString("detail"), resultSet.getString("image_path"));
				book.setImagePath(resultSet.getString("image_path"));
				book.setDetail(resultSet.getString("detail"));
				list.add(book);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			DBConnection.closeConnection(connection);
		}
		return list;
	}

	public Book getBook(int bookId) {
		Connection connection = DBConnection.createConnection();
		String sql = "SELECT * FROM tblbook WHERE book_id=?";
		Book book = new Book();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, bookId);
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				book = new Book(resultSet.getInt("book_id"), resultSet.getString("title"),
						resultSet.getString("author"), resultSet.getInt("price"), resultSet.getInt("quantity_in_stock"),
						resultSet.getString("detail"), resultSet.getString("image_path"));
				book.setImagePath(resultSet.getString("image_path"));
				book.setDetail(resultSet.getString("detail"));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			DBConnection.closeConnection(connection);
		}
		return book;
	}

	public boolean deleteBook(int bookId) {
		boolean isDelete = false;
		Connection connection = DBConnection.createConnection();
		String sql = "DELETE FROM tblbook t WHERE t.book_id=?";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, bookId);
			int count = preparedStatement.executeUpdate();

			isDelete = count > 0;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			DBConnection.closeConnection(connection);
		}
		return isDelete;
	}

	public boolean updateBook(Book book) {
		boolean isUpdate = false;
		Connection connection = DBConnection.createConnection();
		String sql = "UPDATE tblbook \r\n" + "SET title = ?, author = ?,price =?,quantity_in_stock=?,detail=?\r\n"
				+ "WHERE book_id = ?;";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, book.getTitle());
			preparedStatement.setString(2, book.getAuthor());
			preparedStatement.setInt(3, book.getPrice());
			preparedStatement.setInt(4, book.getQuantityInStock());
			preparedStatement.setString(5, book.getDetail());
			preparedStatement.setInt(6, book.getBookId());
			int count = preparedStatement.executeUpdate();

			isUpdate = count > 0;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			DBConnection.closeConnection(connection);
		}
		return isUpdate;
	}

	public boolean createBook(Book book) {
		boolean isSuccess = false;
		Connection connection = DBConnection.createConnection();
		String sql = "INSERT INTO tblbook (title , author , price , quantity_in_stock , detail,image_path)\r\n"
				+ "VALUES (?,?,?,?,?,?)";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, book.getTitle());
			preparedStatement.setString(2, book.getAuthor());
			preparedStatement.setInt(3, book.getPrice());
			preparedStatement.setInt(4, book.getQuantityInStock());
			preparedStatement.setString(5, book.getDetail());
			preparedStatement.setString(6, book.getImagePath());
			int count = preparedStatement.executeUpdate();

			isSuccess = count > 0;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			DBConnection.closeConnection(connection);
		}
		return isSuccess;
	}

	public List<Book> searchBooks(String keyword) {

		List<Book> listBook = new ArrayList<>();
		String sql = "SELECT * FROM tblbook WHERE title LIKE ?";
		Connection connection = DBConnection.createConnection();

		try {
			PreparedStatement pst = connection.prepareStatement(sql);
			pst.setString(1, "%" + keyword + "%"); // Them % de tim gan dung
			ResultSet resultSet = pst.executeQuery();
			while (resultSet.next()) {
				int id = resultSet.getInt("book_id");
				String title = resultSet.getString("title");
				String author = resultSet.getString("author");
				int price = resultSet.getInt("price");
				int quantityInStock = resultSet.getInt("price");
				Book book = new Book(id, title, author, price, quantityInStock, resultSet.getString("detail"),
						resultSet.getString("image_path"));
				listBook.add(book);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			DBConnection.closeConnection(connection);
		}
		
		return listBook;
	}
}
