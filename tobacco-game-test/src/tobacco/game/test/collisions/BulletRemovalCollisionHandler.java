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
package tobacco.game.test.collisions;

import tobacco.core.collision.Collision;
import tobacco.core.collision.CollisionHandler;
import tobacco.core.components.RemoveComponent;
import tobacco.core.entities.Entity;
import tobacco.game.test.components.GameComponent;

/**
 * This handler removes bullets after they've hit a player.
 * @author nirei
 *
 */
public class BulletRemovalCollisionHandler implements CollisionHandler {

	@Override
	public void handle(Collision col) {
		Entity e1 = col.getE1();
		Entity e2 = col.getE2();

		if(e1.has(GameComponent.BULLET_C) && e2.has(GameComponent.PLAYER_C)) {
			e1.add(new RemoveComponent());
		}
		if(e1.has(GameComponent.PLAYER_C) && e2.has(GameComponent.BULLET_C)) {
			e2.add(new RemoveComponent());
		}
	}

}
