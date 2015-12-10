package tobacco.core.util.xml;

public final class XmlConstants {
	//public static final String XML_NS = "tobacco";
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
