package vn.iostar.dao;

import vn.iostar.entity.Category;

import java.util.List;

public interface ICategoryDAO {
    void insert(Category category);

    void update(Category category);

    void delete(int categoryid) throws Exception;

    Category findById(int id);

    Category findByName(String name);

    List<Category> findAll();

    List<Category> findByCategoryName(String categoryName);

    List<Category> findAll(int page, int pagesize);

    int count();
}
