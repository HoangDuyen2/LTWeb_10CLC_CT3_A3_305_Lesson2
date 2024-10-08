package vn.iostar.services;

import vn.iostar.entity.Category;

import java.util.List;

public interface ICategoryService {
    void insert(Category category);

    void update(Category category);

    void delete(int categoryid) throws Exception;

    Category findById(int id);

    List<Category> findAll();

    Category findByName(String name);

    List<Category> findByCategoryName(String categoryName);

    List<Category> findAll(int page, int pagesize);

    int count();
}
