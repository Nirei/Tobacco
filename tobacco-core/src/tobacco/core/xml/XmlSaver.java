package tobacco.core.xml;

import static tobacco.core.xml.XmlConstants.XML_NS;
import static tobacco.core.xml.XmlConstants.WORLD_TAG;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import tobacco.core.serialization.Saver;
import tobacco.core.services.EntityService;
import tobacco.core.services.GameService;

public class XmlSaver extends Saver {

	private File systems;
	private File entities;
	
	public XmlSaver(String systems, String entities) {
		this.systems = new File(systems);
		this.entities = new File(entities);
	}
	
	@Override
	public void saveEntityService(EntityService entServ) {
		try {
			DocumentBuilderFactory dBuilderFac = DocumentBuilderFactory.newInstance();
			dBuilderFac.setNamespaceAware(true);
			DocumentBuilder docBuilder = dBuilderFac.newDocumentBuilder();

			Document document = docBuilder.newDocument();

			Element rootElement = document.createElementNS(XML_NS, WORLD_TAG);
			rootElement.appendChild(XmlAdaptor.entityToXml(document, entServ.getRoot()));
			document.appendChild(rootElement);
			document.normalizeDocument();
			
			Transformer transformer = TransformerFactory.newInstance().newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
			DOMSource domSource = new DOMSource(document);
			StreamResult file = new StreamResult(entities);
			transformer.transform(domSource, file);

		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerException | TransformerFactoryConfigurationError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (XmlSerializationException e) {
			System.out.println("Error marshalling: " + e.getCause());
			e.printStackTrace();
		}
	}

	@Override
	public void saveGameService(GameService gameServ) {
		// TODO Auto-generated method stub

	}
}
