package vn.iostar.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.iostar.entity.User;

import java.io.IOException;

@WebServlet(urlPatterns = "/waiting")
public class WaitingController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        if (session.getAttribute("account") != null) {
            User user = (User) session.getAttribute("account");
            if (user.getRole().getRoleid() == 1) {
                resp.sendRedirect(req.getContextPath()+"/admin/home");
            }
            else if (user.getRole().getRoleid() == 2) {
                resp.sendRedirect(req.getContextPath()+"/manager/home");
            }
            else {
                resp.sendRedirect(req.getContextPath()+"/user/home");
            }
        }
        else {
            resp.sendRedirect("/login");
        }
    }
}
