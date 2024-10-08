package vn.iostar.services;

import vn.iostar.entity.User;

public interface IUserService {
    boolean findByUsername(String username);
    boolean findByPhone(String phone);
    User findUserByUsername(String username);
    boolean insert(User user);
    void update(String username, String newPassword);
    User login(String username, String password);
}
