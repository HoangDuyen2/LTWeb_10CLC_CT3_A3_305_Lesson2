package vn.iostar.controllers.Admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.iostar.entity.Category;
import vn.iostar.entity.Video;
import vn.iostar.services.IVideoService;
import vn.iostar.services.impl.VideoService;

import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/admin/videos","/admin/video/add","/admin/video/insert",
        "/admin/video/edit","/admin/video/upload","/admin/video/delete"})
public class VideoController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    IVideoService videoService = new VideoService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = req.getRequestURI();
        if(url.contains("/admin/videos")){
            Category category = new Category();
            List<Video> videoList = videoService.getAllVideos();
            req.setAttribute("videoList", videoList);
            req.getRequestDispatcher("/views/admin/video-list.jsp").forward(req, resp);
        }
        else if(url.contains("/admin/video/add")){
            req.getRequestDispatcher("/views/admin/video-add.jsp").forward(req, resp);
        }
        else if(url.contains("/admin/video/edit")){
            int id = Integer.parseInt(req.getParameter("id"));
            Video video = videoService.findById(id);
            req.setAttribute("video", video);
            req.getRequestDispatcher("/views/admin/video-edit.jsp").forward(req, resp);
        }
        else if(url.contains("/admin/video/delete")){
            int id = Integer.parseInt(req.getParameter("id"));
            Video video = videoService.findById(id);
            if(video != null){
                videoService.delete(video.getVideoid());
            }
            resp.sendRedirect(req.getContextPath()+"/admin/videos");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = req.getRequestURI();
        if(url.contains("/admin/video/insert")){
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
            int title = Integer.parseInt(req.getParameter("title"));
            int views = Integer.parseInt(req.getParameter("views"));

            Video video = new Video();
            video.setActive(active);
            video.setDescription(description);
            video.setPoster(poster);
            video.setTitle(title);
            video.setViews(views);

            videoService.insert(video);

            resp.sendRedirect(req.getContextPath()+"/admin/videos");
        }
        else if(url.contains("/admin/video/upload")){
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
            int title = Integer.parseInt(req.getParameter("title"));
            int views = Integer.parseInt(req.getParameter("views"));
            Video video = videoService.findById(id);
            video.setActive(active);
            video.setDescription(description);
            video.setPoster(poster);
            video.setTitle(title);
            video.setViews(views);

            videoService.update(video);
            resp.sendRedirect(req.getContextPath()+"/admin/videos");
        }
    }
}
