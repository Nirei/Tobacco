/*******************************************************************************
 * Tobacco - A portable and reusable game engine written in Java.
 * Copyright © 2015 Nirei
 *
 * This file is part of Tobacco
 *
 * Tobacco is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *******************************************************************************/
package tobacco.core.xml;

import java.io.IOException;
import java.net.URL;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import tobacco.core.entities.EntityService;
import tobacco.core.game.GameService;
import tobacco.core.serialization.Loader;
import tobacco.core.services.ConfigurationService;
import tobacco.core.services.DefaultConfigurationService;
import tobacco.core.services.RenderingService;

public class XmlLoader extends Loader {
	
	private URL systems;
	private URL entities;
	private URL configuration;
	
	public XmlLoader(String systems, String entities, String configuration) {
		ClassLoader cl = this.getClass().getClassLoader();
		this.systems = cl.getResource(systems);
		this.entities = cl.getResource(entities);
		this.configuration = cl.getResource(configuration);
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
			xmlReader.parse(new InputSource(this.getClass().getClassLoader().getResourceAsStream("levels/testing.xml")));
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			System.err.println("THERE WAS AN ERROR LOADING XML FILE");
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			System.err.println("THERE WAS AN ERROR LOADING XML FILE");
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.err.println("THERE WAS AN ERROR LOADING XML FILE");
			e.printStackTrace();
		} finally {
		}
		return entityHandler.getWorld();
	}

	@Override
	public GameService loadGameService() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RenderingService loadRenderingService() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ConfigurationService loadConfigurationService() {
		try {
			return new DefaultConfigurationService(configuration);
		} catch (IOException e) {
			System.err.println("Unable to load configuration service");
			e.printStackTrace();
		}
		return null;
	}

}
