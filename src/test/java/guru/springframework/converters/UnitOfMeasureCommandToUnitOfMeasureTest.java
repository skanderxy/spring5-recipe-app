package guru.springframework.converters;

import guru.springframework.commands.UnitOfMeasureCommand;
import guru.springframework.domain.UnitOfMeasure;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class UnitOfMeasureCommandToUnitOfMeasureTest {

	public static final String DESCRIPTION = "description";
	public static final Long LONG_VALUE = 1L;

	UnitOfMeasureCommandToUnitOfMeasure converter;

	@Before
	public void setUp() {
		converter = new UnitOfMeasureCommandToUnitOfMeasure();

	}

	@Test
	public void testNullParamter() {
		Assert.assertNull(converter.convert(null));
	}

	@Test
	public void testEmptyObject() {
		Assert.assertNotNull(converter.convert(UnitOfMeasureCommand.builder().build()));
	}

	@Test
	public void convert() {
		//given
		UnitOfMeasureCommand command = UnitOfMeasureCommand.builder().build();
		command.setId(LONG_VALUE);
		command.setDescription(DESCRIPTION);

		//when
		UnitOfMeasure uom = converter.convert(command);

		//then
		Assert.assertNotNull(uom);
		Assert.assertEquals(LONG_VALUE, uom.getId());
		Assert.assertEquals(DESCRIPTION, uom.getDescription());

	}
}