package vn.iostar.dao;

import vn.iostar.entity.User;

public interface IUserDAO {
    boolean findByUsername(String username);
    User findUserByUsername(String username);
    boolean findByPhone(String phone);
    void insert(User user);
    void update(String username, String newPassword);
    User login(String username, String password);
}
