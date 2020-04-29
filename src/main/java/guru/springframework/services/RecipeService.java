package guru.springframework.services;

import guru.springframework.commands.RecipeCommand;
import guru.springframework.domain.Recipe;

import java.util.Set;

public interface RecipeService {
	Set<Recipe> getRecipes();

	Recipe findById(Long lid);

	RecipeCommand saveRecipeCommand(RecipeCommand command);

	RecipeCommand findRecipeCommandById(Long id);
}
