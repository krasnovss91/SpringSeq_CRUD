package mvc_hiber.dao;

import mvc_hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.util.List;


@Repository
public  class UserDaoImpl implements UserDao {
    // private SessionFactory sessionFactory;
    public EntityManager entityManager = Persistence.createEntityManagerFactory("COLIBRI").createEntityManager();

    //   @Autowired
    //  public UserDAOImpl(SessionFactory sessionFactory) {
    //   this.sessionFactory = sessionFactory;
    //  }

    @Override
    public void saveUser(User user) {
        //sessionFactory.getCurrentSession().saveOrUpdate(user);
        entityManager.getTransaction().begin();
        User user1 = entityManager.merge(user);
        entityManager.getTransaction().commit();

    }

    @Override
    public User getUserById(long id) {
        //return sessionFactory.getCurrentSession().get(User.class, id);
        return entityManager.createQuery("select e from User e where  e.id =:id",User.class).setParameter("id",id).getSingleResult();
    }

    @Override
    public List<User> getAllUsers() {
        // return sessionFactory.getCurrentSession().createQuery("FROM User", User.class).getResultList();
        return entityManager.createQuery("select e from User e",User.class).getResultList(); }

    @Override
    public void editUser(User user) {
        entityManager.getTransaction().begin();;
        entityManager.merge(user);
        entityManager.getTransaction().commit();
        //saveUser(user);
    }

    @Override
    public void deleteUser(User user) {
        entityManager.remove(user);
        //sessionFactory.getCurrentSession().delete(user);
    }
}