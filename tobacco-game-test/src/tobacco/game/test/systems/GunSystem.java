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
package tobacco.game.test.systems;

import tobacco.core.components.Component;
import tobacco.core.components.ContainerComponent;
import tobacco.core.components.Entity;
import tobacco.core.components.PositionComponent;
import tobacco.core.systems.AbstractListSystem;
import tobacco.game.test.components.BulletComponent;
import tobacco.game.test.components.GameComponent;
import tobacco.game.test.components.GunComponent;
import tobacco.game.test.entities.BulletEntityFactory;

public class GunSystem extends AbstractListSystem {

	private static final String[] requiredComponents = {
		GameComponent.GUN_C,
		Component.CONTAINER_C,
		Component.POSITION_C };
	private BulletEntityFactory bulletEntityFactory;

	public GunSystem() {
		super(requiredComponents);
		bulletEntityFactory = new BulletEntityFactory();
	}

	@Override
	public void process(Entity entity) {
		if (qualifies(entity)) {
			PositionComponent posComp = (PositionComponent) entity.get(Component.POSITION_C);
			ContainerComponent children = (ContainerComponent) entity.get(Component.CONTAINER_C);
			GunComponent gunComp = (GunComponent) entity.get(GameComponent.GUN_C);

			if (gunComp.isShooting()) {
				for (Entity e : children) {
					if (e.has(GameComponent.BULLET_C)) {
						BulletComponent bulletComp = (BulletComponent) e.get(GameComponent.BULLET_C);
						long time = System.currentTimeMillis();
						long delta = time - bulletComp.getLastBullet();
						if (bulletComp.getBulletPeriod() <= delta) {
							bulletComp.setLastBullet(time);
							Entity bullet = bulletEntityFactory.create(posComp.getPosition(), e);
							((ContainerComponent) getRootEntity().get(Component.CONTAINER_C)).addChild(bullet);
						}
					}
				}
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
