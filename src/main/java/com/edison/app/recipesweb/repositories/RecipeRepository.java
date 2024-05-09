package com.edison.app.recipesweb.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.edison.app.recipesweb.entities.Recipe;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {

}
