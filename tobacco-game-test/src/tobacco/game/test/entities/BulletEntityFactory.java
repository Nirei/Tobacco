package tobacco.game.test.entities;

import java.util.LinkedList;
import java.util.List;

import tobacco.core.components.Component;
import tobacco.core.components.RotationComponent;
import tobacco.core.components.SizeComponent;
import tobacco.core.components.TextureComponent;
import tobacco.core.entities.Entity;
import tobacco.core.entities.EntityFactory;
import tobacco.core.util.Vector2D;
import tobacco.game.test.components.BulletDataComponent;
import tobacco.game.test.components.DamageComponent;
import tobacco.game.test.components.DirectionComponent;

public class BulletEntityFactory extends EntityFactory {

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
		List<Component> comps = new LinkedList<Component>();
		comps.add(new BulletDataComponent(bulletPeriod, bulletSpeed));
		comps.add(texture);
		comps.add(new SizeComponent(size));
		comps.add(new DirectionComponent(direction));
		comps.add(new DamageComponent(damage));
		if(rotation != null) comps.add(new RotationComponent(rotation)); 
		return super.create(comps);
		
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
