package mvc_hiber.dao;

import mvc_hiber.model.User;


import java.util.List;


public interface UserDao {
    void saveUser(User user);

    User getUserById(long id);

    List<User> getAllUsers();

    void editUser(User user);

    User findById(long id);
    //User findByUsername(String username);

    void deleteUser(long id);
}