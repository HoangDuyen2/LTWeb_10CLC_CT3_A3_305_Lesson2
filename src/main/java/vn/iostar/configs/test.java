package vn.iostar.configs;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class test {
    public static void main(String[] args) {
        EntityManager entityManager = jpaConfig.getEntityManagerFactory();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            transaction.commit();
        }catch(Exception e) {
            e.printStackTrace();
            transaction.rollback();
            throw e;
        }finally {
            entityManager.close();
        }
    }
}
