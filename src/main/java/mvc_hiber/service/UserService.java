package mvc_hiber.service;


import mvc_hiber.model.User;

import java.util.List;


public interface UserService {
    void saveUser(User user);

    User getUserById(long id);

    List<User> getAllUsers();

    void editUser(User user);

    void deleteUser(long id);

    User findUserByName(String name);



}
