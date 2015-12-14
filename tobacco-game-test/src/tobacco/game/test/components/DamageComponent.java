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

public class DamageComponent implements GameComponent {
	
	private float damage;
	
	public DamageComponent() {}

	public DamageComponent(float damage) {
		this.damage = damage;
	}

	@Override
	public Type getComponentType() {
		return GameComponent.DAMAGE_C;
	}

	public float getDamage() {
		return damage;
	}

	public void setDamage(Float damage) {
		this.damage = damage;
	}

	@Override
	public String toString() {
		return "Damage: " + damage;
	}
}
