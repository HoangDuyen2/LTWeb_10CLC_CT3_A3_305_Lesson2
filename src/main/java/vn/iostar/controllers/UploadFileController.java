package vn.iostar.controllers;

import vn.iostar.utils.Constants;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@WebServlet(urlPatterns = "/images")
public class UploadFileController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String imagePath = req.getParameter("fname");
        File file = new File(Constants.DIR + "/" +imagePath);
        resp.setContentType("image/jpeg");
        if(file.exists()){
            IOUtils.copy(new FileInputStream(file), resp.getOutputStream());
        }
    }
}
