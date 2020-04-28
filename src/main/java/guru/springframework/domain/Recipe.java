package guru.springframework.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
public class Recipe {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Lob
	private String description;
	private Integer prepTime;
	private Integer cookTime;
	private Integer servings;
	private String source;
	private String url;

	@Lob
	private String directions;
	private Byte[] image;

	@Enumerated(value = EnumType.STRING)
	private Difficulty difficulty;

	@ManyToMany
	@JoinTable(name = "recipe_category",
			joinColumns = @JoinColumn(name = "recipe_id"),
			inverseJoinColumns = @JoinColumn(name = "category_id"))
	Set<Category> categories;

	@OneToOne(cascade = CascadeType.ALL)
	private Notes notes;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "recipe")
	private Set<Ingredient> ingredients;

	public Recipe addIngredient(Ingredient ingredient) {
		ingredient.setRecipe(this);
		getIngredients().add(ingredient);

		return this;
	}

	public void setNotes(Notes notes) {
		if (notes != null) {
			this.notes = notes;
			notes.setRecipe(this);
		}
	}

	public Set<Ingredient> getIngredients() {
		if (ingredients == null) {
			ingredients = new HashSet<>();
		}

		return ingredients;
	}

	public Set<Category> getCategories() {
		if (categories == null) {
			categories = new HashSet<>();
		}

		return categories;
	}
}
