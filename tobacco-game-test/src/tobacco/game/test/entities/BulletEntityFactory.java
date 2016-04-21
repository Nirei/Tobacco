/*******************************************************************************
 * Tobacco - A portable and reusable game engine written in Java.
 * Copyright © 2016 Nirei
 *
 * This file is part of Tobacco
 *
 * Tobacco is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *******************************************************************************/
package tobacco.game.test.entities;

import java.util.LinkedList;
import java.util.List;

import tobacco.core.components.Component;
import tobacco.core.components.RotationComponent;
import tobacco.core.components.SizeComponent;
import tobacco.core.components.TextureComponent;
import tobacco.core.entities.Entity;
import tobacco.core.entities.EntityFactory;
import tobacco.core.entities.EntityService;
import tobacco.core.util.Vector2D;
import tobacco.game.test.components.BulletDataComponent;
import tobacco.game.test.components.DamageComponent;
import tobacco.game.test.components.DirectionComponent;
import tobacco.render.pc.components.ZIndexComponent;

public class BulletEntityFactory extends EntityFactory {

	private TextureComponent texture;
	private Vector2D size;
	private Vector2D direction;
	private long bulletPeriod;
	private float bulletSpeed;
	private float damage;
	private Float rotation;
	
	public BulletEntityFactory(EntityService entServ, TextureComponent texture, Vector2D size, Vector2D direction, long bulletPeriod, float bulletSpeed, float damage) {
		super(entServ);
		this.texture = texture;
		this.size = size;
		this.direction = direction;
		this.bulletPeriod = bulletPeriod;
		this.bulletSpeed = bulletSpeed;
		this.damage = damage;
	}
	
	public BulletEntityFactory(EntityService entServ, TextureComponent texture, Vector2D size, Vector2D direction, long bulletPeriod, float bulletSpeed, float damage, float rotation) {
		this(entServ, texture, size, direction, bulletPeriod, bulletSpeed, damage);
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
		comps.add(new ZIndexComponent(100));
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
