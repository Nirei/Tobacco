package tobacco.core.components;

public class TintComponent implements Component {

	private float red;
	private float green;
	private float blue;
	
	public static final TintComponent RED = new TintComponent(255f,0f,0f);

	public TintComponent(float r, float g, float b) {
		red = r;
		green = g;
		blue = b;
	}
	
	@Override
	public Type getComponentType() {
		return TINT_C;
	}

	public float getRed() {
		return red;
	}

	public float getGreen() {
		return green;
	}

	public float getBlue() {
		return blue;
	}
	
}
