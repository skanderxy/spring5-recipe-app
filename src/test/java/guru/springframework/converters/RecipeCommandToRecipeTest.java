package guru.springframework.converters;

import guru.springframework.commands.CategoryCommand;
import guru.springframework.commands.IngredientCommand;
import guru.springframework.commands.NotesCommand;
import guru.springframework.commands.RecipeCommand;
import guru.springframework.domain.Difficulty;
import guru.springframework.domain.Recipe;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class RecipeCommandToRecipeTest {
	public static final Long RECIPE_ID = 1L;
	public static final Integer COOK_TIME = Integer.valueOf("5");
	public static final Integer PREP_TIME = Integer.valueOf("7");
	public static final String DESCRIPTION = "My Recipe";
	public static final String DIRECTIONS = "Directions";
	public static final Difficulty DIFFICULTY = Difficulty.EASY;
	public static final Integer SERVINGS = Integer.valueOf("3");
	public static final String SOURCE = "Source";
	public static final String URL = "Some URL";
	public static final Long CAT_ID_1 = 1L;
	public static final Long CAT_ID2 = 2L;
	public static final Long INGRED_ID_1 = 3L;
	public static final Long INGRED_ID_2 = 4L;
	public static final Long NOTES_ID = 9L;

	RecipeCommandToRecipe converter;


	@Before
	public void setUp() {
		converter = new RecipeCommandToRecipe(new CategoryCommandToCategory(),
				new IngredientCommandToIngredient(new UnitOfMeasureCommandToUnitOfMeasure()),
				new NotesCommandToNotes());
	}

	@Test
	public void testNullObject() {
		Assert.assertNull(converter.convert(null));
	}

	@Test
	public void testEmptyObject() {
		Assert.assertNotNull(converter.convert(RecipeCommand.builder().build()));
	}

	@Test
	public void convert() {
		//given
		RecipeCommand recipeCommand = RecipeCommand.builder().build();
		recipeCommand.setId(RECIPE_ID);
		recipeCommand.setCookTime(COOK_TIME);
		recipeCommand.setPrepTime(PREP_TIME);
		recipeCommand.setDescription(DESCRIPTION);
		recipeCommand.setDifficulty(DIFFICULTY);
		recipeCommand.setDirections(DIRECTIONS);
		recipeCommand.setServings(SERVINGS);
		recipeCommand.setSource(SOURCE);
		recipeCommand.setUrl(URL);

		NotesCommand notes = NotesCommand.builder().build();
		notes.setId(NOTES_ID);

		recipeCommand.setNotes(notes);

		CategoryCommand category = CategoryCommand.builder().build();
		category.setId(CAT_ID_1);

		CategoryCommand category2 = CategoryCommand.builder().build();
		category2.setId(CAT_ID2);

		recipeCommand.getCategories().add(category);
		recipeCommand.getCategories().add(category2);

		IngredientCommand ingredient = IngredientCommand.builder().build();
		ingredient.setId(INGRED_ID_1);

		IngredientCommand ingredient2 = IngredientCommand.builder().build();
		ingredient2.setId(INGRED_ID_2);

		recipeCommand.getIngredients().add(ingredient);
		recipeCommand.getIngredients().add(ingredient2);

		//when
		Recipe recipe = converter.convert(recipeCommand);

		Assert.assertNotNull(recipe);
		Assert.assertEquals(RECIPE_ID, recipe.getId());
		Assert.assertEquals(COOK_TIME, recipe.getCookTime());
		Assert.assertEquals(PREP_TIME, recipe.getPrepTime());
		Assert.assertEquals(DESCRIPTION, recipe.getDescription());
		Assert.assertEquals(DIFFICULTY, recipe.getDifficulty());
		Assert.assertEquals(DIRECTIONS, recipe.getDirections());
		Assert.assertEquals(SERVINGS, recipe.getServings());
		Assert.assertEquals(SOURCE, recipe.getSource());
		Assert.assertEquals(URL, recipe.getUrl());
		Assert.assertEquals(NOTES_ID, recipe.getNotes().getId());
		Assert.assertEquals(2, recipe.getCategories().size());
		Assert.assertEquals(2, recipe.getIngredients().size());
	}

}