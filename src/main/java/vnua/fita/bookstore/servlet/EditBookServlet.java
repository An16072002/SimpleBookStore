package vnua.fita.bookstore.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import vnua.fita.bookstore.bean.Book;
import vnua.fita.bookstore.formbean.BookForm;
import vnua.fita.bookstore.model.BookDAO;

public class EditBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BookDAO bookDAO=new BookDAO();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<String> errors = new ArrayList<String>();

		String bookIdStr = request.getParameter("bookId");
		int bookId = -1;
		try {
			bookId = Integer.parseInt(bookIdStr);
		} catch (Exception e) {
			errors.add("Id không tồn tại");
		}

		if (errors.isEmpty()) {
			Book book = bookDAO.getBook(bookId);
			if (book == null) {
				errors.add("Không lấy được sách");
			} else {
				request.setAttribute("book", book);
				RequestDispatcher rd = request.getServletContext()
						.getRequestDispatcher("/views/editBookView.jsp");
				rd.forward(request, response);
			}
		}
		if (!errors.isEmpty()) {
			request.setAttribute("errors", String.join(", ", errors));
			RequestDispatcher rd = request.getServletContext()
					.getRequestDispatcher("/adminHome");
			rd.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String bookIdStr = request.getParameter("bookId");
		String title=new String(request.getParameter("title").getBytes("ISO-8859-1"), "UTF-8");
		String author=new String(request.getParameter("author").getBytes("ISO-8859-1"), "UTF-8");
		String priceStr = request.getParameter("price");
		String quantityInStockStr = request.getParameter("quantityInStock");
		
		BookForm bookForm = new BookForm(bookIdStr, title, author, priceStr,
				quantityInStockStr);
		List<String> errors = new ArrayList<String>();
		if (errors.isEmpty()) {
			int bookId = Integer.parseInt(bookIdStr);
			int price = Integer.parseInt(priceStr);
			int quantityInStock = Integer.parseInt(quantityInStockStr);

			Book book=new Book(bookId, title, author, price, quantityInStock,"","");
			boolean resultUpdate = bookDAO.updateBook(book);
			if (!resultUpdate) {
				errors.add("Update thất bại");
			} else {
				response.sendRedirect(request.getContextPath() + "/adminHome");
			}
		}

		if (!errors.isEmpty()) {
			request.setAttribute("errors", errors);
			request.setAttribute("book", bookForm);
			RequestDispatcher rd = request.getServletContext()
					.getRequestDispatcher("/views/editBookView.jsp");
			rd.forward(request, response);
		}
	}

}
