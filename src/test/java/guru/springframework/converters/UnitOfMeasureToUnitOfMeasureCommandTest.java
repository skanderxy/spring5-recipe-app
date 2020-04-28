package guru.springframework.converters;

import guru.springframework.commands.UnitOfMeasureCommand;
import guru.springframework.domain.UnitOfMeasure;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class UnitOfMeasureToUnitOfMeasureCommandTest {

	public static final String DESCRIPTION = "description";
	public static final Long LONG_VALUE = 1L;

	UnitOfMeasureToUnitOfMeasureCommand converter;

	@Before
	public void setUp() {
		converter = new UnitOfMeasureToUnitOfMeasureCommand();
	}

	@Test
	public void testNullObjectConvert() {
		Assert.assertNull(converter.convert(null));
	}

	@Test
	public void testEmptyObj() {
		Assert.assertNotNull(converter.convert(new UnitOfMeasure()));
	}

	@Test
	public void convert() {
		//given
		UnitOfMeasure uom = new UnitOfMeasure();
		uom.setId(LONG_VALUE);
		uom.setDescription(DESCRIPTION);
		//when
		UnitOfMeasureCommand uomc = converter.convert(uom);

		//then
		Assert.assertEquals(LONG_VALUE, uomc.getId());
		Assert.assertEquals(DESCRIPTION, uomc.getDescription());
	}

}