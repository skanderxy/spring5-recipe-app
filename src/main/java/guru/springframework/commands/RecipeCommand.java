package guru.springframework.commands;

import guru.springframework.domain.Difficulty;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.util.HashSet;
import java.util.Set;

@Data
@SuperBuilder
public class RecipeCommand {
	private Long id;
	private String description;
	private Integer prepTime;
	private Integer cookTime;
	private Integer servings;
	private String source;
	private String url;
	private String directions;
	private Set<IngredientCommand> ingredients;
	private Difficulty difficulty;
	private NotesCommand notes;

	private Set<CategoryCommand> categories;

	public Set<IngredientCommand> getIngredients() {
		if (ingredients == null) {
			ingredients = new HashSet<>();
		}

		return ingredients;
	}

	public Set<CategoryCommand> getCategories() {
		if (categories == null) {
			categories = new HashSet<>();
		}

		return categories;
	}
}