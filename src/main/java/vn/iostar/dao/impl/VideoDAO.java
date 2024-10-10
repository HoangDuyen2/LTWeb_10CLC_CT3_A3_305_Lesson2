package vn.iostar.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import vn.iostar.configs.jpaConfig;
import vn.iostar.dao.IVideoDAO;
import vn.iostar.entity.Video;

import java.util.ArrayList;
import java.util.List;

public class VideoDAO implements IVideoDAO {
    @Override
    public List<Video> getAllVideos() {
        List<Video> videos = new ArrayList<Video>();
        EntityManager entityManager = jpaConfig.getEntityManagerFactory();
        try {
            videos = entityManager.createNamedQuery("Videos.findAll").getResultList();
            return videos;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }finally {
            entityManager.close();
        }
    }
    @Override
    public void insert(Video video){
        EntityManager entityManager = jpaConfig.getEntityManagerFactory();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(video);
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
    public void update(Video video) {
        EntityManager entityManager = jpaConfig.getEntityManagerFactory();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            video = entityManager.merge(video);
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
    public void delete(int videoId) {
        EntityManager entityManager = jpaConfig.getEntityManagerFactory();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.remove(entityManager.find(Video.class, videoId));
            transaction.commit();
        }catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
            throw e;
        }
        finally {
            entityManager.close();
        }
    }
    @Override
    public Video findById(int videoId) {
        Video video;
        EntityManager entityManager = jpaConfig.getEntityManagerFactory();
        try {
            video = entityManager.find(Video.class, videoId);
            if (video != null) {
                return video;
            }
        }catch (Exception e) {
            video = null;
            e.printStackTrace();
        }finally {
            entityManager.close();
        }
        return video;
    }
}
