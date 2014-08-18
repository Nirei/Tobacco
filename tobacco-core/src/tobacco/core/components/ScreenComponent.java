package tobacco.core.components;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import tobacco.core.util.Vector2D;

/**
 * Marks the entity that holds screen information such as width and height
 * @author nirei
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
public class ScreenComponent implements Component {

	private Vector2D screenSize;
	
	public ScreenComponent() {}
	
	public ScreenComponent(Vector2D screenSize) {
		this.screenSize = screenSize;
	}
	
	@Override
	public String getComponentType() {
		return SCREEN_C;
	}
	
	@XmlElement
	public Vector2D getScreenSize() {
		return screenSize;
	}
	
	public void setScreenSize(Vector2D screenSize) {
		this.screenSize = screenSize;
	}
	
	@Override
	public String toString() {
		return "Screen: " + screenSize;
	}
}
