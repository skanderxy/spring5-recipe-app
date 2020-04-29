package guru.springframework.services;

import guru.springframework.commands.RecipeCommand;
import guru.springframework.converters.RecipeCommandToRecipe;
import guru.springframework.converters.RecipeToRecipeCommand;
import guru.springframework.domain.Recipe;
import guru.springframework.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
public class RecipeServiceImpl implements RecipeService {

	private final RecipeRepository recipeRepository;
	private final RecipeCommandToRecipe recipeCommandToRecipe;
	private final RecipeToRecipeCommand recipeToRecipeCommand;

	public RecipeServiceImpl(RecipeRepository recipeRepository, RecipeCommandToRecipe recipeCommandToRecipe,
							 RecipeToRecipeCommand recipeToRecipeCommand) {
		this.recipeRepository = recipeRepository;
		this.recipeCommandToRecipe = recipeCommandToRecipe;
		this.recipeToRecipeCommand = recipeToRecipeCommand;
	}

	@Override
	public Set<Recipe> getRecipes() {
		log.debug("I'm in recipe service");

		Set<Recipe> recipes = new HashSet<>();
		recipeRepository.findAll().iterator().forEachRemaining(recipes::add);

		return recipes;
	}

	@Override
	public Recipe findById(Long l) {

		Optional<Recipe> recipeOptional = recipeRepository.findById(l);

		if (!recipeOptional.isPresent()) {
			throw new RuntimeException("Recipe Not Found!");
		}

		return recipeOptional.get();
	}

	@Override
	@Transactional
	public RecipeCommand saveRecipeCommand(RecipeCommand command) {
		Recipe detachedRecipe = recipeCommandToRecipe.convert(command);

		return Optional.ofNullable(detachedRecipe)
				.map(recipe -> {
					Recipe savedRecipe = recipeRepository.save(recipe);
					return recipeToRecipeCommand.convert(savedRecipe);
				}).orElse(null);
	}

	@Override
	@Transactional
	public RecipeCommand findRecipeCommandById(Long id) {
		final Optional<Recipe> recipeOpt = recipeRepository.findById(id);

		return recipeOpt.map(recipeToRecipeCommand::convert).orElse(null);
	}
}
