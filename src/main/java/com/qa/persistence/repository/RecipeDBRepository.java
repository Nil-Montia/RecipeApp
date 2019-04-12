
package com.qa.persistence.repository;

import static javax.transaction.Transactional.TxType.REQUIRED;
import static javax.transaction.Transactional.TxType.SUPPORTS;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import com.qa.domain.Recipe;
import com.qa.util.JSONUtil;

@Transactional(SUPPORTS)
@Default
public class RecipeDBRepository implements RecipeRepository {

	@PersistenceContext(unitName = "primary")
	private EntityManager manager;

	@Inject
	private JSONUtil util;

	@Override
	public int cycleRecipes(String title) {

		Query query = manager.createQuery("Select a FROM Recipe a");
		Collection<Recipe> recipes = (Collection<Recipe>) query.getResultList();

		List<Recipe> validList = recipes.stream().filter(n -> n.getName().equals(title)).collect(Collectors.toList());

		return validList.size();
	}

	@Override
	public String getAllRecipes() {
		Query query = manager.createQuery("Select m FROM Recipe m");
		Collection<Recipe> recipes =  (Collection<Recipe>) query.getResultList();
		
//		manager.find(Recipe.class, 1L);

		return util.getJSONForObject(recipes);
	}

	@Override
	@Transactional(REQUIRED)
	public String createRecipe(String recipe) {
		Recipe aRecipe = util.getObjectForJSON(recipe, Recipe.class);
		manager.persist(aRecipe);
		return "{\"message\": \"recipe has been sucessfully added\"}";
	}

	@Override
	@Transactional(REQUIRED)
	public String deleteRecipe(Long id) {
		Recipe recipeInDB = util.getObjectForJSON(getARecipe(id), Recipe.class);

		if (manager.contains(manager.find(Recipe.class, id))) {

			manager.remove(manager.find(Recipe.class, id));
		}
		return "{\"message\": \"recipe sucessfully deleted\"}";
	}

	@Override
	public String getARecipe(Long id) {
		return util.getJSONForObject(manager.find(Recipe.class, id));
	}

	public void setManager(EntityManager manager) {
		this.manager = manager;
	}

	public void setUtil(JSONUtil util) {
		this.util = util;
	}

	
}
