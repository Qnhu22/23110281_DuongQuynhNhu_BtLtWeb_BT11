package services;

import entity.User;

public interface UserService {
    User login(String username, String password);
    User findByUsername(String username);
    void update(User user);
}
