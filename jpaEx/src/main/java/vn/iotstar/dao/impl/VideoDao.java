package vn.iotstar.dao.impl;

import java.util.List;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import vn.iotstar.configs.JPAConfig;
import vn.iotstar.dao.IVideoDao;
import vn.iotstar.entity.Video;

public class VideoDao implements IVideoDao {

    @Override
    public void insert(Video video) {
        EntityManager em = JPAConfig.getEntityManager();
        EntityTransaction trans = em.getTransaction();
        try {
            trans.begin();
            em.persist(video);
            trans.commit();
        } catch (Exception e) {
            trans.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    @Override
    public void update(Video video) {
        EntityManager em = JPAConfig.getEntityManager();
        EntityTransaction trans = em.getTransaction();
        try {
            trans.begin();
            em.merge(video);
            trans.commit();
            System.out.println("✅ Cập nhật video: " + video.getVideoId());
        } catch (Exception e) {
            if (trans.isActive()) trans.rollback();
            System.err.println("❌ Lỗi khi cập nhật video: " + e.getMessage());
            e.printStackTrace();
        } finally {
            em.close();
        }
    }


    @Override
    public void delete(int videoId) throws Exception {
        EntityManager em = JPAConfig.getEntityManager();
        EntityTransaction trans = em.getTransaction();
        try {
            trans.begin();
            Video v = em.find(Video.class, videoId);
            if (v != null) {
                em.remove(v);
            } else {
                throw new Exception("Không tìm thấy video với ID: " + videoId);
            }
            trans.commit();
        } catch (Exception e) {
            trans.rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    @Override
    public Video findById(int videoId) {
        EntityManager em = JPAConfig.getEntityManager();
        try {
            return em.find(Video.class, videoId);
        } finally {
            em.close();
        }
    }

    @Override
    public List<Video> findAll() {
        EntityManager em = JPAConfig.getEntityManager();
        try {
            TypedQuery<Video> query = em.createNamedQuery("Video.findAll", Video.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public List<Video> findByUserId(int userId) {
        EntityManager em = JPAConfig.getEntityManager();
        try {
            TypedQuery<Video> query = em.createNamedQuery("Video.findByUserId", Video.class);
            query.setParameter("userId", userId);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
    
    @Override
    public List<Video> search(String keyword) {
        EntityManager em = JPAConfig.getEntityManager();
        try {
            String jpql = "SELECT v FROM Video v WHERE v.title LIKE :kw OR v.description LIKE :kw";
            return em.createQuery(jpql, Video.class)
                     .setParameter("kw", "%" + keyword + "%")
                     .getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public List<Video> searchByUserId(int userId, String keyword) {
        EntityManager em = JPAConfig.getEntityManager();
        try {
            String jpql = "SELECT v FROM Video v WHERE v.user.userId = :uid AND (v.title LIKE :kw OR v.description LIKE :kw)";
            return em.createQuery(jpql, Video.class)
                     .setParameter("uid", userId)
                     .setParameter("kw", "%" + keyword + "%")
                     .getResultList();
        } finally {
            em.close();
        }
    }

}
