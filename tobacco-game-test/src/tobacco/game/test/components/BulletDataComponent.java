/*******************************************************************************
 * Tobacco - A portable and reusable game engine written in Java.
 * Copyright © 2015 Nirei
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
package tobacco.game.test.components;

import tobacco.core.components.Type;

public class BulletDataComponent implements GameComponent {

	private long bulletPeriod; // Inverse of frequency in ms
	private float bulletSpeed; // Movement speed
	private long lastBullet = System.currentTimeMillis();
	
	public BulletDataComponent() {}

	public BulletDataComponent(long bulletPeriod, float bulletSpeed) {
		this.bulletPeriod = bulletPeriod;
		this.bulletSpeed = bulletSpeed;
	}

	@Override
	public Type getComponentType() {
		return BULLET_DATA_C;
	}

	public long getBulletPeriod() {
		return bulletPeriod;
	}

	public void setBulletPeriod(Long bulletPeriod) {
		this.bulletPeriod = bulletPeriod;
	}

	public long getLastBullet() {
		return lastBullet;
	}

	public void setLastBullet(Long lastBullet) {
		this.lastBullet = lastBullet;
	}

	public float getBulletSpeed() {
		return bulletSpeed;
	}

	public void setBulletSpeed(Float bulletSpeed) {
		this.bulletSpeed = bulletSpeed;
	}

	@Override
	public String toString() {
		return "Bullet: (Speed: " + bulletSpeed + ", Period: " + bulletPeriod
				+ ", Last bullet: " + lastBullet + ")";
	}
}