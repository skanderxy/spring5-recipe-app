package guru.springframework.commands;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class NotesCommand {
	private Long id;
	private String recipeNotes;
}