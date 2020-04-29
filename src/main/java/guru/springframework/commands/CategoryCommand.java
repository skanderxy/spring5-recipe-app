package guru.springframework.commands;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@SuperBuilder
public class CategoryCommand {
	private Long id;
	private String description;
}