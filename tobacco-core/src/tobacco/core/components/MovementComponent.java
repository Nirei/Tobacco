package tobacco.core.components;

import tobacco.core.util.Vector2D;

public class MovementComponent implements Component {

	private Vector2D direction= new Vector2D(0,0);
	private float speed = 0; 
	
	@Override
	public String getComponentType() {
		return MOVEMENT_C;
	}
	
	public MovementComponent() 
	{
		
	}
	
	public MovementComponent(float _speed) {
		speed = _speed;
	}

	public MovementComponent(Vector2D _direction, float _speed) {
		direction = _direction;
		speed = _speed;
	}
	
	public float getSpeed() {
		return speed;
	}
	
	public void setSpeed(float _speed) {
		speed = _speed;
	}
	
	public Vector2D getDirection() {
		return direction;
	}
	
	public void setDirection(Vector2D _direction) {
		direction = _direction;
	}
	
	public String toString()
	{
		return "Direction: " + direction + ", Speed: " + speed ;
	}

}
