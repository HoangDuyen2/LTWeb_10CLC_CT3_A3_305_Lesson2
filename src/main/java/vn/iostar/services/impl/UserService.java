package vn.iostar.services.impl;

import vn.iostar.dao.IUserDAO;
import vn.iostar.dao.impl.UserDAO;
import vn.iostar.entity.User;
import vn.iostar.services.IUserService;

public class UserService implements IUserService {
    IUserDAO userDAO = new UserDAO();
    @Override
    public boolean findByUsername(String username) {
        return userDAO.findByUsername(username);
    }

    @Override
    public boolean findByPhone(String phone) {
        return userDAO.findByPhone(phone);
    }

    @Override
    public User findUserByUsername(String username) {
        return userDAO.findUserByUsername(username);
    }

    @Override
    public boolean insert(User user) {
        if(!userDAO.findByPhone(user.getPhone())&&!userDAO.findByUsername(user.getUsername())){
            userDAO.insert(user);
            return true;
        }
        return false;
    }

    @Override
    public void update(String username, String newPassword) {
        if (userDAO.findByUsername(username)){
            userDAO.update(username, newPassword);
        }
    }

    @Override
    public User login(String username, String password) {
        return userDAO.login(username, password);
    }
}
