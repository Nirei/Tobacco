package tobacco.core.components;

import java.util.HashMap;
import java.util.Map;

public final class Type {

	private static final Map<String, Type> types = new HashMap<String, Type>();

	private final String name;
	private final Class<?> implementer;

	public Type(String name, Class<?> implementer) {
		this.name = name;
		this.implementer = implementer;
		types.put(name, this);
	}
	
	public static Type findByName(String name) {
		return types.get(name);
	}
	
	public String getName() {
		return name;
	}
	
	public Class<?> getImplementer() {
		return implementer;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Type other = (Type) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
}
