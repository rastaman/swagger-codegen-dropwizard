package io.brillo.swagger.codegen.dropwizard;

import org.junit.Assert;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class DropwizardServerCodegenTest 
{

	@Test
	public void testGenerator() {
		Assert.assertNotNull(new DropwizardServerCodegen());
	}
}
