package tobacco.game.test.components;

import tobacco.core.util.Vector2D;
import tobacco.game.test.util.BulletData;

public class BulletComponent implements GameComponent {

	private BulletData bullet;
	private Vector2D bulletDirection = Vector2D.ZERO;
	private long bulletPeriod = 200; // Inverse of frequency in ms
	private float bulletSpeed = 0; // Movement speed
	private long lastBullet = System.currentTimeMillis();
	
	public BulletComponent(BulletData bullet) {
		this.bullet = bullet;
	}
	
	public BulletComponent(
			BulletData bullet,
			Vector2D bulletDirection,
			long bulletPeriod,
			float bulletSpeed) {
		this.bullet = bullet;
		this.bulletDirection = bulletDirection;
		this.bulletPeriod = bulletPeriod;
		this.bulletSpeed = bulletSpeed;
	}

	@Override
	public String getComponentType() {
		return BULLET_C;
	}

	public Vector2D getBulletDirection() {
		return bulletDirection;
	}

	public void setBulletDirection(Vector2D bulletDirection) {
		this.bulletDirection = bulletDirection;
	}
	
	public long getBulletPeriod() {
		return bulletPeriod;
	}
	
	public void setBulletPeriod(long bulletPeriod) {
		this.bulletPeriod = bulletPeriod;
	}
	
	public long getLastBullet() {
		return lastBullet;
	}
	
	public void setLastBullet(long lastBullet) {
		this.lastBullet = lastBullet;
	}

	public float getBulletSpeed() {
		return bulletSpeed;
	}

	public void setBulletSpeed(float bulletSpeed) {
		this.bulletSpeed = bulletSpeed;
	}
	
	public void setBulletData(BulletData bullet) {
		this.bullet = bullet;  
	}
	
	public BulletData getBulletData() {
		return bullet;
	}

	@Override
	public String toString() {
		return "Bullet: (Speed: " + bulletSpeed + ", Period: " + bulletPeriod +
				", Last bullet: " + lastBullet + ", Direction: " + bulletDirection + ")";
	}
}