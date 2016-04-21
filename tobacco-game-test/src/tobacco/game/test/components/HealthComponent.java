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
package tobacco.game.test.components;

import tobacco.core.components.Type;

public class HealthComponent implements GameComponent {

	private float health;
	
	public HealthComponent() {}

	public HealthComponent(float health) {
		this.health = health;
	}

	@Override
	public Type getComponentType() {
		return GameComponent.HEALTH_C;
	}

	public float getHealth() {
		return health;
	}

	public void setHealth(Float health) {
		this.health = health;
	}
	
	public void hurt(Float damage) {
		health -= damage;
	}
	
	@Override
	public String toString() {
		return "Health: " + health;
	}

}
