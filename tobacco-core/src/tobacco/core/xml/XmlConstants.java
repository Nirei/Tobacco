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

public final class XmlConstants {
	public static final String XML_NS = "tobacco";
	/* Tags */
	public static final String WORLD_TAG = "world";
	public static final String ENTITY_TAG = "entity";
	public static final String COMPONENT_TAG = "component";
	public static final String VALUE_TAG = "value"; /* typed */
	public static final String LIST_TAG = "list"; /* typed */
	public static final String ITEM_TAG = "item"; 
	public static final String MAP_TAG = "map"; /* typed? */
	public static final String ENTRY_TAG = "entry"; 
	/* Attribs */
	public static final String TYPE_ATTR = "type";
	public static final String NAME_ATTR = "name";
	public static final String KEY_TYPE_ATTR = "keyType";
	public static final String KEY_ATTR = "key";
	/* Type attrib. values */
	public static final String VECTOR_TYPE = "vector";
	public static final String INT_TYPE = "int";
	public static final String LONG_TYPE = "long";
	public static final String FLOAT_TYPE = "float";
	public static final String BOOL_TYPE = "bool";
	public static final String STRING_TYPE = "string";
	
	private XmlConstants() {}
}
