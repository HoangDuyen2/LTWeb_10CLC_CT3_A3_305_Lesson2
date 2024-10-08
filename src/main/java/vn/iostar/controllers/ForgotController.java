package vn.iostar.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.iostar.services.impl.UserService;

import java.io.IOException;

@WebServlet(urlPatterns = "/forgot-password")
public class ForgotController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/views/forgot.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String confirmPassword = req.getParameter("password-repeat");

        String alertMsg = "";
        if (username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            alertMsg = "Username and password cannot be empty";
            req.setAttribute("alertMsg", alertMsg);
            req.getRequestDispatcher("/views/forgot.jsp").forward(req, resp);
            return;
        }
        if (!password.equals(confirmPassword)) {
            alertMsg = "Passwords do not match";
            req.setAttribute("alertMsg", alertMsg);
            req.getRequestDispatcher("/views/forgot.jsp").forward(req, resp);
            return;
        }
        UserService userService = new UserService();
        if (userService.findByUsername(username)){
            userService.update(username, password);
            resp.sendRedirect(req.getContextPath()+"/login");
        }
        else {
            alertMsg = "Username or password is incorrect";
            req.setAttribute("alertMsg", alertMsg);
            req.getRequestDispatcher("/views/forgot-password.jsp").forward(req, resp);
        }
    }
}
