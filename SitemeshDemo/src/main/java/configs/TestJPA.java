package configs;

import entity.User;
import jakarta.persistence.EntityManager;

public class TestJPA {
    public static void main(String[] args) {
        System.out.println(">>> Bắt đầu test JPA...");

        EntityManager em = JPAConfig.getEntityManager();

        try {
            em.getTransaction().begin();

            // Tạo user admin mặc định
            User admin = new User();
            admin.setUsername("admin");
            admin.setPassword("123456");
            admin.setFullname("Quản trị viên");
            admin.setPhone("0123456789");

            em.persist(admin);

            em.getTransaction().commit();
            System.out.println(">>> Insert user thành công!");
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }

        System.out.println(">>> Kết thúc test JPA.");
    }
}
