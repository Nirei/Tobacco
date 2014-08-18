package tobacco.core.serialization;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import tobacco.core.components.Entity;

public class EntityToXml {
	
	private Marshaller marshaller;
	
	public EntityToXml(JAXBContext context) throws JAXBException {
		this.marshaller = context.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
	}

	public void printOutXmlTree(Entity entity) throws JAXBException {
		marshaller.marshal(entity, System.out);
	}
}
