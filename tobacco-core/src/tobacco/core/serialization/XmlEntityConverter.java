package tobacco.core.serialization;

import java.io.InputStream;
import java.io.OutputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import tobacco.core.components.Entity;

public class XmlEntityConverter {
	
	private Marshaller marshaller;
	private Unmarshaller unmarshaller;
	
	public XmlEntityConverter(JAXBContext context) throws JAXBException {
		marshaller = context.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		unmarshaller = context.createUnmarshaller();
	}
	
	public Entity toEntity(InputStream fis) throws JAXBException {
		return (Entity) unmarshaller.unmarshal(fis);
	}

	public void toXml(Entity entity, OutputStream fos) throws JAXBException {
		marshaller.marshal(entity, fos);
	}
}
