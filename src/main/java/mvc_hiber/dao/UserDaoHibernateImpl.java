package mvc_hiber.dao;


import mvc_hiber.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

@Repository
public class UserDaoHibernateImpl implements UserDao {


    private SessionFactory sessionFactory;


    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    @Override
    public List<User> getAllUsers() {

        Session session = this.sessionFactory.getCurrentSession();
        List<User> allUsers = session.createQuery("from User").list();
        return allUsers;

    }

}