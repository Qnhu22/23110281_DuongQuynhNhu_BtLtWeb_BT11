package dao;
import model.User;
public interface UserDao {
	User get(String username);
	User getByEmail(String email);
	boolean checkExistEmail(String email);
    boolean checkExistUsername(String username);
    boolean checkExistPhone(String phone);
    boolean insert(User user);
    void updatePassword(String email, String newPassword);
}