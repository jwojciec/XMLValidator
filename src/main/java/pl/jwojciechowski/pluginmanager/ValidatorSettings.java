package pl.jwojciechowski.pluginmanager;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

public class ValidatorSettings {
	InputStream input = null;
	HashMap<Plugins, String> hm = new HashMap<Plugins, String>();

	public HashMap<Plugins, String> readMainConfig(String propsFileName) {
		Properties props = new Properties();
		try {
			input = new FileInputStream(propsFileName);
			props.load(input);

			for (String key : props.stringPropertyNames()) {
				hm.put(Plugins.valueOf(key), props.getProperty(key));
			}
			input.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			System.out.println("Plugin not known: " + e.getMessage());
		}

		return hm;
	}

	public List<String> readValidatorCommands(String validatorConfigName) {
		Properties props = new Properties();
		List<String> validatorCommands = new ArrayList<String>();
		try {
			input = new FileInputStream(validatorConfigName);
			props.load(input);
			for (String s : props.stringPropertyNames()) {
				validatorCommands.add(props.getProperty(s));
			}
			input.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return validatorCommands;
	}
}
