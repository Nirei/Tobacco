package tobacco.core.components;

public class RemoveComponent implements Component {

	@Override
	public String getComponentType() {
		return REMOVE_C;
	}

	@Override
	public String toString() {
		return "Remove";
	}
}
