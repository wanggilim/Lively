
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

import com.quadcore.lively.controller.AdminController;
import com.quadcore.lively.controller.MemberController;
import com.quadcore.lively.model.AdminVO;
import com.quadcore.lively.model.MemberVO;

/**
 * Servlet implementation class TestServlet
 */
@WebServlet("*.do")
public class TestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String path = "";
	private String realPath = "";
	private String uri = "";
	private String url = "";
	private String action = "";
	private Map<String, Object> data;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		path = request.getContextPath(); // /webprogramming
		realPath = request.getServletContext().getRealPath(".");
		uri = request.getRequestURI(); // /webprogramming/*.do

		
		url = request.getRequestURL().toString(); // http://localhost:9090/webprogramming/*.do
		action = uri.substring(path.length(), uri.length() - 3); // *
		response.setCharacterEncoding("utf-8");
		int adminNo;
		String page=null;
		String tableName = null;
		int adminLevel;
		String adminEmail=null;
		String adminPass =null;
		int userLevel;
		String userMail=null;
		int userNo;
		//admin 등록(가입)
		if(action.equals("/admin/adminInsert")) {
			adminEmail = request.getParameter("adminEmail");
			adminPass  = request.getParameter("adminPass");
			
			AdminController control = new AdminController();
			control.insertAdmin(adminEmail,adminPass); // int로 값이 들어옴.
			page="/admin/admin.jsp";

		}
		//admin 조건 검색
		if(action.equals("/admin/adminRef2")) {
		     AdminController control = new AdminController();
			 List<AdminVO> adminList = new ArrayList<>();
			 adminLevel = Integer.parseInt(request.getParameter("adminLevel"));
			 adminEmail = request.getParameter("adminEmail");
			 tableName ="admin";
			 adminList= (List<AdminVO>)control.selectByLevelEmail(adminLevel, adminEmail,tableName);
			 request.setAttribute("adminList", adminList);
			 page="/admin/adminRef.jsp";
			
		}
		//member 조건 검색
		if(action.equals("/admin/memberRef2")) {
			AdminController control = new AdminController();
			List<MemberVO> memberList = new ArrayList<>();
				
			userLevel = Integer.parseInt(request.getParameter("userLevel"));
			userMail = request.getParameter("userMail");
			tableName ="member";
			memberList= (List<MemberVO>)control.selectByLevelEmail(userLevel, userMail,tableName);
			request.setAttribute("memberList", memberList);
			page="/admin/memberRef.jsp";
			
		}
		// Ajax 검색 (테이블 명에 따른 전체 검색)
		if (action.equals("/admin/admin") || action.equals("/admin/admin2" )) {
			tableName = request.getParameter("tableName");

			if (tableName.equals("admin")) {
				AdminController control = new AdminController();
				List<AdminVO> adminList = new ArrayList<>(); // import
				adminList = (List<AdminVO>) control.selectByTableName(tableName);
				request.setAttribute("adminList", adminList);
			} else {
				AdminController control = new AdminController();
				List<MemberVO> memberList = new ArrayList<>();
				memberList = (List<MemberVO>) control.selectByTableName(tableName);
				request.setAttribute("memberList", memberList);
			}
			
			

			if (action.equals("/admin/admin")) {
				
				if (tableName.equals("admin")) {
					page = "/admin/adminRef.jsp";
				} else {
					page = "/admin/memberRef.jsp";
				}
			}else {
				if (tableName.equals("admin")) {
					page = "/admin/adminRef2.jsp";
				} else {
					page = "/admin/memberRef2.jsp";
				}
			}

			
		}
		
		//admin 업데이트
		if(action.equals("/admin/adminUpdate")) {
			adminNo = Integer.parseInt(request.getParameter("adminNo"));
			adminPass = request.getParameter("adminPass");
			adminEmail = request.getParameter("adminEmail");
			adminLevel = Integer.parseInt(request.getParameter("adminLevel"));
			request.setAttribute("adminNo", adminNo);
			request.setAttribute("adminPass", adminPass);
			request.setAttribute("adminEmail", adminEmail);
			request.setAttribute("adminLevel", adminLevel);
			page="/admin/adminUpdate.jsp";
			if(request.getParameter("setAdminLevel") != null) {
			int setAdminLevel =	Integer.parseInt(request.getParameter("setAdminLevel"));
			AdminController control = new AdminController();
			control.updateByAdminNo(adminNo, adminEmail, adminPass, adminLevel, setAdminLevel);
			page="/admin/admin.jsp";
			}
		}
		//member 업데이트
		if(action.equals("/admin/memberUpdate")) {
			userNo = Integer.parseInt(request.getParameter("userNo"));
			String userPass = request.getParameter("userPass");
			userMail = request.getParameter("userMail");
			userLevel = Integer.parseInt(request.getParameter("userLevel"));
			String gender = request.getParameter("gender");
			String birthday = request.getParameter("birthday");
			request.setAttribute("userNo", userNo);
			request.setAttribute("userPass", userPass);
			request.setAttribute("userMail", userMail);
			request.setAttribute("userLevel", userLevel);
			request.setAttribute("gender", gender);
			request.setAttribute("birthday", birthday);
			page="/admin/memberUpdate.jsp";
			if(request.getParameter("setMemberLevel") != null) {
			int setMemberLevel =Integer.parseInt(request.getParameter("setMemberLevel"));
			AdminController control = new AdminController();
			control.updateByMemberNo(userNo, userMail, userPass, userLevel,gender,birthday,setMemberLevel);
			page="/admin/admin.jsp";
			}
		}
		
		// Admin 삭제
		if(action.equals("/admin/adminDelete")) {
			adminNo=Integer.parseInt(request.getParameter("adminNo"));
			AdminController control = new AdminController();
			control.deleteByAdminNo(adminNo);
			page="/admin/admin.jsp";
		}
		if(action.equals("/admin/memberDelete")) {
			userNo =Integer.parseInt(request.getParameter("userNo"));
			MemberController control = new MemberController();
			control.deleteUserFromUserMail(userNo);
			page="/admin/admin.jsp";
		}

		
		RequestDispatcher rd = request.getRequestDispatcher(page);
		rd.forward(request, response);
		}
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		    doGet( request, response);
	}
	}

