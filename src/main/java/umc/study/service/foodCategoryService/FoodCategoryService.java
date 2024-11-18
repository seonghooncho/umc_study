package umc.study.service.foodCategoryService;

import java.util.List;

public interface FoodCategoryService {
    public boolean checkCategoriesExist(List<Long> categoryIds);
}
