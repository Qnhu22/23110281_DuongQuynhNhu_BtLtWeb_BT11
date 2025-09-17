package vn.iotstar.dao;

import vn.iotstar.entity.User;

public interface IUserDao {
    User findByUsernameAndPassword(String username, String password);
    void insert(User user);
    void update(User user);
    User findById(int id);
}