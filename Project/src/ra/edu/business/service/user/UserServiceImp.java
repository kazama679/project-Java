package ra.edu.business.service.user;

import ra.edu.business.dao.user.UserDAO;
import ra.edu.business.dao.user.UserDAOImp;
import ra.edu.business.model.User;

import java.util.List;

public class UserServiceImp implements UserService {
    private final UserDAO userDAO;
    public UserServiceImp() {
        userDAO = new UserDAOImp();
    }

    @Override
    public List<User> findAll() {
        return userDAO.findAll();
    }

    @Override
    public boolean update(User user) {
        return userDAO.update(user);
    }

    @Override
    public User findById(int id) {
        return userDAO.findById(id);
    }
}
