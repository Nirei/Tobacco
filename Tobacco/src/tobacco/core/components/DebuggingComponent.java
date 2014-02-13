package tobacco.core.components;

public class DebuggingComponent implements Component {

	private static int TYPE = DEBUGGING_C;

	@Override
	public int getComponentType() {
		return TYPE;
	}

}
