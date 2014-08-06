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

import java.util.List;

import tobacco.core.components.Component;
import tobacco.core.components.Entity;
import tobacco.core.components.MovementComponent;
import tobacco.core.components.PositionComponent;
import tobacco.core.components.TrajectoryComponent;
import tobacco.core.util.Vector2D;

/**
 * Handles movement of things with trajectories!
 * 
 * @author nirei
 * 
 */
public class TrajectorySystem extends AbstractListSystem {

	private static final String[] requiredComponents = {
		Component.TRAJECTORY_C,
		Component.MOVEMENT_C,
		Component.POSITION_C };

	public TrajectorySystem() {
		super(requiredComponents);
	}

	@Override
	public void process(Entity entity) {
		if (qualifies(entity)) {
			TrajectoryComponent trajComp = (TrajectoryComponent) entity.get(Component.TRAJECTORY_C);

			int step = trajComp.getStep();
			List<Vector2D> waypoints = trajComp.getTrajectory().getWaypoints();
			if(step < waypoints.size()) {
				PositionComponent posComp = (PositionComponent) entity.get(Component.POSITION_C);
				MovementComponent movComp = (MovementComponent) entity.get(Component.MOVEMENT_C);

				Vector2D pos = posComp.getPosition();
				Vector2D dest = waypoints.get(step);
				Vector2D mov = trajComp.getTrajectory().getPathFunction().path(waypoints, pos, step);

				if(pos.isNear(dest, 1f)) {
						trajComp.setStep(++step);
					if(step >= waypoints.size()) {
						if(trajComp.isLoop()) trajComp.setStep(0);
						else mov = Vector2D.ZERO;
					}
				}
				
				movComp.setDirection(mov);
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
