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
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.ext.DefaultHandler2;
import tobacco.core.components.Component;
import tobacco.core.components.ContainerComponent;
import tobacco.core.components.Type;
import tobacco.core.entities.DefaultEntityService;
import tobacco.core.entities.Entity;
import tobacco.core.entities.EntityService;
import tobacco.core.util.Vector2D;

public class XmlEntityHandler extends DefaultHandler2 {
	

	private StringBuilder contentAcc = new StringBuilder();
	private Stack<Entity> entStack = new Stack<>();
	private Component currComp;
	private List<Object> currList;
	private Map<Object,Object> currMap;
	private Class<?> currCompClass;
	private String currValueType;
	private String currValueName;
	private Class<?> currValueClass;
	private String currKey;
	private String currKeyType;
	private EntityService world;
	private Entity root;
	
	private static final Pattern pattern = Pattern.compile("\\{([+-]?[0-9]*\\.[0-9]*|[0-9]*),\\ ?([+-]?[0-9]*\\.[0-9]*|[0-9]*)\\}");
	
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		super.startElement(uri, localName, qName, attributes);
		switch (localName) {
		case ENTITY_TAG:
			Entity e = world.create();
			if(root == null) {
				world.setRoot(e);
				root = e;
			} else {
				Entity parent = entStack.peek();
				if(!parent.has(Component.CONTAINER_C)) parent.add(new ContainerComponent());
				((ContainerComponent) parent.get(Component.CONTAINER_C)).addChild(e);;
			}
			entStack.push(e);
			break;
		case COMPONENT_TAG:
			String cTypeName = attributes.getValue(TYPE_ATTR);
			Type cType = Type.findByName(cTypeName);
			
			// If cType is null, the type name declared in XML
			// doesn't match any known component types
			if(cType == null) {
				Logger.getGlobal().log(Level.WARNING, "Parser mismatch: {0} is not a known component type", cTypeName);
			} else {
				currCompClass = Type.findByName(cTypeName).getImplementer();
				try {
					currComp = (Component) currCompClass.newInstance();
				} catch (InstantiationException e1) {
					throw new SAXException(e1);
				} catch (IllegalAccessException e1) {
					throw new SAXException(e1);
				}
				entStack.peek().add(currComp);
			}
			break;
		case LIST_TAG:
			currValueType = attributes.getValue(TYPE_ATTR);
			currValueClass = classFromType(currValueType);
			currValueName = attributes.getValue(NAME_ATTR);
			currList = new LinkedList<Object>();
			break;
		case MAP_TAG:
			currValueType = attributes.getValue(TYPE_ATTR);
			currKeyType = attributes.getValue(KEY_TYPE_ATTR);
			break;
		case VALUE_TAG:
			currValueType = attributes.getValue(TYPE_ATTR);
			currValueClass = classFromType(currValueType);
			currValueName = attributes.getValue(NAME_ATTR);
			break;
		case ENTRY_TAG:
			currKey = attributes.getValue(KEY_ATTR);
			break;
		case ITEM_TAG:
			break;
		case WORLD_TAG:
			world = new DefaultEntityService();
			break;
		default:
			throw new SAXException("Unrecognized tag: " + localName);
		}
	}
	
	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		super.endElement(uri, localName, qName);
		String content;
		switch (localName) {
		case ENTITY_TAG:
			entStack.pop();
			break;
		case COMPONENT_TAG:
			currComp = null;
			currCompClass = null;
			break;
		case LIST_TAG:
			try {
				String setterName = "set" + Character.toUpperCase(currValueName.charAt(0)) + currValueName.substring(1);
				Method setter = currCompClass.getMethod(setterName, List.class);
				List<Object> defList = new ArrayList<Object>(currList.size());
				defList.addAll(currList);
				setter.invoke(currComp, defList);
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				throw new SAXException(e);
			} catch (NoSuchMethodException | SecurityException e) {
				throw new SAXException(e);
			}
			currList = null;
			break;
		case MAP_TAG:
			try {
				String setterName = "set" + Character.toUpperCase(currValueName.charAt(0)) + currValueName.substring(1);
				Method setter = currCompClass.getMethod(setterName, Map.class);
				setter.invoke(currComp, currMap);
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				throw new SAXException(e);
			} catch (NoSuchMethodException | SecurityException e) {
				throw new SAXException(e);
			}
			currMap = null;
			break;
		case VALUE_TAG:
			content = contentAcc.toString();
			try {
				String setterName = "set" + Character.toUpperCase(currValueName.charAt(0)) + currValueName.substring(1);
				Method setter = currCompClass.getMethod(setterName, currValueClass);
				setter.invoke(currComp, parse(currValueType, content));
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				throw new SAXException(e);
			} catch (NoSuchMethodException | SecurityException e) {
				throw new SAXException(e);
			}
			break;
		case ENTRY_TAG:
			content = contentAcc.toString();
			currMap.put(parse(currKeyType, currKey), parse(currValueType, content));
			break;
		case ITEM_TAG:
			content = contentAcc.toString();
			currList.add(parse(currValueType, content));
			break;
		case WORLD_TAG:
			break;
		default:
			throw new SAXException("Unrecognized tag: " + localName);
		}
		contentAcc.setLength(0);
	}
	
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		super.characters(ch, start, length);
		for(int i=start; i<start+length; i++) contentAcc.append(ch[i]);
	}
	
	@Override
	public void endDocument() throws SAXException {
		super.endDocument();
		if(!entStack.isEmpty()) throw new SAXException("Entity stack wasn't empty after parsing ended");
	}
	
	public EntityService getWorld() {
		return world;
	}
	
	private Class<?> classFromType(String type) {
		switch (type) {
		case VECTOR_TYPE:
			return Vector2D.class;
		case INT_TYPE:
			return Integer.class;
		case LONG_TYPE:
			return Long.class;
		case FLOAT_TYPE:
			return Float.class;
		case BOOL_TYPE:
			return Boolean.class;
		case STRING_TYPE:
			return String.class;
		default:
			return null;
		}
	}
	
	private Object parse(String type, String value) {
		String trimmed = value.trim();
		switch (type) {
		case VECTOR_TYPE:
			Matcher matcher = pattern.matcher(trimmed);
			matcher.matches();
			float x = Float.parseFloat(matcher.group(1));
			float y = Float.parseFloat(matcher.group(2));
			return new Vector2D(x,y);
		case INT_TYPE:
			return Integer.parseInt(trimmed);
		case LONG_TYPE:
			return Long.parseLong(trimmed);
		case FLOAT_TYPE:
			return Float.parseFloat(trimmed);
		case BOOL_TYPE:
			return Boolean.parseBoolean(trimmed);
		case STRING_TYPE:
			return trimmed;
		default:
			return null;
		}
	}
}
