

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;

import com.rushikesh.dto.Book;
import com.rushikesh.service.BookService;
import com.rushikesh.util.SpringUtil;


@WebServlet("/EditBookServlet")
public class EditBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		PrintWriter out=response.getWriter();
		ApplicationContext applicationContext = SpringUtil.getApplicationContext();
		BookService bookService = applicationContext.getBean("bookServiceImpl", BookService.class);		
		Book book=new Book();
		book.setId(Integer.parseInt(request.getParameter("editid")));
		book.setName(request.getParameter("editname"));
		book.setCode(request.getParameter("editcode"));
		int i=bookService.update(book);
		if(i>0)
		{
			response.sendRedirect("index.jsp");
		}
		else
		{
			out.println("Error In Update");
		}
		out.close();
	}

}
