package vn.iostar.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import vn.iostar.entity.User;
import vn.iostar.services.impl.UserService;

import java.io.IOException;

@WebServlet(urlPatterns = "/login")
public class LoginController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/views/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        String alertMsg = "";

        if (username.isEmpty() || password.isEmpty()) {
            alertMsg = "Username or password is empty";
            req.setAttribute("alertMsg", alertMsg);
            req.getRequestDispatcher("/views/login.jsp").forward(req, resp);
        }

        UserService userService = new UserService();
        User user = userService.login(username, password);
        if (user != null) {
            HttpSession session = req.getSession();
            session.setAttribute("account", user);
            Cookie cookie = new Cookie("account", user.getUsername());
            cookie.setMaxAge(24 * 60 * 60);
            resp.addCookie(cookie);
            resp.sendRedirect(req.getContextPath() + "/waiting");
        }
        else {
            alertMsg = "Username or password is incorrect";
            req.setAttribute("alertMsg", alertMsg);
            req.getRequestDispatcher("/views/login.jsp").forward(req, resp);
        }
    }
}
