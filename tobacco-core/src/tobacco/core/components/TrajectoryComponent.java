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
package tobacco.core.components;

import java.util.List;

import tobacco.core.util.ContentClass;
import tobacco.core.util.Vector2D;

public class TrajectoryComponent implements Component {

	private int step = 0;
	private boolean loop = false;
	private List<Vector2D> waypoints;
	
	public TrajectoryComponent() {}
	
	public TrajectoryComponent(List<Vector2D> waypoints) {
		setWaypoints(waypoints);
	}
	
	public TrajectoryComponent(List<Vector2D> waypoints, boolean loop) {
		setWaypoints(waypoints);
		this.loop = loop;
	}
	
	@ContentClass(Vector2D.class)
	public List<Vector2D> getWaypoints() {
		return waypoints;
	}
	
	public void setWaypoints(List<Vector2D> waypoints) {
		this.waypoints = waypoints;
	}

	public int getStep() {
		return step;
	}

	public void setStep(Integer step) {
		this.step = step;
	}

	public boolean isLoop() {
		return loop;
	}

	public void setLoop(Boolean loop) {
		this.loop = loop;
	}

	@Override
	public Type getComponentType() {
		return TRAJECTORY_C;
	}

	@Override
	public String toString() {
		return "Trajectory: [loop: " + loop + " step: " + step + " waypoints:" + waypoints + "]";
	};
}
