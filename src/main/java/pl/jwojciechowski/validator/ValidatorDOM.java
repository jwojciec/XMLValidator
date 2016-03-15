package pl.jwojciechowski.validator;

import java.io.IOException;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.SchemaFactory;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import pl.jwojciechowski.pluginmanager.Plugins;

public class ValidatorDOM extends Validator {

	@Override
	public Plugins getPluginName() {
		return Plugins.VALIDATOR_DOM;
	}

	@Override
	public boolean validate(String xmlFile, List<String> commands) {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
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
			DocumentBuilder builder = factory.newDocumentBuilder();
			builder.setErrorHandler(new ValidatorErrorHandler());

			@SuppressWarnings("unused")
			Document document = builder.parse(new InputSource(xmlFile));
		} catch (SAXException e) {
			e.printStackTrace();
			return false;
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}
}
