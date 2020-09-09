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