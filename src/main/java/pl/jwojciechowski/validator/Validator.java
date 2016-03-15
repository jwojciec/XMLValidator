package pl.jwojciechowski.validator;

import java.util.List;

import pl.jwojciechowski.pluginmanager.Plugins;

public abstract class Validator {
	
	public Plugins getPluginName() {
		return Plugins.VALIDATOR_DEFAULT;
	}

	public abstract boolean validate(String xmlFile, List<String> commands);
}
