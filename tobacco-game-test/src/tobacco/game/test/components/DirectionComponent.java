package tobacco.game.test.components;

import tobacco.core.components.Type;
import tobacco.core.datatypes.GVector2D;

public class DirectionComponent implements GameComponent {
	
	private GVector2D direction;
	
	public DirectionComponent() {}
	
	public DirectionComponent(GVector2D direction) {
		this.direction = direction;
	}

	@Override
	public Type getComponentType() {
		return DIRECTION_C;
	}

	public GVector2D getDirection() {
		return direction;
	}
	
	public void setDirection(GVector2D direction) {
		this.direction = direction;
	}
	
	@Override
	public String toString() {
		return "Direction: " + direction;
	}
}
