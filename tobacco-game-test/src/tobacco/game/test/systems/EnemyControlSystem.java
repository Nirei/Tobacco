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

import java.util.List;

import tobacco.core.components.ContainerComponent;
import tobacco.core.components.PositionComponent;
import tobacco.core.components.Type;
import tobacco.core.entities.Entity;
import tobacco.core.services.Directory;
import tobacco.core.systems.AbstractListSystem;
import tobacco.core.util.Vector2D;
import tobacco.game.test.components.DirectionComponent;
import tobacco.game.test.components.GameComponent;
import tobacco.game.test.components.GunComponent;

public class EnemyControlSystem extends AbstractListSystem {
	
	private static final Type[] requiredComponents = {
		GameComponent.ENEMY_C,
		GameComponent.GUN_C,
		GameComponent.CONTAINER_C,
		GameComponent.POSITION_C,
	};
	
	private List<Entity> players;

	public EnemyControlSystem() {
		super(requiredComponents);
	}

	@Override
	public void process(Entity entity, long delta) {
		if(qualifies(entity) && !entity.has(GameComponent.PLAYER_C)) {
			PositionComponent closestPlayerPos = null;
			float closestDist = Float.MAX_VALUE;
			
			PositionComponent enemyPos = (PositionComponent) entity.get(GameComponent.POSITION_C);
			ContainerComponent enemyContainer = (ContainerComponent) entity.get(GameComponent.CONTAINER_C);
			GunComponent enemyGun = (GunComponent) entity.get(GameComponent.GUN_C);
			
			// Select closest player
			for(Entity p : players) {
				PositionComponent playerPos;
				
				if(p.has(GameComponent.POSITION_C)) {
					playerPos = (PositionComponent) p.get(GameComponent.POSITION_C);
					float distance = Vector2D.minus(playerPos.getPosition(), enemyPos.getPosition()).module();
					if(distance < closestDist) {
						closestDist = distance;
						closestPlayerPos = playerPos;
					}
				}
			}
			
			// Shoot 'em
			if(closestPlayerPos != null) {
				for(Entity b : enemyContainer) {
					if(b.has(GameComponent.BULLET_DATA_C)) {
						DirectionComponent dirComp = (DirectionComponent) b.get(GameComponent.DIRECTION_C);
						dirComp.setDirection(Vector2D.minus(closestPlayerPos.getPosition(), enemyPos.getPosition()));
					}
				}
				enemyGun.setShooting(true);
			} else {
				enemyGun.setShooting(false);
			}
		}
	}

	@Override
	public void setUp() {
		players = Directory.getEntityService().findAll(GameComponent.PLAYER_C);
	}

	@Override
	public void tearDown() {}

}
