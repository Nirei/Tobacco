package tobacco.game.test.entities;

import tobacco.core.components.RotationComponent;
import tobacco.core.components.SizeComponent;
import tobacco.core.components.TextureComponent;
import tobacco.core.entities.Entity;
import tobacco.core.services.Directory;
import tobacco.core.util.Vector2D;
import tobacco.game.test.components.BulletDataComponent;
import tobacco.game.test.components.DamageComponent;
import tobacco.game.test.components.DirectionComponent;

public class BulletEntityFactory implements EntityFactory {

	private TextureComponent texture;
	private Vector2D size;
	private Vector2D direction;
	private long bulletPeriod;
	private float bulletSpeed;
	private float damage;
	private Float rotation;
	
	public BulletEntityFactory(TextureComponent texture, Vector2D size, Vector2D direction, long bulletPeriod, float bulletSpeed, float damage) {
		this.texture = texture;
		this.size = size;
		this.direction = direction;
		this.bulletPeriod = bulletPeriod;
		this.bulletSpeed = bulletSpeed;
		this.damage = damage;
	}
	
	public BulletEntityFactory(TextureComponent texture, Vector2D size, Vector2D direction, long bulletPeriod, float bulletSpeed, float damage, float rotation) {
		this(texture, size, direction, bulletPeriod, bulletSpeed, damage);
		this.rotation = rotation;
	}
	
	@Override
	public Entity create() {
		
		Entity bullet = Directory.getEntityService().create();
		bullet.add(new BulletDataComponent(bulletPeriod, bulletSpeed));
		bullet.add(texture);
		bullet.add(new SizeComponent(size));
		bullet.add(new DirectionComponent(direction));
		bullet.add(new DamageComponent(damage));
		if(rotation != null) bullet.add(new RotationComponent(rotation)); 
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
