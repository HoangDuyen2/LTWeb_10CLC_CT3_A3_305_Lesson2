package vn.iostar.services;

import vn.iostar.entity.Video;

import java.util.List;

public interface IVideoService {
    void insert(Video video);

    List<Video> getAllVideos();

    void update(Video video);

    void delete(int videoId);

    Video findById(int videoId);
}
