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
package tobacco.game.test.systems;

import tobacco.core.components.RemoveComponent;
import tobacco.core.components.Type;
import tobacco.core.entities.Entity;
import tobacco.core.systems.AbstractListSystem;
import tobacco.game.test.components.GameComponent;
import tobacco.game.test.components.HealthComponent;

/**
 * Manages entities' deaths.
 * 
 * @author nirei
 * 
 */
public class HealthSystem extends AbstractListSystem {

	private static final Type[] requiredComponents = { GameComponent.HEALTH_C };

	public HealthSystem() {
		super(requiredComponents);
	}

	@Override
	public void process(Entity entity, long delta) {
		if (qualifies(entity)) {
			HealthComponent healthComponent = (HealthComponent) entity.get(GameComponent.HEALTH_C);
			if (healthComponent.getHealth() <= 0f) {
				entity.add(new RemoveComponent());
			}
		}
	}

	@Override
	public void setUp() {
	}

	@Override
	public void tearDown() {
	}

}
