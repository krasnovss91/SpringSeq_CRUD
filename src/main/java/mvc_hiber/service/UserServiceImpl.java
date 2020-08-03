package mvc_hiber.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import mvc_hiber.model.User;
import mvc_hiber.dao.UserDao;

import java.sql.SQLException;
import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {

    private UserDao userDao;

    private static UserService userService;

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }


    @Override
    @Transactional
  /*  public List<User> getAllUsers() {
        return this.userDao.getAllUsersDao();
    } */

    public static UserService getInstance() {
        if (userService == null) {
            userService = new UserService(new UserDaoFactory().getUserDao());
        }
        return userService;
    }


    public User getUserById(Long id) {
        User user = null;
        try {
            user = userDao.getUserByIdDao(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public List<User> getAllUsers() {
        return userDao.getAllUsersDao();
    }

    public boolean checkUserByName(String name) {

        try {
            userDao.checkUserByNameDao(name);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean checkUserByLogin(String login) {

        try {
            return userDao.checkUserByLoginDao(login);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void addUser(User user) {
        if (user.getName() != null && user.getName().length() > 0
                && user.getLogin() != null && user.getLogin().length() > 0
                && user.getPassword() != null && user.getPassword().length() > 0)

            userDao.addUserDao(user);
    }


    public void deleteUserById(Long id) {

        try {
            userDao.deleteUserByIdDao(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateUser(User user) {
        if (user.getName() != null && user.getName().length() > 0
                && user.getLogin() != null && user.getLogin().length() > 0
                && user.getPassword() != null && user.getPassword().length() > 0) {

            userDao.updateUserDao(user);
        }
    }

    public User isExist(String login, String password) {
        User user = null;
        user = userDao.isExist(login, password);
        return user;
    }
}