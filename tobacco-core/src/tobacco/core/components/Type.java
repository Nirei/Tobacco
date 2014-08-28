package tobacco.core.components;

public final class Type {

	private final String name;
	private final Class<?> implementer;

	public Type(String name, Class<?> implementer) {
		this.name = name;
		this.implementer = implementer;
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
