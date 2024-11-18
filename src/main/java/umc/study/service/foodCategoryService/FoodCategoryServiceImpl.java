package umc.study.service.foodCategoryService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.study.repository.FoodCategoryRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FoodCategoryServiceImpl implements FoodCategoryService {
    private final FoodCategoryRepository foodCategoryRepository;

    @Override
    public boolean checkCategoriesExist(List<Long> categoryIds) {
        return categoryIds.stream()
                .allMatch(foodCategoryRepository::existsById);
    }
}
