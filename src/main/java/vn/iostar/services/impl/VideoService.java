package vn.iostar.services.impl;

import vn.iostar.dao.IVideoDAO;
import vn.iostar.dao.impl.VideoDAO;
import vn.iostar.entity.Video;
import vn.iostar.services.IVideoService;

import java.util.List;

public class VideoService implements IVideoService {
    IVideoDAO videoDAO = new VideoDAO();
    @Override
    public void insert(Video video){
        if (videoDAO.findById(video.getVideoid()) == null) {
            videoDAO.insert(video);
        }
    }
    @Override
    public List<Video> getAllVideosByCategory(String categoryId){
        return videoDAO.getAllVideosByCategory(categoryId);
    }

    @Override
    public void update(Video video){
        if (videoDAO.findById(video.getVideoid()) != null) {
            videoDAO.update(video);
        }
    }

    @Override
    public void delete(int videoId){
        if (videoDAO.findById(videoId) != null) {
            videoDAO.delete(videoId);
        }
    }

    @Override
    public Video findById(int videoId){
        return videoDAO.findById(videoId);
    }
}
