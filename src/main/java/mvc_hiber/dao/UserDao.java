package mvc_hiber.dao;

import mvc_hiber.model.User;


import java.util.List;

public interface UserDao {

    List<User> getAllUsers();
}