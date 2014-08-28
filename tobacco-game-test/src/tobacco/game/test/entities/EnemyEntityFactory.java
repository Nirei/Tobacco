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

import java.util.Arrays;
import java.util.List;

import tobacco.core.components.DebuggingComponent;
import tobacco.core.components.SolidityComponent;
import tobacco.core.components.Entity;
import tobacco.core.components.MovementComponent;
import tobacco.core.components.PositionComponent;
import tobacco.core.components.SizeComponent;
import tobacco.core.components.TrajectoryComponent;
import tobacco.core.movement.RectilinearPath;
import tobacco.core.movement.Trajectory;
import tobacco.core.util.Vector2D;
import tobacco.game.test.components.HealthComponent;
import tobacco.render.pc.components.TextureComponent;

public class EnemyEntityFactory {

	String texture;
	Vector2D size;

	public EnemyEntityFactory(String texture, Vector2D size) {
		this.texture = texture;
		this.size = size;
	}

	public Entity create() {
		Entity entity = new Entity();

		TextureComponent textureComp = new TextureComponent(texture);
		SizeComponent sizeComp = new SizeComponent(size);
		entity.add(textureComp);
		entity.add(sizeComp);
		entity.add(new PositionComponent(new Vector2D(20f, 200f)));
		entity.add(new MovementComponent(100f));
		entity.add(new DebuggingComponent());
		entity.add(new SolidityComponent(10f));
		entity.add(new HealthComponent(100f));
		TrajectoryComponent trajComp = new TrajectoryComponent();
		Trajectory traj = new Trajectory(RectilinearPath.getInstance()) {
			
			@Override
			public List<Vector2D> getWaypoints() {
				Vector2D p0 = new Vector2D(20f, 200f);
				Vector2D p1 = new Vector2D(200f, 200f);
				Vector2D p2 = new Vector2D(200f, 20f);
				Vector2D p3 = new Vector2D(20f, 20f);
				Vector2D points[] = {p0, p1, p2, p3};
				return Arrays.asList(points);
			}
		};
		trajComp.setTrajectory(traj);
		trajComp.setLoop(true);
		entity.add(trajComp);

		return entity;
	}
}