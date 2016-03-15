Plugin management is based on text files.
Methods of validation can be chosen by user but it must be developed and added to enum Plugins to work.
Every method has its own properties file where user can add his commands.

Eclipse project:
pl.jwojciechowski.parser - CustomDOMParser used by VALIDATOR_CUSTOM method
pl.jwojciechowski.pluginmanager - classes for plugins management
pl.jwojciechowski.validator - implementation of all VALIDATOR methods
scr/test/java - some Unit tests
scr/test/resources - files used by Unit tests

All cases are explained via provided files:
plants.xml
plants.xsd
PLUGINS.properties
ValidatorCustom.properties
ValidatorDOM.properties
ValidatorSAX.properties

User can choose which plugins should be used for validation via PLUGINS.properties file:
#key = validation method
#value = validator file
VALIDATOR_CUSTOM=ValidatorCustom.properties
VALIDATOR_SAX=ValidatorSAX.properties
VALIDATOR_DOM=ValidatorDOM.properties
# not implemented, should return info during runtime
VALIDATOR_TEST=ValidatorTEST.properties

result:
Plugin not known: No enum constant pl.jwojciechowski.pluginmanager.Plugins.VALIDATOR_TEST
Plugin loaded: VALIDATOR_DOM
Plugin loaded: VALIDATOR_CUSTOM
Plugin loaded: VALIDATOR_SAX

Implemented validation methods:
1. VALIDATOR_CUSTOM
	commands taken from ValidatorCustom.properties
	# Example of custom validation. Check quantity of given node
	COMMAND1=PLANT;36
	COMMAND2=COMMON;12	
2. VALIDATOR_SAX
	commands taken from ValidatorSAX.properties
	each command is xsd file name that is used for validation
	COMMAND1=plants.xsd
	COMMAND2=plants2.xsd	
3. VALIDATOR_DOM
	commands taken from ValidatorDOM.properties
	each command is xsd file name that is used for validation
	COMMAND1=plants.xsd
	COMMAND2=plants2.xsd
	COMMAND3=plants.xsd
	COMMAND4=plants2.xsd	

Results of validation:

1. VALIDATOR_SAX - Valid XML and both commands are valid xsd files:
	----------PARSING STARTED FOR:--------------
	VALIDATOR_SAX SUCCESSFULLY validated
	[plants2.xsd, plants.xsd]
	------------PARSING FINISHED----------------

2. VALIDATOR_DOM - 	Valid XML and all commands are valid xsd files:
	----------PARSING STARTED FOR:--------------
	VALIDATOR_DOM SUCCESSFULLY validated
	[plants2.xsd, plants.xsd, plants2.xsd, plants.xsd]
	------------PARSING FINISHED----------------

3. VALIDATOR_CUSTOM - Valid XML but one command is wrong. COMMON node occurs 36 times not 12.
	----------PARSING STARTED FOR:--------------
	VALIDATOR_CUSTOM Validation FAILED!
	[COMMON;12, PLANT;36]
	------------PARSING FINISHED----------------

