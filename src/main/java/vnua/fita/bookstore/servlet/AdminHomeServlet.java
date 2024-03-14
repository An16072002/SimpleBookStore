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
import vnua.fita.bookstore.model.BookDAO;

public class AdminHomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BookDAO bookDAO=new BookDAO();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String errors = null;
		List<Book> list = bookDAO.listAllBook();
		if (list.isEmpty()) {
			errors = "Không thể lấy dữ liệu";
		}

		request.setAttribute("errors", errors);
		request.setAttribute("bookList", list);
		RequestDispatcher rd = this.getServletContext()
				.getRequestDispatcher("/views/adminHomeView.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
