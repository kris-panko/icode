package com.fdmgroup.icode.recipes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/recipes")
public class RecipesController {

    private final RecipesService recipesService;

    @Autowired
    public RecipesController(RecipesService recipesService) {
        this.recipesService = recipesService;
    }

    @GetMapping
    public String listRecipes(Model model) {
        model.addAttribute("recipes", recipesService.getAllRecipes());
        return "getAll"; // Thymeleaf template name for listing recipes
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("recipe", new Recipe());
        return "add"; // Thymeleaf template name for adding a recipe
    }

    @PostMapping("/add")
    public String addRecipe(@ModelAttribute Recipe recipe, RedirectAttributes redirectAttributes) {
        recipesService.addRecipe(recipe);
        redirectAttributes.addFlashAttribute("successMessage", "Recipe added successfully!");
        return "redirect:/recipes";
    }



    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") int id, Model model) {
        Recipe recipe = recipesService.getRecipe(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid recipe Id:" + id));
        model.addAttribute("recipe", recipe);
        return "recipes/update";
    }

    @PostMapping("/update/{id}")
    public String updateRecipe(@PathVariable("id") int id, Recipe recipe) {
        recipe.setId(id);
        recipesService.updateRecipe(recipe);
        return "redirect:/recipes";
    }

    @GetMapping("/delete/{id}")
    public String deleteRecipe(@PathVariable("id") int id) {
        recipesService.deleteRecipe(id);
        return "redirect:/recipes";
    }

    @GetMapping("/{id}")
    public String showRecipeDetails(@PathVariable("id") int id, Model model) {
        Recipe recipe = recipesService.getRecipe(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid recipe Id:" + id));
        model.addAttribute("recipe", recipe);
        return "recipes/get";
    }
}

