package services;
import model.User;

public interface UserService {
	User login(String username, String password);
    User get(String username);
    boolean checkExistEmail(String email);
    boolean checkExistUsername(String username);
    boolean checkExistPhone(String phone);
    boolean register(String username, String password, String email, String fullname, String phone);
}