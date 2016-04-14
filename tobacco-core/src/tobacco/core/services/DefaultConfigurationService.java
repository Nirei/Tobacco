package tobacco.core.services;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;

public class DefaultConfigurationService implements ConfigurationService {

	Properties prop = new Properties();
	
	public DefaultConfigurationService(URL propFile) throws FileNotFoundException, IOException {
		prop.load(propFile.openStream());
	}

	@Override
	public String getProperty(String key) {
		return prop.getProperty(key);
	}

}
