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
package tobacco.game.test.entities;

import tobacco.core.components.ContainerComponent;
import tobacco.core.components.DebuggingComponent;
import tobacco.core.components.SolidityComponent;
import tobacco.core.components.TextureComponent;
import tobacco.core.components.MovementComponent;
import tobacco.core.components.PositionComponent;
import tobacco.core.components.SizeComponent;
import tobacco.core.components.TrajectoryComponent;
import tobacco.core.entities.Entity;
import tobacco.core.movement.SplineTrajectoryFactory;
import tobacco.core.services.Directory;
import tobacco.core.util.Vector2D;
import tobacco.game.test.components.DamageComponent;
import tobacco.game.test.components.EnemyComponent;
import tobacco.game.test.components.GunComponent;
import tobacco.game.test.components.HealthComponent;
import tobacco.game.test.components.TeamComponent;

public class EnemyEntityFactory {

	private TextureComponent texture;
	private Integer created = 0;
	private Vector2D points[][] = {
		// First fairy
		{new Vector2D(20f, 200f),
		new Vector2D(200f, 200f),
		new Vector2D(200f, 20f),
		new Vector2D(20f, 20f)},
		// Second fairy
		{new Vector2D(-260, 320),
		new Vector2D(260, 240),
		new Vector2D(-260, 160),
		new Vector2D(260, 80),
		new Vector2D(-260, 0),
		new Vector2D(260, -80),
		new Vector2D(-260, -160),
		new Vector2D(260, -240),
		new Vector2D(-260, -320)}
	};

	public EnemyEntityFactory(TextureComponent texture) {
		this.texture = texture;
	}

	public synchronized Entity create() {
		Entity entity = Directory.getEntityService().create();

		float width = texture.getWidth() / texture.getColumns();
		float height = texture.getHeight() / texture.getRows();
		entity.add(texture);
		entity.add(new SizeComponent(new Vector2D(width, height)));
		entity.add(new TeamComponent("ENEMY"));
		entity.add(new DamageComponent(100f));
		entity.add(new PositionComponent(points[created][0]));
		entity.add(new MovementComponent(200f));
		entity.add(new DebuggingComponent());
		entity.add(new SolidityComponent(10f));
		entity.add(new HealthComponent(100f));
		entity.add(new EnemyComponent());

		TrajectoryComponent trajComp = SplineTrajectoryFactory.create(points[created]);
		trajComp.setLoop(true);
		entity.add(trajComp);
		
		/* Shooting */
		ContainerComponent containerComponent = new ContainerComponent();
		GunComponent gunComponent = new GunComponent();
		TextureComponent bulletTexture = new TextureComponent("/tobacco/game/test/textures/fairy_bullet.png", 8, 16);
		BulletEntityFactory bef = new BulletEntityFactory(bulletTexture, new Vector2D(8f, 16f), new Vector2D(0f, 0f), 1000, 200f, 50f);
		containerComponent.addChild(bef.create());
		entity.add(gunComponent);
		entity.add(containerComponent);

		entity.add(new MovementComponent(200f));
		entity.add(new HealthComponent(100f));
		entity.add(new SolidityComponent(10f));
		entity.add(new TeamComponent("ENEMY"));

		created++;
		return entity;
	}
}