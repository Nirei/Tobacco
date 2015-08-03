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
import tobacco.core.components.Type;
import tobacco.core.entities.Entity;
import tobacco.core.util.Vector2D;

/**
 * This system resets the movement of the player entity each tick after it has been applied.
 * 
 * @author nirei
 * 
 */
// TODO: Design "AbstractListenerSystem" for Systems that only work with a very specific Entity?
public class MovementResetSystem extends AbstractListSystem {

	private final static Type[] requiredComponents = {
		Component.PLAYER_C,
		Component.MOVEMENT_C };

	public MovementResetSystem() {
		super(requiredComponents);
	}

	@Override
	public void process(Entity entity) {
		if (qualifies(entity)) {
			MovementComponent movComp = (MovementComponent) entity.get(Component.MOVEMENT_C);
			movComp.setDirection(Vector2D.ZERO);
		}
	}

	@Override public void setUp() {}
	@Override public void tearDown() {}

}
