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
import vnua.fita.bookstore.util.Constant;

@WebServlet(urlPatterns = {"/clientHome"})
public class ClientHomeService extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private BookDAO bookDAO=new BookDAO();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String errors=null;
		List<Book> list=null;
		
		String keyword=req.getParameter("keyword");
		if(keyword!=null && !keyword.isEmpty()) {
			list=bookDAO.listAllBook(keyword);
		}else {
			list=bookDAO.listAllBook();
			System.out.println(list);
		}
		if(list.isEmpty()) {
			errors=Constant.GET_BOOK_FAIL;
		}
		req.setAttribute("errors", errors);
		req.setAttribute("bookList", list);
		RequestDispatcher dispatcher=this.getServletContext().getRequestDispatcher("/views/clientHomeView.jsp");
		dispatcher.forward(req, resp);
	}
}
