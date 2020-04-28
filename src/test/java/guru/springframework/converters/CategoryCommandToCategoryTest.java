package guru.springframework.converters;


import guru.springframework.commands.CategoryCommand;
import guru.springframework.domain.Category;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CategoryCommandToCategoryTest {

	public static final Long ID_VALUE = 1L;
	public static final String DESCRIPTION = "description";
	CategoryCommandToCategory conveter;

	@Before
	public void setUp() {
		conveter = new CategoryCommandToCategory();
	}

	@Test
	public void testNullObject() {
		assertNull(conveter.convert(null));
	}

	@Test
	public void testEmptyObject() {
		assertNotNull(conveter.convert(CategoryCommand.builder().build()));
	}

	@Test
	public void convert() {
		//given
		CategoryCommand categoryCommand = CategoryCommand.builder().build();
		categoryCommand.setId(ID_VALUE);
		categoryCommand.setDescription(DESCRIPTION);

		//when
		Category category = conveter.convert(categoryCommand);

		//then
		assertEquals(ID_VALUE, category.getId());
		assertEquals(DESCRIPTION, category.getDescription());
	}

}