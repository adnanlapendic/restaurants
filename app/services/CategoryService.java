package services;

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
}
