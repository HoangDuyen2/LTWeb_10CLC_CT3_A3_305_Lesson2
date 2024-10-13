package vn.iostar.controllers.Admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import vn.iostar.entity.Category;
import vn.iostar.entity.Video;
import vn.iostar.services.ICategoryService;
import vn.iostar.services.IVideoService;
import vn.iostar.services.impl.CategoryService;
import vn.iostar.services.impl.VideoService;
import vn.iostar.utils.Constants;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

@MultipartConfig
@WebServlet(urlPatterns = {"/admin/videos","/admin/video/add","/admin/video/insert",
        "/admin/video/edit","/admin/video/upload","/admin/video/delete"})
public class VideoController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    IVideoService videoService = new VideoService();
    ICategoryService categoryService = new CategoryService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = req.getRequestURI();
        if (url.contains("/admin/videos")) {
            List<Video> videoList = videoService.getAllVideos();
            req.setAttribute("videoList", videoList);
            req.getRequestDispatcher("/views/admin/video-list.jsp").forward(req, resp);
        } else if (url.contains("/admin/video/add")) {
            List<Category> categoryList = categoryService.findAll();
            req.setAttribute("categoryList", categoryList);
            req.getRequestDispatcher("/views/admin/video-add.jsp").forward(req, resp);
        } else if (url.contains("/admin/video/edit")) {
            int id = Integer.parseInt(req.getParameter("id"));
            Video video = videoService.findById(id);
            req.setAttribute("video", video);
            List<Category> categoryList = categoryService.findAll();
            req.setAttribute("categoryList", categoryList);
            req.getRequestDispatcher("/views/admin/video-edit.jsp").forward(req, resp);
        } else if (url.contains("/admin/video/delete")) {
            int id = Integer.parseInt(req.getParameter("id"));
            Video video = videoService.findById(id);
            if (video != null) {
                videoService.delete(video.getVideoid());
            }
            resp.sendRedirect(req.getContextPath() + "/admin/videos");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = req.getRequestURI();
        if (url.contains("/admin/video/insert")) {
            // Kiểm tra và xử lý giá trị "active"
            String activeParam = req.getParameter("active");
            boolean active = false; // giá trị mặc định
            if (activeParam != null && !activeParam.isEmpty()) {
                int act = Integer.parseInt(activeParam);
                active = (act == 1);
            }

            String description = req.getParameter("description");
            String title = req.getParameter("title");

            // Kiểm tra và xử lý "views"
            int views = 0;
            String viewsParam = req.getParameter("views");
            if (viewsParam != null && !viewsParam.isEmpty()) {
                try {
                    views = Integer.parseInt(viewsParam);
                } catch (NumberFormatException e) {
                    views = 0; // giá trị mặc định
                }
            }

            String poster = req.getParameter("poster");
            if (poster == null || poster.isEmpty()) {
                poster = "0c93b81da322586c08b5d61227b1f98b.jpg";
            }

            Video video = new Video();
            video.setActive(active);
            video.setDescription(description);
            video.setTitle(title);
            video.setViews(views);

            // Xử lý upload file
            String fName = "";
            String uploadPath = Constants.DIR;
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }
            try {
                Part part = req.getPart("poster1");
                if (part.getSize() > 0) {
                    String fileName = Paths.get(part.getSubmittedFileName()).getFileName().toString();
                    int index = fileName.lastIndexOf(".");
                    String fileExtension = fileName.substring(index + 1);
                    fName = System.currentTimeMillis() + "." + fileExtension;
                    part.write(uploadPath + "/"+fName);
                    video.setPoster(fName);
                } else {
                    video.setPoster(poster);
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            Category category = null;
            category = categoryService.findById(Integer.parseInt(req.getParameter("selectedCategoryId")));
            if (category != null) {
                video.setCategory(category);
            }

            videoService.insert(video);
            resp.sendRedirect(req.getContextPath() + "/admin/videos");
        }
        else if (url.contains("/admin/video/upload")) {
            int id = Integer.parseInt(req.getParameter("videoid"));
            int act = Integer.parseInt(req.getParameter("active"));
            boolean active;
            if(act == 1){
                active = true;
            }
            else{
                active = false;
            }
            String description = req.getParameter("description");
            String poster = req.getParameter("poster");
            String title = req.getParameter("title");
            int views = Integer.parseInt(req.getParameter("views"));
            Video video = videoService.findById(id);
            video.setActive(active);
            video.setDescription(description);
            video.setTitle(title);
            video.setViews(views);

            String fName = "";
            String uploadPath = Constants.DIR;
            File uploadDir = new File(uploadPath);
            if(!uploadDir.exists()) {
                uploadDir.mkdir();
            }

            try {
                Part part = req.getPart("poster1");
                if (part.getSize()>0){
                    String fileName = Paths.get(part.getSubmittedFileName()).getFileName().toString();
                    int index = fileName.lastIndexOf(".");
                    fName = System.currentTimeMillis()+"."+index;
                    part.write(uploadPath+"/"+fName);
                    video.setPoster(fName);
                }
                else {
                    video.setPoster(poster);
                }
            }catch (FileNotFoundException e){
                e.printStackTrace();
            }
            Category category = null;
            category = categoryService.findById(Integer.parseInt(req.getParameter("selectedCategoryId")));
            if (category != null){
                video.setCategory(category);
            }
            videoService.update(video);
            resp.sendRedirect(req.getContextPath()+"/admin/videos");
        }
    }
}