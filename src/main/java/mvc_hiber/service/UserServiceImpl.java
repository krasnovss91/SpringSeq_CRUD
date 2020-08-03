package mvc_hiber.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import mvc_hiber.model.User;
import mvc_hiber.dao.UserDao;

import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {

    private UserDao userDao;


    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }


    @Override
    @Transactional
    public List<User> getAllUsers() {
        return this.userDao.getAllUsers();
    }
}