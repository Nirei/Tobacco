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
package tobacco.core.movement;

import java.util.Arrays;
import tobacco.core.components.TrajectoryComponent;
import tobacco.core.util.Vector2D;

public final class SplineTrajectoryFactory {

	private static int resolution = 20;
	
	private SplineTrajectoryFactory() {}
	
	public static TrajectoryComponent create(Vector2D[] waypoints) {
		Vector2D[] spline = new Vector2D[resolution * waypoints.length];
		Vector2D p0, p1, m1, p2, m2, p3;
		int size = waypoints.length;
		
		for(int i=0; i<waypoints.length; i++) {
			p0 = waypoints[((i+size)-1) % size];
			p1 = waypoints[i];
			p2 = waypoints[(i+1) % size];
			p3 = waypoints[(i+2) % size];

			m1 = Vector2D.minus(p2, p0);
			m2 = Vector2D.minus(p3, p1);
			
			for(int j=0; j<resolution; j++) {
				float amount = (float) j / (float) resolution;
				spline[i*resolution + j] = hermite(p1, p2, m1, m2, amount);
			}
		}
		
		return new TrajectoryComponent(Arrays.asList(spline));
	}
	
	private static Vector2D hermite(Vector2D src, Vector2D dst, Vector2D dsrc, Vector2D ddst, float t) {
		float tSqr = t*t;
		float tCub = tSqr*t;
		Vector2D e1 = src.scale((2*tCub - 3*tSqr + 1));
		Vector2D e2 = dsrc.scale(tCub-2*tSqr+t);
		Vector2D e3 = dst.scale(-2*tCub + 3*tSqr);
		Vector2D e4 = ddst.scale(tCub - tSqr);

		return Vector2D.sum(Vector2D.sum(e1, e2), Vector2D.sum(e3, e4));
	}
}
