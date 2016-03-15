package pl.jwojciechowski.validator;

import java.io.IOException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.SchemaFactory;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import pl.jwojciechowski.pluginmanager.Plugins;

public class ValidatorSAX extends Validator {

	@Override
	public Plugins getPluginName() {
		return Plugins.VALIDATOR_SAX;
	}

	@Override
	public boolean validate(String xmlFile, List<String> commands) {
		SAXParserFactory factory = SAXParserFactory.newInstance();
		factory.setValidating(false);
		factory.setNamespaceAware(true);

		SchemaFactory schemaFactory = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");

		try {
			// convert List<String> to Source[]
			Source[] source = new Source[commands.size()];
			for (int i = 0; i < commands.size(); i++) {
				source[i] = new StreamSource(commands.get(i));
			}

			factory.setSchema(schemaFactory.newSchema(source));
			SAXParser parser = factory.newSAXParser();

			XMLReader reader = parser.getXMLReader();
			reader.setErrorHandler(new ValidatorErrorHandler());
			reader.parse(new InputSource(xmlFile));
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
			return false;
		} catch (SAXException e) {
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}
}
