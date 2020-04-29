package guru.springframework.commands;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@SuperBuilder
public class IngredientCommand {
	private Long id;
	private String description;
	private BigDecimal amount;
	private UnitOfMeasureCommand unitOfMeasure;
}