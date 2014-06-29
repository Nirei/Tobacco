/*
* 	Tobacco - A portable and reusable game engine written in Java.
*	Copyright © 2014 Nirei
*
*	This file is part of Tobacco
*
*   Tobacco is free software: you can redistribute it and/or modify
*   it under the terms of the GNU General Public License as published by
*   the Free Software Foundation, either version 3 of the License, or
*   (at your option) any later version.
*
*   This program is distributed in the hope that it will be useful,
*   but WITHOUT ANY WARRANTY; without even the implied warranty of
*   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
*   GNU General Public License for more details.
*
*   You should have received a copy of the GNU General Public License
*   along with this program.  If not, see <http://www.gnu.org/licenses/>.
*
*/
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

	public BulletComponent(BulletData bullet, Vector2D bulletDirection,
			long bulletPeriod, float bulletSpeed) {
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
		return "Bullet: (Speed: " + bulletSpeed + ", Period: " + bulletPeriod
				+ ", Last bullet: " + lastBullet + ", Direction: "
				+ bulletDirection + ")";
	}
}