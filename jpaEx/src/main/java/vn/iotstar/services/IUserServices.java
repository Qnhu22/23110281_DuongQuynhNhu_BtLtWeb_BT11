package vn.iotstar.services;

import vn.iotstar.entity.User;

public interface IUserServices {
    User login(String username, String password);
    void insert(User user);
    User findById(int id);
    void update(User user);
}