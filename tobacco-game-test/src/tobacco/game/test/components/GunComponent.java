package tobacco.game.test.components;

import tobacco.core.util.Vector2D;

public class GunComponent implements GameComponent {

	private Vector2D bulletSize = Vector2D.ZERO;
	private Vector2D bulletDirection = Vector2D.ZERO;
	private float bulletSpeed = 0;
	private boolean shooting = false;
	
	public GunComponent() {}
	
	public GunComponent(Vector2D bulletSize, Vector2D bulletDirection,
			float bulletSpeed) {
		this.bulletSize = bulletSize;
		this.bulletDirection = bulletDirection;
		this.bulletSpeed = bulletSpeed;
	}

	@Override
	public String getComponentType() {
		return GUN_C;
	}

	public Vector2D getBulletSize() {
		return bulletSize;
	}

	public void setBulletSize(Vector2D bulletSize) {
		this.bulletSize = bulletSize;
	}

	public Vector2D getBulletDirection() {
		return bulletDirection;
	}

	public void setBulletDirection(Vector2D bulletDirection) {
		this.bulletDirection = bulletDirection;
	}

	public float getBulletSpeed() {
		return bulletSpeed;
	}

	public void setBulletSpeed(float bulletSpeed) {
		this.bulletSpeed = bulletSpeed;
	}
	
	public void setShooting(boolean _shooting) {
		shooting = _shooting;
	}
	
	public boolean isShooting() {
		return shooting;
	}
	
	@Override
	public String toString() {
		return "Gun: (Shooting: " + shooting + ", Speed: " + bulletSpeed + ", Size: " +
				bulletSize + ", Direction: " + bulletDirection + ")";
	}
}
