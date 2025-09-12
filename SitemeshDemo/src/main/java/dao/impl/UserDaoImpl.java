package dao.impl;

import configs.JPAConfig;
import dao.UserDao;
import entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;

public class UserDaoImpl implements UserDao {
    @Override
    public User findByUsername(String username) {
        EntityManager em = JPAConfig.getEntityManager();
        try {
            return em.createQuery("SELECT u FROM User u WHERE u.username = :uname", User.class)
                    .setParameter("uname", username)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }

    @Override
    public void update(User user) {
        EntityManager em = JPAConfig.getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(user);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }
}
