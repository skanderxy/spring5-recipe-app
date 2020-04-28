package guru.springframework.converters;

import guru.springframework.commands.RecipeCommand;
import guru.springframework.domain.Category;
import guru.springframework.domain.Recipe;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

/**
 * Created by jt on 6/21/17.
 */
@Component
public class RecipeToRecipeCommand implements Converter<Recipe, RecipeCommand> {

	private final CategoryToCategoryCommand categoryConveter;
	private final IngredientToIngredientCommand ingredientConverter;
	private final NotesToNotesCommand notesConverter;

	public RecipeToRecipeCommand(CategoryToCategoryCommand categoryConveter, IngredientToIngredientCommand ingredientConverter,
								 NotesToNotesCommand notesConverter) {
		this.categoryConveter = categoryConveter;
		this.ingredientConverter = ingredientConverter;
		this.notesConverter = notesConverter;
	}

	@Synchronized
	@Nullable
	@Override
	public RecipeCommand convert(Recipe source) {
		if (source == null) {
			return null;
		}

		final RecipeCommand command = RecipeCommand.builder()
				.id(source.getId())
				.cookTime(source.getCookTime())
				.prepTime(source.getPrepTime())
				.description(source.getDescription())
				.difficulty(source.getDifficulty())
				.servings(source.getServings())
				.source(source.getSource())
				.url(source.getUrl())
				.directions(source.getDirections())
				.notes(notesConverter.convert(source.getNotes()))
				.build();

		if (source.getCategories() != null && !source.getCategories().isEmpty()) {
			source.getCategories()
					.forEach((Category category) -> command.getCategories().add(categoryConveter.convert(category)));
		}

		if (source.getIngredients() != null && !source.getIngredients().isEmpty()) {
			source.getIngredients()
					.forEach(ingredient -> command.getIngredients().add(ingredientConverter.convert(ingredient)));
		}

		return command;
	}
}