package com.edison.app.recipesweb.services;

import java.util.List;

import com.edison.app.recipesweb.entities.Recipe;
import com.edison.app.recipesweb.entities.User;

public interface RecipeService {

    public Recipe createRecipe(Recipe recipe, User user);
    public Recipe findRecipeById(Long id)throws Exception;
    public void deleteRecipe(Long id)throws Exception;
    public Recipe updateRecipe(Recipe recipe, Long id)throws Exception;
    public List<Recipe> findAllRecipe();
    public Recipe likeRecipe(Long recipeId, User user)throws Exception;

}
