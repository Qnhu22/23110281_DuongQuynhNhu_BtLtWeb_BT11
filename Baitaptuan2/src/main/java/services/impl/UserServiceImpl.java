package services.impl;

import dao.UserDao;
import dao.impl.UserDaoImpl;
import model.User;
import services.UserService;


public class UserServiceImpl implements UserService{
	UserDao userDao = new UserDaoImpl();

    @Override
    public User login(String username, String password) {
        User user = this.get(username);
        if (user != null && password.equals(user.getPassWord())) {
            return user;
        }
        return null;
    }

    @Override
    public User get(String username) {
        return userDao.get(username);
    }
    
    @Override
    public boolean checkExistEmail(String email) {
        return userDao.checkExistEmail(email);
    }

    @Override
    public boolean checkExistUsername(String username) {
        return userDao.checkExistUsername(username);
    }

    @Override
    public boolean checkExistPhone(String phone) {
        return userDao.checkExistPhone(phone);
    }

    @Override
    public boolean register(String username, String password, String email, String fullname, String phone) {
        long millis = System.currentTimeMillis();
        java.sql.Date createdDate = new java.sql.Date(millis);
        User user = new User();
        user.setUserName(username);
        user.setPassWord(password);
        user.setEmail(email);
        user.setFullName(fullname);
        user.setPhone(phone);
        user.setCreatedDate(createdDate);
        user.setRoleid(3);
        return userDao.insert(user);
    }
    
}