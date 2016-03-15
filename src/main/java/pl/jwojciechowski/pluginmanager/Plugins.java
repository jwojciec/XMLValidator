package pl.jwojciechowski.pluginmanager;

public enum Plugins {
	VALIDATOR_DEFAULT, VALIDATOR_DOM, VALIDATOR_SAX, VALIDATOR_CUSTOM;
	
	public static boolean isValidPlugin(Plugins key) {
		for (Plugins s : Plugins.values())
			if (s.equals(key))
				return true;
		return false;
	}
}
