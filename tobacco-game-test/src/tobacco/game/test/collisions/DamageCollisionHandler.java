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
package tobacco.game.test.collisions;

import tobacco.core.collision.Collision;
import tobacco.core.collision.CollisionHandler;
import tobacco.core.entities.Entity;
import tobacco.game.test.components.DamageComponent;
import tobacco.game.test.components.GameComponent;
import tobacco.game.test.components.HealthComponent;
import tobacco.game.test.components.TeamComponent;

/**
 * This system detects damaging collisions and does as needed.
 * It applies damage and marks the bullet for removal.
 * @author nirei
 *
 */
public class DamageCollisionHandler implements CollisionHandler {

	private void damage(Entity e1, Entity e2) {
		if(e1.has(GameComponent.DAMAGE_C) && e2.has(GameComponent.HEALTH_C)) {
			DamageComponent damComp = (DamageComponent) e1.get(GameComponent.DAMAGE_C);
			HealthComponent healthComp = (HealthComponent) e2.get(GameComponent.HEALTH_C);
			TeamComponent t1 = (TeamComponent) e1.get(GameComponent.TEAM_C);
			TeamComponent t2 = (TeamComponent) e2.get(GameComponent.TEAM_C);

			if(e1.getID() == 0 || e2.getID() == 0) {
				System.out.println(e1 + " ==> " + t1 + " / " + e2 + " ==> " + t2);
			}
			// Are entities teamed?
			if(t1 == null || t2 == null) {
				// They aren't, do damage anyway
				healthComp.hurt(damComp.getDamage());
			} else if(!t1.equals(t2)) {
				// They are, check if they belong to different teams and do the thing
				healthComp.hurt(damComp.getDamage());
			}
		}
	}

	@Override
	public void handle(Collision c) {
		Entity e1 = c.getE1();
		Entity e2 = c.getE2();

		damage(e1, e2);
		damage(e2, e1);
	}

}
