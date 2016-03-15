package pl.jwojciechowski;

import java.util.ArrayList;
import java.util.List;

import pl.jwojciechowski.pluginmanager.PluginManager;
import pl.jwojciechowski.validator.Validator;
import pl.jwojciechowski.validator.ValidatorCustom;
import pl.jwojciechowski.validator.ValidatorDOM;
import pl.jwojciechowski.validator.ValidatorSAX;

public class ValidatorApp {
	static final String XML_FILE = "plants.xml";
	List<Validator> validatorsList = null;
	PluginManager pluginManager = null;	

	public void init() {
		validatorsList = new ArrayList<Validator>();		
		validatorsList.add(new ValidatorSAX());
		validatorsList.add(new ValidatorDOM());
		validatorsList.add(new ValidatorCustom());
		
		pluginManager = new PluginManager();
		pluginManager.fillPluginMap();		
	}
	
	public void processXML() {
		for (Validator v : validatorsList) {
			if (pluginManager.isPluginLoaded(v.getPluginName())) {
				List<String> validatorCommands = pluginManager.getValidatorCommands(v.getPluginName());
				System.out.println("\n----------PARSING STARTED FOR:--------------");
				System.out.print(v.getPluginName() + " ");
				if(v.validate(XML_FILE, validatorCommands))
					System.out.println("SUCCESSFULLY validated");
				else
					System.out.println("Validation FAILED!");
				System.out.println(validatorCommands);
				System.out.println("------------PARSING FINISHED----------------\n");				
			}
		}
	}
}
