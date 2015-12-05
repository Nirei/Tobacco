package tobacco.core.components;

public class TintComponent implements Component {

	private float red;
	private float green;
	private float blue;
	
	public static final TintComponent RED = new TintComponent(1f,0f,0f);
	public static final TintComponent LIGHT_BLUE = new TintComponent(0.5f,0.5f,1f);
	public static final Component GREEN = new TintComponent(0f, 1f, 0f);

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

	public void setRed(float red) {
		this.red = red;
	}

	public void setGreen(float green) {
		this.green = green;
	}

	public void setBlue(float blue) {
		this.blue = blue;
	}
	
}
