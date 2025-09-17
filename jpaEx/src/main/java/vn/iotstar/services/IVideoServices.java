package vn.iotstar.services;

import java.util.List;
import vn.iotstar.entity.Video;

public interface IVideoServices {
    void insert(Video video);
    void update(Video video);
    void delete(int videoId) throws Exception;
    Video findById(int videoId);
    List<Video> findAll();
    List<Video> findByUserId(int userId);
    List<Video> search(String keyword);
    List<Video> searchByUserId(int userId, String keyword);

}
