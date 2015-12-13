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

import java.util.LinkedList;
import java.util.List;

import tobacco.core.components.Component;
import tobacco.core.components.ContainerComponent;
import tobacco.core.components.DebuggingComponent;
import tobacco.core.components.SolidityComponent;
import tobacco.core.components.TextureComponent;
import tobacco.core.components.MovementComponent;
import tobacco.core.components.PositionComponent;
import tobacco.core.components.SizeComponent;
import tobacco.core.components.TrajectoryComponent;
import tobacco.core.datatypes.GVector2D;
import tobacco.core.entities.Entity;
import tobacco.core.entities.EntityFactory;
import tobacco.core.movement.SplineTrajectoryFactory;
import tobacco.core.services.EntityService;
import tobacco.game.test.components.DamageComponent;
import tobacco.game.test.components.EnemyComponent;
import tobacco.game.test.components.GunComponent;
import tobacco.game.test.components.HealthComponent;
import tobacco.game.test.components.TeamComponent;
import tobacco.render.pc.components.ZIndexComponent;

public class EnemyEntityFactory extends EntityFactory {

	private BulletEntityFactory bef;
	private TextureComponent texture;
	private Integer created = 0;
	private GVector2D points[][] = {
		// First fairy
		{new GVector2D(20f, 200f),
		new GVector2D(200f, 200f),
		new GVector2D(200f, 20f),
		new GVector2D(20f, 20f)},
		// Second fairy
		{new GVector2D(-260, 320),
		new GVector2D(260, 240),
		new GVector2D(-260, 160),
		new GVector2D(260, 80),
		new GVector2D(-260, 0),
		new GVector2D(260, -80),
		new GVector2D(-260, -160),
		new GVector2D(260, -240),
		new GVector2D(-260, -320)}
	};

	public EnemyEntityFactory(EntityService entServ, TextureComponent texture) {
		super(entServ);
		this.texture = texture;
		TextureComponent bulletTexture = new TextureComponent("/tobacco/game/test/textures/fairy_bullet.png", 8, 16);
		bef = new BulletEntityFactory(entServ, bulletTexture, new GVector2D(8f, 16f), new GVector2D(0f, 0f), 1000, 200f, 50f);
	}

	@Override
	public Entity create() {

		List<Component> comps = new LinkedList<Component>();
		float width = texture.getWidth() / texture.getColumns();
		float height = texture.getHeight() / texture.getRows();
		comps.add(texture);
		comps.add(new SizeComponent(new GVector2D(width, height)));
		comps.add(new TeamComponent("ENEMY"));
		comps.add(new DamageComponent(100f));
		comps.add(new PositionComponent(points[created][0]));
		comps.add(new MovementComponent(50f));
		comps.add(new DebuggingComponent());
		comps.add(new SolidityComponent(10f));
		comps.add(new HealthComponent(100f));
		comps.add(new EnemyComponent());

		TrajectoryComponent trajComp = SplineTrajectoryFactory.create(points[created]);
		trajComp.setLoop(true);
		comps.add(trajComp);
		
		/* Shooting */
		ContainerComponent containerComponent = new ContainerComponent();
		GunComponent gunComponent = new GunComponent();
		containerComponent.addChild(bef.create());
		comps.add(gunComponent);
		comps.add(containerComponent);

		comps.add(new ZIndexComponent());
		comps.add(new MovementComponent(120f));
		comps.add(new HealthComponent(100f));
		comps.add(new SolidityComponent(10f));
		comps.add(new TeamComponent("ENEMY"));

		created++;
		return super.create(comps);
	}
}