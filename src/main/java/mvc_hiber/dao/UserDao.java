package mvc_hiber.dao;

import mvc_hiber.model.User;


import java.util.List;

public interface UserDao {

   // List<User> getAllUsers();

    List<User> getAllUsersDao();

    User getUserByIdDao(long id);

    boolean checkUserByNameDao(String name);

    boolean checkUserByLoginDao(String login);

    void addUserDao(User user);

    void updateUserDao(User user);

    void deleteUserByIdDao(Long id);

    User isExist(String login, String password);

}