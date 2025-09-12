package dao;

import entity.User;

public interface UserDao {
    User findByUsername(String username);
    void update(User user);
}
