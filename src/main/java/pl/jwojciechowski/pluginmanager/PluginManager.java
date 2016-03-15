package pl.jwojciechowski.pluginmanager;

import java.util.HashMap;
import java.util.List;

public class PluginManager {
	private static final String MAIN_PROPERTIES_FILE = "PLUGINS.properties";
	private ValidatorSettings validatorSettings = new ValidatorSettings();
	private HashMap<Plugins, String> pluginMap = null;
	private HashMap<Plugins, String> pluginFinalMap = null;

	public void fillPluginMap() {
		pluginMap = validatorSettings.readMainConfig(MAIN_PROPERTIES_FILE);
		loadPlugins();
	}

	public boolean isPluginLoaded(Plugins pluginName) {
		if (pluginFinalMap.containsKey(pluginName))
			return true;
		return false;
	}

	public List<String> getValidatorCommands(Plugins pluginName) {
		String validatorFile = pluginFinalMap.get(pluginName);
		List<String> commandList = validatorSettings.readValidatorCommands(validatorFile);
		return commandList;
	}

	private void loadPlugins() {
		pluginFinalMap = new HashMap<Plugins, String>();
		for (Plugins key : pluginMap.keySet()) {
			if (Plugins.isValidPlugin(key)) {
				System.out.println("Plugin loaded: " + key);
				pluginFinalMap.put(key, pluginMap.get(key));
			}
			else
				System.out.println("Plugin not known: " + key);				
		}
	}
}
