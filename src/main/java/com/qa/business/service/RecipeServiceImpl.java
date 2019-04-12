package com.qa.business.service;

import javax.inject.Inject;

import com.qa.persistence.repository.RecipeRepository;

public class RecipeServiceImpl implements RecipeService {

	@Inject
	private RecipeRepository repo;

	public String getAllRecipes() {
		return repo.getAllRecipes();
	}

	@Override
	public String addRecipe(String recipe) {
		if(recipe.contains("Bacon")) {
		
			return "{'message': 'Can't Add This Recipe'}";
		}
		return repo.createRecipe(recipe);
	}

	@Override
	public String deleteRecipe(Long id) {
		return repo.deleteRecipe(id);
	}

	public void setRepo(RecipeRepository repo) {
		this.repo = repo;
	}

	@Override
	public int cycleRecipes(String title) {

		return repo.cycleRecipes(title);

	}

	@Override
	public String getARecipe(Long id) {
		return repo.getARecipe(id);
		
	}


}
