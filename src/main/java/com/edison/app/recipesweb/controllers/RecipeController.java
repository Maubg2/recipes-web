package com.edison.app.recipesweb.controllers;

import com.edison.app.recipesweb.entities.Recipe;
import com.edison.app.recipesweb.entities.User;
import com.edison.app.recipesweb.services.RecipeService;
import com.edison.app.recipesweb.services.UserService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/recipes")
public class RecipeController {

    @Autowired
    private RecipeService recipeService;

    @Autowired
    private UserService userService;

    @GetMapping()
    public List<Recipe> getAllRecipes() throws Exception{
        List<Recipe> recipes = recipeService.findAllRecipe();
        return recipes;
    }

    @PostMapping("/user/{userId}")
    public Recipe createRecipe(@RequestBody Recipe recipe, @PathVariable Long userId) throws Exception{
        User user = userService.findUserById(userId);

        Recipe createdRecipe = recipeService.createRecipe(recipe, user);
        return createdRecipe;
    }

    @PutMapping("/{id}")
    public Recipe updateRecipe(@RequestBody Recipe recipe, @PathVariable Long id) throws Exception{

        Recipe updatedRecipe = recipeService.updateRecipe(recipe, id);
        return updatedRecipe;
    }

    @DeleteMapping("/{recipeId}")
    public String deleteRecipe(@PathVariable Long recipeId) throws Exception{
        recipeService.deleteRecipe(recipeId);
        return "Receta eliminada correctamente";
    }

    @PutMapping("/{id}/like/user/{userId}")
    public Recipe likeRecipe(@PathVariable Long userId, @PathVariable Long id) throws Exception{
        User user = userService.findUserById(userId);
        Recipe updatedRecipe = recipeService.likeRecipe(id, user);
        return updatedRecipe;
    }

}
