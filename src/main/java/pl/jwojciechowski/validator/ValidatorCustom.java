package pl.jwojciechowski.validator;

import java.io.File;
import java.util.List;

import pl.jwojciechowski.parser.CustomDOMParser;
import pl.jwojciechowski.pluginmanager.Plugins;

public class ValidatorCustom extends Validator {
	CustomDOMParser customDOM = null;
	boolean result = false;
	
	@Override
	public Plugins getPluginName() {
		return Plugins.VALIDATOR_CUSTOM;
	}
	
	@Override
	public boolean validate(String xmlFile, List<String> commands) {
		customDOM = new CustomDOMParser();
		customDOM.setErrorHandler(new ValidatorErrorHandler());
		customDOM.parseFile(new File(xmlFile));
		result = true;
		
		try {
			for(String s: commands) {
				String command[] = s.split(";");
				String nodeName = command[0];
				int quantity = Integer.parseInt(command[1]);
				if(customDOM.getValues(nodeName).size() != quantity) {
					result = false;
				}
			}
		} catch (NumberFormatException e) {
			System.out.println("Incorrect command: " + e.getMessage());
			result = false;
		} catch (ArrayIndexOutOfBoundsException e) {
			e.printStackTrace();
			result = false;
		} catch (Exception e) {
			e.printStackTrace();
			result = false;
		}
		
		return result;
	}
}
