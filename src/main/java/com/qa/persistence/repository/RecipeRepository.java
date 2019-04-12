package com.qa.persistence.repository;

public interface RecipeRepository {

	//C
	String createRecipe(String movie);
	
	//R
	String getAllRecipes();
	
	String getARecipe(Long id);
	

	//D
	String deleteRecipe(Long id);
	
	int cycleRecipes(String title);

}