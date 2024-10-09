package vn.iotstar.controllers;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.iotstar.services.impl.UserService;
import vn.iotstar.utils.Constant;

@WebServlet(urlPatterns = { "/forgotpsw" })
public class ForgotPswController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/views/forgotpsw.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setCharacterEncoding("UTF-8");
		req.setCharacterEncoding("UTF-8");
		String email = req.getParameter("email");
		String newpsw = req.getParameter("newpsw");
		UserService service = new UserService();
		String alertMsg = "";
		if (!service.checkExistEmail(email)) {
			alertMsg = "Email không tồn tại!";
			req.setAttribute("alert", alertMsg);
			req.getRequestDispatcher(Constant.Path.ForgotPsw).forward(req, resp);
			return;
		}
		try {
			service.update(newpsw, email);
			alertMsg = "Đổi thành công!";
			req.setAttribute("alert", alertMsg);
			resp.sendRedirect(req.getContextPath() + "/login");
		} catch (Exception e) {
			e.printStackTrace();
			alertMsg = "System error!";
			req.setAttribute("alert", alertMsg);
			req.getRequestDispatcher(Constant.Path.ForgotPsw).forward(req, resp);
		}
	}

}