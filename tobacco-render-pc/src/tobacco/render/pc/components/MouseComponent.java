package tobacco.render.pc.components;

public class MouseComponent implements RendererComponent {
	
	private float x,y;

	@Override
	public String getComponentType() {
		return MOUSE_C;
	}

	public void setX(float _x) {
		x = _x;
	}
	public void setY(float _y) {
		y = _y;
	}
	public float getX() {
		return x;
	}
	public float getY() {
		return y;
	}
	
	public String toString() {
		return "Mouse: (" + x + ", " + y + ")";
	}
}
