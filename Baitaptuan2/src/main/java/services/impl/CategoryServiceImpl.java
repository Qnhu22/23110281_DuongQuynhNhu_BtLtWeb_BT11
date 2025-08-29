package services.impl;

import dao.CategoryDao;
import dao.impl.CategoryDaoImpl;
import model.Category;
import services.CategoryService;

import java.util.List;

public class CategoryServiceImpl implements CategoryService {
    CategoryDao dao = new CategoryDaoImpl();

    @Override
    public void insert(Category category) {
        dao.insert(category);
    }

    @Override
    public void update(Category category) {
        dao.update(category);
    }

    @Override
    public void delete(int cateId) {
        dao.delete(cateId);
    }

    @Override
    public Category get(int cateId) {
        return dao.get(cateId);
    }

    @Override
    public List<Category> getAllByUser(int userId) {
        return dao.getAllByUser(userId);
    }
}