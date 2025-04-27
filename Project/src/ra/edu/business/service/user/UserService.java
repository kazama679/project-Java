package ra.edu.business.service.user;

import ra.edu.business.model.User;

import java.util.List;

public interface UserService {
    User findById(int id);
    List<User> findAll();
    boolean update(User user);
}
