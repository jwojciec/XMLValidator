package pl.jwojciechowski.validator;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;
import pl.jwojciechowski.pluginmanager.Plugins;

public class ValidatorCustomTest extends TestCase {
	ValidatorCustom vc = new ValidatorCustom();

	public ValidatorCustomTest(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
	}

	public void testGetPluginName() {
		assertTrue(vc.getPluginName().equals(Plugins.VALIDATOR_CUSTOM));
	}

	public void testValidate() {
		List<String> aList = new ArrayList<String>();
		aList.add("COMMON;36");
		assertTrue(vc.validate("src\\test\\resources\\plants.xml", aList));
		assertFalse(vc.validate("src\\test\\resources\\plants_error.xml", aList));		
	}

}
