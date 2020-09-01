package mvc_hiber.dao;

import mvc_hiber.model.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    EntityManager entityManager;


    @Override
    public void saveUser(User user) {
        entityManager.getTransaction().begin();
        User user1 = entityManager.merge(user);
        entityManager.getTransaction().commit();

    }

    @Override
    public User getUserById(long id) {

     //   return entityManager.createQuery("select e from User e where  e.id =:id", User.class).setParameter("id", id).getSingleResult();
      //  EntityManager entityManager = entityManagerFactory.createEntityManager();
       // EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        User user = entityManager.find(User.class, new Integer((int)id));
        entityManager.getTransaction().commit();
        entityManager.close();
        return user;

    }

    @Override
    public List<User> getAllUsers() {

        return entityManager.createQuery("select e from User e", User.class).getResultList();
    }

    @Override
    public void editUser(User user) {

        entityManager.getTransaction().begin();
        entityManager.merge(user);
        entityManager.getTransaction().commit();
        entityManager.close();

    }

    @Override
    public void deleteUser(User user) {
        entityManager.remove(user);

    }
}