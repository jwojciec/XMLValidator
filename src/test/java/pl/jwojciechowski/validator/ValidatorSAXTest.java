package pl.jwojciechowski.validator;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;
import pl.jwojciechowski.pluginmanager.Plugins;

public class ValidatorSAXTest extends TestCase {
	ValidatorSAX vs = new ValidatorSAX();

	public ValidatorSAXTest(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
	}

	public void testGetPluginName() {
		assertTrue(vs.getPluginName().equals(Plugins.VALIDATOR_SAX));
	}

	public void testValidate() {
		List<String> aList = new ArrayList<String>();
		aList.add("src\\test\\resources\\plants.xsd");
		assertTrue(vs.validate("src\\test\\resources\\plants.xml", aList));
		assertFalse(vs.validate("src\\test\\resources\\plants_error.xml", aList));		
	}
}
