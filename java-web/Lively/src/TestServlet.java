

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TestServlet
 */
@WebServlet("/test.do")
public class TestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String path = "";
	private String realPath = "";
	private String uri = "";
	private String url = "";
	private String action = "";
	private Map<String, Object> data;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		path = request.getContextPath();	// /webprogramming
    	realPath = request.getServletContext().getRealPath(".");
    	uri = request.getRequestURI();		// /webprogramming/*.do
    	url = request.getRequestURL().toString(); // http://localhost:9090/webprogramming/*.do
    	action = uri.substring(path.length(), uri.length()-3); // *
		response.setCharacterEncoding("utf-8");
		
		if(action.equals("/admin/tableName")) {
			String tableName = request.getParameter("tableName");
			AdminContorller adminControl = new AdminController();
			MemeberController memberControl = new MemberController();
			
			if(tableName.equals("admin")) {
				
			}
			else {
				
			}
			RequestDispatcher rd = request.getRequestDispatcher("");
		}
		
		if(action.equals("/admin/admin")) {
			String tableName = request.getParameter("tableName");
			
			if(tableName.equals("admin")) {
			AdminContorller control = new AdminController();
			List<AdminVO> adminList = new ArrayList<>(); //import
		    //String adminNo = request.getParameter("emailNo");
		    //int levelNo = Integer.parseInt(request.getParameter("levelNo"));
		    adminList = (AdminVO)control.SelectByTableName();
			RequestDispatcher rd = request.getRequestDispatcher("/admin/adminRef.jsp");
			rd.forward(request, response);
			}
			
			else { //if(tableName.equals("member")				
			MemberContorller control = new MemberContorller();
			List<MemberVO> memberList = new ArrayList<>();
			//String userNo = request.getParameter("userNo");
			memberList = (MemberVO)control.SelectByTableName("userNo");
			RequestDispatcher rd = request.getRequestDispatcher("/admin/memberRef.jsp");
			rd.forward(request, response);
			}
		}
		
		if(action.equals("")) {
			String getNumber = request.getParameter("");
		}
		

	}

	 

}
