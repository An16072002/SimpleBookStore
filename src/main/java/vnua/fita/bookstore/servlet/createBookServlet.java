package vnua.fita.bookstore.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import vnua.fita.bookstore.bean.Book;
import vnua.fita.bookstore.formbean.BookForm;
import vnua.fita.bookstore.model.BookDAO;

/**
 * Servlet implementation class createBookServlet
 */
//@WebServlet(urlPatterns = {"/createBook"})
public class createBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BookDAO bookDAO=new BookDAO();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public createBookServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher rd = request.getServletContext()
				.getRequestDispatcher("/views/createBookView.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String title=new String(request.getParameter("title").getBytes("ISO-8859-1"), "UTF-8");
		String author=new String(request.getParameter("author").getBytes("ISO-8859-1"), "UTF-8");
		String priceStr = request.getParameter("price");
		String quantityInStockStr = request.getParameter("quantityInStock");

		BookForm bookForm = new BookForm(title, author, priceStr, quantityInStockStr);
		List<String> errors = bookForm.validateCreateBookForm();
		if(errors.isEmpty()) {
			int price=Integer.parseInt(priceStr);
			int quantityInStock=Integer.parseInt(quantityInStockStr);
			
			Book book=new Book(-1, title, author, price, quantityInStock,"","");
			boolean isUpdate=bookDAO.createBook(book);
			if(isUpdate) {
				response.sendRedirect(request.getContextPath()+"/adminHome");
			}else {
				response.sendRedirect(request.getContextPath() + "/adminHome");
			}
		}

		if (!errors.isEmpty()) {
			request.setAttribute("errors", String.join(", ", errors));
			request.setAttribute("book", bookForm);
			RequestDispatcher rd = request.getServletContext()
					.getRequestDispatcher("/views/createBookView.jsp");
			rd.forward(request, response);
		}
	}

}
