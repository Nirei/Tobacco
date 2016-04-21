/*******************************************************************************
 * Tobacco - A portable and reusable game engine written in Java.
 * Copyright © 2016 Nirei
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

import static tobacco.core.xml.XmlConstants.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import tobacco.core.components.Component;
import tobacco.core.components.ContainerComponent;
import tobacco.core.components.Type;
import tobacco.core.entities.Entity;
import tobacco.core.util.ContentClass;

public final class XmlAdaptor {

	private XmlAdaptor() {
	}

	public static Element entityToXml(Document d, Entity e) throws XmlSerializationException {
		Element elem = d.createElementNS(XML_NS, ENTITY_TAG);
		for (Component c : e.components())
			if (c.getComponentType().equals(Component.CONTAINER_C)) {
				for (Entity child : (ContainerComponent) c)
					elem.appendChild(entityToXml(d, child));
			} else
				elem.appendChild(componentToXml(d, c));
		return elem;
	}

	public static Element componentToXml(Document d, Component c) throws XmlSerializationException {
		Element elem = d.createElementNS(XML_NS, COMPONENT_TAG);
		
		Type cType = c.getComponentType();
		elem.setAttribute(TYPE_ATTR, cType.getName());
		Class<?> impl = (Class<?>) cType.getImplementer();
		Method[] methods = impl.getDeclaredMethods();
		
		for (int i = 0; i < methods.length; i++) {
			if (isDataGetter(methods[i])) {
				String mName = methods[i].getName();
				// Strip of get/is part of name
				String attrName = mName.startsWith("get") ? mName.substring(3) : mName.substring(2);
				String name = Character.toLowerCase(attrName.charAt(0)) + attrName.substring(1);
				Object val;
				try {
					val = methods[i].invoke(c);
					Class<?> retClass = methods[i].getReturnType();
					if (retClass.getSimpleName().equals("List")) {
						Class<?> contentClass = methods[i].getAnnotation(ContentClass.class).value();
						elem.appendChild(listToXml(d, name, (List<?>) val, contentClass));
						// } else if (retClass.getSimpleName().equals("Map")) {
						// elem.appendChild(mapToElement(d,val,retClass));
					} else
						elem.appendChild(valueToXml(d, name, val, retClass));
				} catch (XmlSerializationException e) {
					throw new XmlSerializationException(e, c);
				} catch (NullPointerException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
					throw new XmlSerializationException(e, c);
				}
			}
		}
		return elem;
	}

	public static Element listToXml(Document d, String name, List<?> list, Class<?> contentClass)
			throws XmlSerializationException {
		Element elem = d.createElementNS(XML_NS, LIST_TAG);
		elem.setAttribute(TYPE_ATTR, classToValueType(contentClass));
		elem.setAttribute(NAME_ATTR, name);
		for (Object o : list) {
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
		case "boolean":
		case "Boolean":
			return BOOL_TYPE;
		case "float":
		case "Float":
			return FLOAT_TYPE;
		case "int":
		case "Integer":
			return INT_TYPE;
		case "long":
		case "Long":
			return LONG_TYPE;
		case "String":
			return STRING_TYPE;
		case "Vector2D":
			return VECTOR_TYPE;
		default:
			throw new XmlSerializationException("Attribute with class " + c.getName() + " is not serializable", c);
		}
	}

	private static boolean isDataGetter(Method method) {
		if (method.getName().equals("getComponentType"))
			return false;
		if (!(method.getName().startsWith("get") || method.getName().startsWith("is")))
			return false;
		if (method.getParameterTypes().length != 0)
			return false;
		if (void.class.equals(method.getReturnType()))
			return false;
		return true;
	}
}
