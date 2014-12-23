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
import tobacco.core.components.DebuggingComponent;
import tobacco.core.components.DurationComponent;
import tobacco.core.components.MovementComponent;
import tobacco.core.components.PositionComponent;
import tobacco.core.components.RotationComponent;
import tobacco.core.components.SizeComponent;
import tobacco.core.components.SolidityComponent;
import tobacco.core.components.Type;
import tobacco.core.entities.Entity;
import tobacco.core.services.Directory;
import tobacco.core.systems.AbstractListSystem;
import tobacco.core.util.Vector2D;
import tobacco.game.test.components.BulletComponent;
import tobacco.game.test.components.DamageComponent;
import tobacco.game.test.components.DirectionComponent;
import tobacco.game.test.components.GameComponent;
import tobacco.game.test.components.GunComponent;
import tobacco.game.test.components.TeamComponent;
import tobacco.render.pc.components.TextureComponent;

public class GunSystem extends AbstractListSystem {

	private static final Type[] requiredComponents = {
		GameComponent.GUN_C,
		GameComponent.TEAM_C,
		GameComponent.CONTAINER_C,
		GameComponent.POSITION_C };

	public GunSystem() {
		super(requiredComponents);
	}
	
	private Entity createBullet(Vector2D pos, Entity bullet, TeamComponent team) {
		// TODO: Exception if bullet is missing necessary components
		BulletComponent bComp = (BulletComponent) bullet.get(GameComponent.BULLET_C);
		String texture = bComp.getBulletTexture();
		Vector2D size = ((SizeComponent) bullet.get(GameComponent.SIZE_C)).getSize();
		Vector2D dir = ((DirectionComponent) bullet.get(GameComponent.DIRECTION_C)).getDirection();
		float damage = ((DamageComponent) bullet.get(GameComponent.DAMAGE_C)).getDamage();
		
		Entity entity = Directory.getEntityService().create();
		TextureComponent textureComp = new TextureComponent(texture);
		SizeComponent sizeComp = new SizeComponent(size);
		entity.add(team);
		entity.add(textureComp);
		entity.add(sizeComp);
		entity.add(new DamageComponent(damage));
		entity.add(new PositionComponent(pos));
		entity.add(new MovementComponent(dir, bComp.getBulletSpeed()));
		entity.add(new RotationComponent(90f + Vector2D.angle(Vector2D.VERTICAL, dir).getDegrees()));
		entity.add(new DebuggingComponent());
		entity.add(new DurationComponent(1000));
		entity.add(new SolidityComponent(10f));

		return entity;
	}

	@Override
	public void process(Entity entity) {
		if (qualifies(entity)) {
			PositionComponent posComp = (PositionComponent) entity.get(Component.POSITION_C);
			ContainerComponent children = (ContainerComponent) entity.get(Component.CONTAINER_C);
			GunComponent gunComp = (GunComponent) entity.get(GameComponent.GUN_C);
			TeamComponent teamComp = (TeamComponent) entity.get(GameComponent.TEAM_C);
			
			if (gunComp.isShooting()) {
				for (Entity e : children) {
					if (e.has(GameComponent.BULLET_C)) {
						BulletComponent bulletComp = (BulletComponent) e.get(GameComponent.BULLET_C);
						long time = System.currentTimeMillis();
						long delta = time - bulletComp.getLastBullet();
						if (bulletComp.getBulletPeriod() <= delta) {
							bulletComp.setLastBullet(time);
							Entity bullet = createBullet(posComp.getPosition(), e, teamComp);
							((ContainerComponent) getRoot().get(Component.CONTAINER_C)).addChild(bullet);
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
