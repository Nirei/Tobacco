package tobacco.core.loader.xml;

import java.io.IOException;
import java.net.URL;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import tobacco.core.loader.Loader;
import tobacco.core.services.EntityService;
import tobacco.core.services.GameService;

public class XmlLoader extends Loader {
	
	private URL systems;
	private URL entities;
	
	public XmlLoader(String systems, String entities) {
		this.systems = XmlLoader.class.getResource(systems);
		this.entities = XmlLoader.class.getResource(entities);
	}

	@Override
	public EntityService loadEntityService() {

	    SAXParserFactory spf = SAXParserFactory.newInstance();
	    spf.setNamespaceAware(true);
	    SAXParser saxParser;
	    XmlEntityHandler entityHandler = new XmlEntityHandler();
	    try {
			saxParser = spf.newSAXParser();			
			XMLReader xmlReader = saxParser.getXMLReader();
			xmlReader.setContentHandler(entityHandler);
			xmlReader.parse(new InputSource(entities.openStream()));
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return entityHandler.getWorld();
	}

	@Override
	public GameService loadGameService() {
		// TODO Auto-generated method stub
		return null;
	}

}
