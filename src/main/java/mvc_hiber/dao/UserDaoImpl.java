package mvc_hiber.dao;

import mvc_hiber.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;


@Repository
public class UserDaoImpl implements UserDao {

  //  @PersistenceContext
  //  EntityManager entityManager;

    @Autowired
     SessionFactory sessionFactory;
   // SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();


    @Override
    public void saveUser(User user) {
        sessionFactory.getCurrentSession().saveOrUpdate(user);
        //entityManager.persist(user);
    }

    @Override
    public User getUserById(long id) {

       // return entityManager.find(User.class, id);
        return sessionFactory.getCurrentSession().get(User.class, id);
    }

    @Override
    public List<User> getAllUsers() {
       // return entityManager.createQuery("select e from User e", User.class).getResultList();
        return sessionFactory.getCurrentSession().createQuery("FROM User", User.class).getResultList();
    }
    @Override
    public User findByUsername(String username) {

        //   return entityManager.find(User.class, name);
        // return (User) session.load(User.class, name);
/*
        System.out.println("List<User> users = new ArrayList<>();");
        List<User> users = new ArrayList<>();
        System.out.println("List<User> users = " + users);
        users = sessionFactory.getCurrentSession().createQuery("from User where username=?").setParameter(0, username).list();
        System.out.println("List<User> users = " + users);
        if (users.size() > 0) {
            return users.get(0);
        } else {
            return null;
        }
 */
        String hql ="FROM User WHERE name=:name";
        Query query = sessionFactory.getCurrentSession().createQuery(hql).setParameter("name",username);// здесь SessionFactory-null
        return (User) query.getSingleResult();
    }


    @Override
    public void editUser(User user) {

        saveUser(user);
        //entityManager.merge(user);
    }

    @Override
    public void deleteUser(long id) {
      /*  User userToBeDeleted = entityManager.getReference(User.class, id);

        if (userToBeDeleted != null) {
            entityManager.remove(userToBeDeleted);
        }
  */
        sessionFactory.getCurrentSession().delete(getUserById(id));
    }

}
