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
package tobacco.game.test.util;

import tobacco.core.collision.CollisionStrategy;
import tobacco.core.components.Component;
import tobacco.core.components.Entity;
import tobacco.core.components.PositionComponent;
import tobacco.core.components.SolidityComponent;
import tobacco.core.util.Vector2D;

/**
 * Simple collision strategy using hit circles.
 * @author nirei
 */
public class HitCircleCollisionStrategy implements CollisionStrategy {
	
	private static HitCircleCollisionStrategy singleton;
	
	private HitCircleCollisionStrategy() {}
	
	public static HitCircleCollisionStrategy getSingleton() {
		if(singleton == null)
			singleton = new HitCircleCollisionStrategy();
		return singleton;
	}

	@Override
	public boolean collides(Entity e1, Entity e2) {
		Vector2D pos1 = ((PositionComponent) e1.get(Component.POSITION_C)).getPosition();
		Vector2D pos2 = ((PositionComponent) e2.get(Component.POSITION_C)).getPosition();
		float rad1 = ((SolidityComponent) e1.get(Component.SOLIDITY_C)).getRadius();
		float rad2 = ((SolidityComponent) e1.get(Component.SOLIDITY_C)).getRadius();
		float centerDist = Vector2D.minus(pos1, pos2).module();

		return centerDist < rad1 + rad2;
	}

}
