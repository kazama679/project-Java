package ra.edu.business.dao.user;

import ra.edu.business.model.User;

import java.util.List;

public interface UserDAO {
    User findById(int id);
    List<User> findAll();
    boolean update(User user);
}