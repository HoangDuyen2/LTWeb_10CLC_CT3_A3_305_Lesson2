package vn.iostar.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import vn.iostar.configs.jpaConfig;
import vn.iostar.dao.ICategoryDAO;
import vn.iostar.entity.Category;

import java.util.List;

public class CategoryDAO implements ICategoryDAO {
    @Override
    public void insert(Category category){
        EntityManager entityManager = jpaConfig.getEntityManagerFactory();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(category);
            transaction.commit();
        }catch (Exception e){
            e.printStackTrace();
            transaction.rollback();
            throw e;
        }finally {
            entityManager.close();
        }
    }
    @Override
    public void update(Category category){
        EntityManager entityManager = jpaConfig.getEntityManagerFactory();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.merge(category);
            transaction.commit();
        }catch (Exception e){
            e.printStackTrace();
            transaction.rollback();
            throw e;
        }finally {
            entityManager.close();
        }
    }
    @Override
    public void delete(int categoryid) throws Exception {
        EntityManager entityManager = jpaConfig.getEntityManagerFactory();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            Category category = findById(categoryid);
            if(category != null){
                entityManager.remove(category);
            }
            else {
                throw new Exception("Category not found");
            }
            transaction.commit();
        }catch (Exception e){
            e.printStackTrace();
            transaction.rollback();
            throw e;
        }finally {
            entityManager.close();
        }
    }
    @Override
    public Category findById(int id){
        EntityManager entityManager = jpaConfig.getEntityManagerFactory();
        Category category = entityManager.find(Category.class, id);
        entityManager.close();
        return category;
    }
    @Override
    public Category findByName(String name) {
        EntityManager entityManager = jpaConfig.getEntityManagerFactory();
        Category category;
        String jpql = "select c from Category c where c.categoryname = :categoryName";
        try{
            TypedQuery<Category> query = entityManager.createQuery(jpql, Category.class);
            query.setParameter("categoryName", name);
            category = query.getSingleResult();
        }catch (Exception e){
            category = null;
        }finally {
            entityManager.close();
        }
        return category;
    }

    @Override
    public List<Category> findAll(){
        EntityManager entityManager = jpaConfig.getEntityManagerFactory();
        List<Category> categoryList = entityManager.createNamedQuery("Category.findAll", Category.class).getResultList();
        entityManager.close();
        return categoryList;
    }
    @Override
    public List<Category> findByCategoryName(String categoryName){
        EntityManager entityManager = jpaConfig.getEntityManagerFactory();
        String jpql = "select c from Category c where c.categoryname like :categoryName";
        TypedQuery<Category> query = entityManager.createQuery(jpql, Category.class);
        query.setParameter("categoryName", "%"+categoryName+"%");
        List<Category> categoryList = query.getResultList();
        entityManager.close();
        return categoryList;
    }
    @Override
    public List<Category> findAll(int page, int pagesize){
        EntityManager entityManager = jpaConfig.getEntityManagerFactory();
        TypedQuery<Category> query = entityManager.createNamedQuery("Category.findAll", Category.class);
        query.setFirstResult((page - 1) * pagesize);
        query.setMaxResults(pagesize);
        List<Category> categoryList = query.getResultList();
        entityManager.close();
        return categoryList;
    }
    @Override
    public int count(){
        EntityManager entityManager = jpaConfig.getEntityManagerFactory();
        String jpql = "select count(*) from Category";
        TypedQuery<Long> query = entityManager.createQuery(jpql, Long.class);
        Long count = query.getSingleResult();
        entityManager.close();
        return count.intValue();
    }
}
