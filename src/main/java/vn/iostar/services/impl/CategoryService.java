package vn.iostar.services.impl;

import vn.iostar.dao.ICategoryDAO;
import vn.iostar.dao.impl.CategoryDAO;
import vn.iostar.entity.Category;
import vn.iostar.services.ICategoryService;

import java.util.List;

public class CategoryService implements ICategoryService {
    ICategoryDAO categoryDAO = new CategoryDAO();
    @Override
    public void insert(Category category) {
        if(categoryDAO.findByName(category.getCategoryname()) == null){
            categoryDAO.insert(category);
        }
    }

    @Override
    public void update(Category category) {
        if(categoryDAO.findByName(category.getCategoryname()) != null){
            categoryDAO.update(category);
        }
    }

    @Override
    public void delete(int categoryid) throws Exception {
        categoryDAO.delete(categoryid);
    }

    @Override
    public Category findById(int id) {
        return categoryDAO.findById(id);
    }

    @Override
    public List<Category> findAll() {
        return categoryDAO.findAll();
    }
    @Override
    public Category findByName(String name) {
        return categoryDAO.findByName(name);
    }
    @Override
    public List<Category> findByCategoryName(String categoryName) {
        return categoryDAO.findByCategoryName(categoryName);
    }

    @Override
    public List<Category> findAll(int page, int pagesize) {
        return categoryDAO.findAll(page, pagesize);
    }

    @Override
    public int count() {
        return categoryDAO.count();
    }
}
