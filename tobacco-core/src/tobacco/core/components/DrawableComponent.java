package tobacco.core.components;

import tobacco.core.util.Vector2D;

public class DrawableComponent implements Component {
	
	private String imagePath;
	private Vector2D size;
	
	public DrawableComponent() {}
	public DrawableComponent(String _imagePath, Vector2D _size) {
		imagePath = _imagePath;
		size = _size;
	}
	
	@Override
	public short getComponentType() {
		return DRAWABLE_C;
	}
	
	public String getImagePath() {
		return imagePath;
	}
	
	public void setImagePath(String _imagePath) {
		imagePath = _imagePath;
	}
	
	public Vector2D getSize() {
		return size==null?null:new Vector2D(size.getX(), size.getY());
	}
	
	public void setSize(Vector2D _size) {
		size = _size;
	}
	
	@Override
	public String toString() {
		return "Drawable: {ImagePath: " + imagePath + ", Size: " + size + "}";
	}
}
