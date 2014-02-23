package tobacco.game.test.components;

import tobacco.core.util.Vector2D;

public class WeaponComponent implements GameComponent {
	
	private Vector2D bulletSize;
	private Vector2D bulletDirection;
	private float bulletSpeed;

	@Override
	public String getComponentType() {
		return WEAPON_C;
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

}
