package pl.jwojciechowski.parser;

import java.io.File;
import java.util.List;

import junit.framework.TestCase;

public class CustomDOMParserTest extends TestCase {
	CustomDOMParser domParser = new CustomDOMParser();
	
	public CustomDOMParserTest(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
	}

	public void testParseFile() {
		assertTrue(domParser.parseFile(new File("src\\test\\resources\\plants.xml")));
		assertFalse(domParser.parseFile(new File("src\\test\\resources\\plants_error.xml")));
	}
	
	/*
	public void testGetAttributes() {
		fail("Not yet implemented");
	}
	*/

	public void testGetValues() {
		domParser.parseFile(new File("src\\test\\resources\\plants.xml"));
		List<String> aList = domParser.getValues("COMMON");
		assertTrue(aList.size() == 36);
		
		aList = domParser.getValues("PLANT");
		assertFalse(aList.size() != 36);
	}
}
