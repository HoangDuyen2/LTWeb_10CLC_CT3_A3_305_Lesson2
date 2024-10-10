package vn.iostar.dao;

import vn.iostar.entity.Video;

import java.util.List;

public interface IVideoDAO {
    List<Video> getAllVideos();

    void insert(Video video);

    void update(Video video);

    void delete(int videoId);

    Video findById(int videoId);
}
