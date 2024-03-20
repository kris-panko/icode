package com.fdmgroup.icode.recipes;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipesRepository extends JpaRepository<Recipe, Integer> {
}

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class RecipesRepo {
    private final Map<Integer, Recipe> recipes = new HashMap<>();
    
    public List<Recipe> findAll() {
        return new ArrayList<>(recipes.values());
    }
    
    public Optional<Recipe> findById(int id) {
        return Optional.ofNullable(recipes.get(id));
    }
    
    public void save(Recipe recipe) {
        recipes.put(recipe.getId(), recipe);
    }
    
    public void delete(int id) {
        recipes.remove(id);
    }
}
