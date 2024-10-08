package vn.iostar.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import vn.iostar.configs.jpaConfig;
import vn.iostar.dao.IUserDAO;
import vn.iostar.entity.User;

import java.util.List;

public class UserDAO implements IUserDAO {
    @Override
    public boolean findByUsername(String username) {
        boolean flag = false;
        EntityManager entityManager = jpaConfig.getEntityManagerFactory();
        String jpql = "SELECT u FROM User u WHERE u.username = :username";
        try {
            TypedQuery<User> query = entityManager.createQuery(jpql, User.class);
            query.setParameter("username", username);
            List<User> users = query.getResultList(); // Sử dụng getResultList() để tránh NoResultException
            if (!users.isEmpty()) {
                flag = true; // Nếu danh sách không trống, có ít nhất một user
            }
        }catch (Exception e) {
            flag = false;
            e.printStackTrace();
        }finally {
            entityManager.close();
        }
        return flag;
    }
    @Override
    public User findUserByUsername(String username) {
        User user;
        EntityManager entityManager = jpaConfig.getEntityManagerFactory();
        String jpql = "SELECT u FROM User u WHERE u.username = :username";
        try {
            TypedQuery<User> query = entityManager.createQuery(jpql, User.class);
            query.setParameter("username", username);
            user = query.getSingleResult();
            if (user != null) {
                return user;
            }
        }catch (Exception e) {
            user = null;
            e.printStackTrace();
        }finally {
            entityManager.close();
        }
        return user;
    }
    @Override
    public boolean findByPhone(String phone) {
        EntityManager entityManager = jpaConfig.getEntityManagerFactory();
        String jpql = "SELECT u FROM User u WHERE u.phone = :phone";
        try {
            TypedQuery<User> query = entityManager.createQuery(jpql, User.class);
            query.setParameter("phone", phone);
            User user = query.getSingleResult();
            if (user != null) {
                return true;
            }
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            entityManager.close();
        }
        return false;
    }

    @Override
    public void insert(User user) {
        EntityManager entityManager = jpaConfig.getEntityManagerFactory();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(user);
            transaction.commit();
        }catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
            throw e;
        }finally {
            entityManager.close();
        }
    }

    @Override
    public void update(String username, String newPassword) {
        EntityManager entityManager = jpaConfig.getEntityManagerFactory();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            User user = findUserByUsername(username);
            if (user != null) {
                user.setPassword(newPassword);
                entityManager.merge(user);
                transaction.commit();
            }
        }catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
            throw e;
        }finally {
            entityManager.close();
        }
    }

    @Override
    public User login(String username, String password) {
        EntityManager entityManager = jpaConfig.getEntityManagerFactory();
        String jpql = "SELECT u FROM User u WHERE u.username = :username and u.password = :password";
        try {
            TypedQuery<User> query = entityManager.createQuery(jpql, User.class);
            query.setParameter("username", username);
            query.setParameter("password", password);
            User user = query.getSingleResult();
            if (user != null) {
                return user;
            }
        }catch (Exception e) {
            e.printStackTrace();
            throw e;
        }finally {
            entityManager.close();
        }
        return null;
    }
}
