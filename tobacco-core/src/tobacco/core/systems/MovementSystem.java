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
package tobacco.core.systems;

import tobacco.core.components.Component;
import tobacco.core.components.MovementComponent;
import tobacco.core.components.PositionComponent;
import tobacco.core.components.Type;
import tobacco.core.entities.Entity;
import tobacco.core.util.Vector2D;

public class MovementSystem extends AbstractListSystem {

	private final static Type[] requiredComponents = {
		Component.POSITION_C,
		Component.MOVEMENT_C };
	private long lastCall = System.currentTimeMillis();
	private long delta = 0;

	public MovementSystem() {
		super(requiredComponents);
	}

	@Override
	public void process(Entity entity) {
		if (qualifies(entity)) {
			PositionComponent posComp;

			posComp = ((PositionComponent) entity.get(Component.POSITION_C));
			Vector2D position = posComp.getPosition();

			MovementComponent movComp = ((MovementComponent) entity.get(Component.MOVEMENT_C));
			Vector2D direction = movComp.getDirection();

			// Calculate new position
			if (!direction.isZero()) {
				float speed = movComp.getSpeed();

				Vector2D newPos = Vector2D.sum(position, direction.normalize().scale(speed * (delta / 1000f)));
				posComp.setPosition(newPos);
			}
		}
	}

	@Override
	public void setUp() {
		long now = System.currentTimeMillis();
		delta = now - lastCall;
		lastCall = now;
	}

	@Override
	public void tearDown() {
	}

}
