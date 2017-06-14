package services;

import models.Category;
import repository.CategoryRepository;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by lapa on 6/14/17.
 */
public class CategoryService implements BaseService {

    private CategoryRepository categoryRepository;

    @Inject
    public void setCategoryRepository(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List getAllCategories() {
        return categoryRepository.findAll();
    }

    public Category saveCategory(Category category){
        return categoryRepository.create(category);
    }

    public Category updateCategory(Category category) {
        return categoryRepository.update(category);
    }

    public void deleteCategory(Long id) {
        Category category = categoryRepository.findById(id);
        categoryRepository.delete(category);
    }

    public Category getCategoryDetails(Long id) {
        return categoryRepository.findById(id);
    }
}
