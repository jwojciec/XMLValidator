package pl.jwojciechowski.validator;

import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class ValidatorErrorHandler implements ErrorHandler {
	
	public void warning(SAXParseException e) throws SAXException {
		System.out.println(e.getMessage());
		//logging
        //throw new SAXException("Warning encountered");
	}

	public void error(SAXParseException e) throws SAXException {
		System.out.println(e.getMessage());
		//logging
        //throw new SAXException("Error encountered");
	}

	public void fatalError(SAXParseException e) throws SAXException {
		System.out.println(e.getMessage());        
        throw new SAXException("Fatal Error encountered");
	}
}
