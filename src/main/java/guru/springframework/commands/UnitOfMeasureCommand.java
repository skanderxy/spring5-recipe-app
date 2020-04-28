package guru.springframework.commands;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class UnitOfMeasureCommand {
	private Long id;
	private String description;
}