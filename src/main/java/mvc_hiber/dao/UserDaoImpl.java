package mvc_hiber.dao;

import mvc_hiber.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    EntityManager entityManager;
    SessionFactory sessionFactory;


    @Override
    public void saveUser(User user) {
        entityManager.persist(user);
    }

    @Override
    public User getUserById(long id) {

        return entityManager.find(User.class, id);

    }

    @Override
    public List<User> getAllUsers() {
        return entityManager.createQuery("select e from User e", User.class).getResultList();
    }
    @Override
    public User findById(long id) {

       // return entityManager.find(User.class, username);
     Session session = this.sessionFactory.getCurrentSession();
        return (User) session.get(User.class, id);

       /*   System.out.println("List<User> users = new ArrayList<>();");
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
    }


    @Override
    public void editUser(User user) {
        entityManager.merge(user);
    }

    @Override
    public void deleteUser(long id) {
        User userToBeDeleted = entityManager.getReference(User.class, id);

        if (userToBeDeleted != null) {
            entityManager.remove(userToBeDeleted);
        }

    }

}