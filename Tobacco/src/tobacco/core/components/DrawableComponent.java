package tobacco.core.components;

public class DrawableComponent implements Component {
	
	private String imagePath;
	
	@Override
	public int getComponentType() {
		return DRAWABLE_C;
	}
	
	public String getImagePath() {
		return imagePath;
	}
	
	public void setImagePath(String _imagePath) {
		imagePath = _imagePath;
	}
}
