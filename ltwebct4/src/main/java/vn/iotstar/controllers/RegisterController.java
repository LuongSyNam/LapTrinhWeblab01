package vn.iotstar.controllers;

import java.io.IOException;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.iotstar.models.UserModel;
import vn.iotstar.services.impl.UserService;
import vn.iotstar.utils.Constant;;

@WebServlet(urlPatterns = {"/register"})
public class RegisterController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		/*
		 * HttpSession session = req.getSession(false); if (session != null &&
		 * session.getAttribute("username") != null) {
		 * resp.sendRedirect(req.getContextPath() + "/admin"); return; }
		 * 
		 * Cookie[] cookies = req.getCookies(); if (cookies != null) { for (Cookie
		 * cookie : cookies) { if (cookie.getName().equals("username")) { session =
		 * req.getSession(true); session.setAttribute("username", cookie.getValue());
		 * resp.sendRedirect(req.getContextPath() + "/admin"); return; } } }
		 * 
		 * req.getRequestDispatcher(Constant.Path.REGISTER).forward(req, resp);
		 */
		req.getRequestDispatcher(Constant.Path.REGISTER).forward(req, resp);
	}

	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		resp.setCharacterEncoding("UTF-8");
		req.setCharacterEncoding("UTF-8");
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String email = req.getParameter("email");
		String fullname = req.getParameter("fullname");
		String phone = req.getParameter("phone");
		UserService service = new UserService();
		String alertMsg = "";
		if (service.checkExistEmail(email)) {
			alertMsg = "Email đã tồn tại!";
			req.setAttribute("alert", alertMsg);
			req.getRequestDispatcher(Constant.Path.REGISTER).forward(req, resp);
			return;
		}
		if (service.checkExistUsername(username)) {
			alertMsg = "Tài khoản đã tồn tại!";
			req.setAttribute("alert", alertMsg);
			req.getRequestDispatcher(Constant.Path.REGISTER).forward(req, resp);
			return;
		}
		if (service.checkExistPhone(phone)) {
			alertMsg = "Số điện thoại đã tồn tại!";
			req.setAttribute("alert", alertMsg);
			req.getRequestDispatcher(Constant.Path.REGISTER).forward(req, resp);
			return;
		}
		UserModel user = service.register(username, password, email, fullname, phone);
		if (user != null) {
			// SendMail sm = new SendMail();
			// sm.sendMail(email, "Shopping.iotstar.vn", "Welcome to Shopping. Please Login
			// to use service. Thanks !");
			alertMsg = "Tạo thành công!";
			req.setAttribute("alert", alertMsg);
		    HttpSession session = req.getSession(true); 
		    session.setAttribute("account", user);
			resp.sendRedirect(req.getContextPath() + "/waiting");
		} else {
			alertMsg = "System error!";
			req.setAttribute("alert", alertMsg);
			req.getRequestDispatcher(Constant.Path.REGISTER).forward(req, resp);
		}
	}
}