package vn.iotstar.services.impl;

import java.util.List;
import vn.iotstar.dao.IVideoDao;
import vn.iotstar.dao.impl.VideoDao;
import vn.iotstar.entity.Video;
import vn.iotstar.services.IVideoServices;

public class VideoServices implements IVideoServices {
    IVideoDao videoDao = new VideoDao();

    @Override
    public void insert(Video video) {
        videoDao.insert(video);
    }

    @Override
    public void update(Video video) {
        videoDao.update(video);
    }

    @Override
    public void delete(int videoId) throws Exception {
        videoDao.delete(videoId);
    }

    @Override
    public Video findById(int videoId) {
        return videoDao.findById(videoId);
    }

    @Override
    public List<Video> findAll() {
        return videoDao.findAll();
    }

    @Override
    public List<Video> findByUserId(int userId) {
        return videoDao.findByUserId(userId);
    }
    
    @Override
    public List<Video> search(String keyword) {
        return videoDao.search(keyword);
    }

    @Override
    public List<Video> searchByUserId(int userId, String keyword) {
        return videoDao.searchByUserId(userId, keyword);
    }

}
