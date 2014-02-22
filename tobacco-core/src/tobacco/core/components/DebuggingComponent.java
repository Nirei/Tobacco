package tobacco.core.components;

public class DebuggingComponent implements Component {

	@Override
	public String getComponentType() {
		return DEBUGGING_C;
	}
	
	@Override
	public String toString() {
		return "Debugging: :)";
	}

}
