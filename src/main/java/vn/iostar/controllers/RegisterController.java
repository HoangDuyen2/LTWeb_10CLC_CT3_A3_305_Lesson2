package vn.iostar.controllers;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.iostar.entity.Role;
import vn.iostar.entity.User;
import vn.iostar.services.impl.UserService;

import java.io.IOException;

@WebServlet(urlPatterns = "/register")
public class RegisterController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/views/register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String fullname = req.getParameter("fullname");
        String address = req.getParameter("address");
        String image = req.getParameter("images");
        String phone = req.getParameter("phone");
        String confirmPassword = req.getParameter("repassword");

        String alertMsg = "";
        if (username.isEmpty() || password.isEmpty() || fullname.isEmpty() || address.isEmpty() || phone.isEmpty()) {
            alertMsg = "All fields don't empty";
            req.setAttribute("alertMsg", alertMsg);
            req.getRequestDispatcher("/views/register.jsp").forward(req, resp);
            return;
        }
        if (!password.equals(confirmPassword)) {
            alertMsg = "Passwords do not match";
            req.setAttribute("alertMsg", alertMsg);
            req.getRequestDispatcher("/views/register.jsp").forward(req, resp);
            return;
        }
        UserService userService = new UserService();
        if (userService.findByPhone(phone)){
            alertMsg = "Phone number already in use";
            req.setAttribute("alertMsg", alertMsg);
            req.getRequestDispatcher("/views/register.jsp").forward(req, resp);
            return;
        }
        if (userService.findByUsername(username)){
            alertMsg = "Username already in use";
            req.setAttribute("alertMsg", alertMsg);
            req.getRequestDispatcher("/views/register.jsp").forward(req, resp);
            return;
        }
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setFullname(fullname);
        user.setAddress(address);
        user.setPhone(phone);
        user.setImages(image);
        Role role = new Role();
        role.setRoleid(3);
        role.setRolename("user");
        user.setRole(role);
        user.setCreateDate(new java.sql.Date(System.currentTimeMillis()));
        if (!userService.insert(user)){
            alertMsg = "User not inserted";
            req.setAttribute("alertMsg", alertMsg);
            req.getRequestDispatcher("/views/register.jsp").forward(req, resp);
        }
        else {
            resp.sendRedirect(req.getContextPath()+"/login");
        }
    }
}
