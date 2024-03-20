package com.fdmgroup.icode.recipes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class RecipesService {

    private final RecipesRepository recipesRepository;

    @Autowired
    public RecipesService(RecipesRepository recipesRepository) {
        this.recipesRepository = recipesRepository;
    }

    public List<Recipe> getAllRecipes() {
        return recipesRepository.findAll();
    }

    public Optional<Recipe> getRecipe(Integer id) {
        return recipesRepository.findById(id);
    }

    public Recipe addRecipe(Recipe recipe) {
        return recipesRepository.save(recipe);
    }

    public Recipe updateRecipe(Recipe recipe) {
        return recipesRepository.save(recipe);
    }

    public void deleteRecipe(Integer id) {
        recipesRepository.deleteById(id);
    }
}
