package tobacco.game.test.entities;

import tobacco.core.components.SizeComponent;
import tobacco.core.entities.Entity;
import tobacco.core.services.Directory;
import tobacco.core.util.Vector2D;
import tobacco.game.test.components.BulletDataComponent;
import tobacco.game.test.components.DamageComponent;
import tobacco.game.test.components.DirectionComponent;

public class BulletEntityFactory implements EntityFactory {

	private String texturePath;
	private Vector2D size;
	private Vector2D direction;
	private long bulletPeriod;
	private float bulletSpeed;
	private float damage;
	
	public BulletEntityFactory(String texturePath, Vector2D size, Vector2D direction, long bulletPeriod, float bulletSpeed, float damage) {
		this.texturePath = texturePath;
		this.size = size;
		this.direction = direction;
		this.bulletPeriod = bulletPeriod;
		this.bulletSpeed = bulletSpeed;
		this.damage = damage;
	}
	
	@Override
	public Entity create() {
		
		Entity bullet = Directory.getEntityService().create();
		bullet.add(new BulletDataComponent(texturePath, bulletPeriod, bulletSpeed));
		bullet.add(new SizeComponent(size));
		bullet.add(new DirectionComponent(direction));
		bullet.add(new DamageComponent(damage));
		return bullet;
		
	}
	
	public Vector2D getDirection() {
		return direction;
	}

	public void setDirection(Vector2D direction) {
		this.direction = direction;
	}

	public long getBulletPeriod() {
		return bulletPeriod;
	}

	public void setBulletPeriod(long bulletPeriod) {
		this.bulletPeriod = bulletPeriod;
	}

	public float getBulletSpeed() {
		return bulletSpeed;
	}

	public void setBulletSpeed(float bulletSpeed) {
		this.bulletSpeed = bulletSpeed;
	}

}
