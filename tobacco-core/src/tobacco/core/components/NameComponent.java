package tobacco.core.components;

public class NameComponent implements Component {
	
	private String name;
	
	public NameComponent() {}
	
	public NameComponent(String name) {
		this.name = name;
	}
	
	@Override
	public Type getComponentType() {
		return NAME_C;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Name: " + name;
	}
}
