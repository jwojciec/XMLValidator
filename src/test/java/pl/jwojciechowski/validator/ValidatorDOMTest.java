package pl.jwojciechowski.validator;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;
import pl.jwojciechowski.pluginmanager.Plugins;

public class ValidatorDOMTest extends TestCase {
	ValidatorDOM vd = new ValidatorDOM();

	public ValidatorDOMTest(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
	}

	public void testGetPluginName() {
		assertTrue(vd.getPluginName().equals(Plugins.VALIDATOR_DOM));
	}

	public void testValidate() {
		List<String> aList = new ArrayList<String>();
		aList.add("src\\test\\resources\\plants.xsd");
		assertTrue(vd.validate("src\\test\\resources\\plants.xml", aList));
		assertFalse(vd.validate("src\\test\\resources\\plants_error.xml", aList));
	}

}
