package net.ctdp.clay.io;


public class ConfigParser extends AbstractIniParser {

	private Configuration config;
	
	public ConfigParser() {
		super();
		config = new Configuration();
	}
	
	@Override
	protected boolean onSettingParsed(int lineNr, String group, String key,
			String value, String comment) throws ParsingException {
		if(group.equals("General")) {
			// general settings
			if(key.equals("port")) {
				config.port = Integer.parseInt(value);
			}
		} else if(group.equals("URLHandlers")) {
			// urlhandlers setup
			
			if(key.equals("handler")) {
				value = value.substring(1, value.length()-1);
				String[] tokens = value.split(",");
				String handlerSource = tokens[0].trim();
				String handlerTarget = tokens[1].trim();
				config.urlHandlers.put(handlerSource, handlerTarget);
			}
		}
		
		return true;
	}
	
	public Configuration getConfig() {
		return config;
	}
	
}
