package mvc_hiber.service;


import mvc_hiber.model.User;

import java.util.List;


public interface UserService {

    List<User> getAllUsers();
    //static UserService getInstance();
    boolean checkUserByName(String name);
    User getUserById(Long id);
    boolean checkUserByLogin(String login);
    void addUser(User user);
    void deleteUserById(Long id);
    void updateUser(User user);
    User isExist(String login, String password);
}