package tobacco.game.test.components;

import tobacco.core.util.Vector2D;

public class DirectionComponent implements GameComponent {
	
	private Vector2D direction = Vector2D.VERTICAL;
	
	public DirectionComponent() {}
	
	public DirectionComponent(Vector2D direction) {
		this.direction = direction;
	}

	@Override
	public String getComponentType() {
		return DIRECTION_C;
	}

	public Vector2D getDirection() {
		return direction;
	}
	
	public void setDirection(Vector2D direction) {
		this.direction = direction;
	}
	
	@Override
	public String toString() {
		return "Direction: " + direction;
	}
}
