package com.edison.app.recipesweb.controllers;

import com.edison.app.recipesweb.entities.Recipe;
import com.edison.app.recipesweb.entities.User;
import com.edison.app.recipesweb.services.RecipeService;
import com.edison.app.recipesweb.services.UserService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
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

    @PostMapping()
    public Recipe createRecipe(@RequestBody Recipe recipe, @RequestHeader("Authorization") String jwt) throws Exception{
        User user = userService.findUserByJwt(jwt);

        Recipe createdRecipe = recipeService.createRecipe(recipe, user);
        return createdRecipe;
    }

    @PutMapping("/{id}")
    public Recipe updateRecipe(@RequestBody Recipe recipe, @PathVariable Long id) throws Exception{

        Recipe updatedRecipe = recipeService.updateRecipe(recipe, id);
        return updatedRecipe;
    }
/*
    @DeleteMapping("/{recipeId}")
    public String deleteRecipe(@PathVariable Long recipeId) throws Exception{
        recipeService.deleteRecipe(recipeId);
        return "receta eliminada correctamente";
    }*/

    @DeleteMapping("/{recipeId}")
    public ResponseEntity<Map<String, String>> deleteRecipe(@PathVariable Long recipeId) throws Exception{
        recipeService.deleteRecipe(recipeId);
        Map<String, String> res = new HashMap<>();
        res.put("message", "receta eliminada correctamente");
        return ResponseEntity.ok(res);
    }

    @PutMapping("/{id}/like")
    public Recipe likeRecipe(@RequestHeader("Authorization") String jwt, @PathVariable Long id) throws Exception{
        User user = userService.findUserByJwt(jwt);
        Recipe updatedRecipe = recipeService.likeRecipe(id, user);
        return updatedRecipe;
    }

}
