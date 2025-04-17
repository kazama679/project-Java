package ra.edu.business.dao.user;

import ra.edu.business.dao.AppDAO;
import ra.edu.business.model.User;

import java.util.List;

public interface UserDAO extends AppDAO<User> {
    List<User> findAll();
}
