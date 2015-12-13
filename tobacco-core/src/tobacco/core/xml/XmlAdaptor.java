package tobacco.core.xml;

import static tobacco.core.xml.XmlConstants.XML_NS;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static tobacco.core.xml.XmlConstants.BOOL_TYPE;
import static tobacco.core.xml.XmlConstants.COMPONENT_TAG;
import static tobacco.core.xml.XmlConstants.ENTITY_TAG;
import static tobacco.core.xml.XmlConstants.FLOAT_TYPE;
import static tobacco.core.xml.XmlConstants.INT_TYPE;
import static tobacco.core.xml.XmlConstants.ITEM_TAG;
import static tobacco.core.xml.XmlConstants.LIST_TAG;
import static tobacco.core.xml.XmlConstants.LONG_TYPE;
import static tobacco.core.xml.XmlConstants.NAME_ATTR;
import static tobacco.core.xml.XmlConstants.STRING_TYPE;
import static tobacco.core.xml.XmlConstants.TYPE_ATTR;
import static tobacco.core.xml.XmlConstants.VALUE_TAG;
import static tobacco.core.xml.XmlConstants.VECTOR_TYPE;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import tobacco.core.components.Component;
import tobacco.core.components.ContainerComponent;
import tobacco.core.components.Type;
import tobacco.core.entities.Entity;
import tobacco.core.util.List;

public final class XmlAdaptor {

	private XmlAdaptor() {}
	
	public static Element entityToElement(Document d, Entity e) throws XmlSerializationException {
		Element elem = d.createElementNS(XML_NS, ENTITY_TAG);
		for(Component c : e.components())
			if(c.getComponentType().equals(Component.CONTAINER_C)) {
				for(Entity child : (ContainerComponent) c)
					elem.appendChild(entityToElement(d, child));
			} else
				elem.appendChild(componentToXml(d, c));
		return elem;	
	}
	
	@SuppressWarnings("unchecked")
	public static Element componentToXml(Document d, Component c) throws XmlSerializationException {
		Element elem = d.createElementNS(XML_NS,COMPONENT_TAG);
		Type cType = c.getComponentType();
		elem.setAttribute(TYPE_ATTR, cType.getName());
		Class <?> impl = (Class<?>) cType.getImplementer();
		Method[] methods = impl.getDeclaredMethods();
		for(int i=0; i<methods.length; i++) {
			if(isDataGetter(methods[i])) {
				String name = methods[i].getName().substring(3).toLowerCase();
				Object val;
				try {
					val = methods[i].invoke(c);
				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
					throw new XmlSerializationException(e,c);
				}
				Class<?> retClass = methods[i].getReturnType();
				if(retClass.getSimpleName().equals("List")) {
					elem.appendChild(listToXml(d, name, (tobacco.core.util.List<Object>) val));
//				} else if (retClass.getSimpleName().equals("Map")) {
//					//elem.appendChild(mapToElement(d,val,retClass));
				} else
					try {
						elem.appendChild(valueToXml(d, name, val, retClass));
					} catch (XmlSerializationException e) {
						throw new XmlSerializationException(e, c);
					}
			}
		}
		return elem;
	}
	
	public static Element listToXml(Document d, String name, List<Object> l) throws XmlSerializationException {
		Element elem = d.createElementNS(XML_NS, LIST_TAG);
		elem.setAttribute(TYPE_ATTR, classToValueType(l.getContentClass()));
		elem.setAttribute(NAME_ATTR, name);
		for(Object o : l) {
			elem.appendChild(itemToXml(d, o));
		}
		
		return elem;
	}
	
	public static Element valueToXml(Document d, String name, Object v, Class<?> c) throws XmlSerializationException {
		Element elem = d.createElementNS(XML_NS, VALUE_TAG);
		elem.setAttribute(TYPE_ATTR, classToValueType(c));
		elem.setAttribute(NAME_ATTR, name);
		elem.setTextContent(v.toString());
		return elem;
	}
	
	public static Element itemToXml(Document d, Object v) throws XmlSerializationException {
		Element elem = d.createElementNS(XML_NS, ITEM_TAG);
		elem.setTextContent(v.toString());
		return elem;
	}

	private static String classToValueType(Class<?> c) throws XmlSerializationException {
		switch (c.getSimpleName()) {
		case "bool":
		case "Boolean":
			return BOOL_TYPE;
		case "float":
		case "Float":
			return FLOAT_TYPE;
		case "Integer":
		case "int":
			return INT_TYPE;
		case "Long":
		case "long":
			return LONG_TYPE;
		case "String":
			return STRING_TYPE;
		case "Vector2D":
			return VECTOR_TYPE;
		default:
			throw new XmlSerializationException("Attribute with class " + c.getName() + " is not serializable", c);
		}
	}

	private static boolean isDataGetter(Method method){
		if(method.getName().equals("getComponentType")) return false;
		if(!method.getName().startsWith("get")) return false;
		if(method.getParameterTypes().length != 0) return false;  
		if(void.class.equals(method.getReturnType())) return false;
		return true;
	}
}
