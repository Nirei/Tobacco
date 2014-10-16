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
package tobacco.core.movement;

import java.util.List;

import tobacco.core.services.DebuggingService;
import tobacco.core.services.Directory;
import tobacco.core.util.Line2D;
import tobacco.core.util.Vector2D;

public final class SplinePath implements PathFunction {
	
	private static SplinePath instance;
	
	private SplinePath() {}
	
	private Vector2D hermite(Vector2D p0, Vector2D p1, Vector2D m0, Vector2D m1, float t) {
		float tSqr = t*t;
		float tCub = tSqr*t;
		Vector2D e1 = p0.scale((2*tCub - 3*tSqr + 1));
		Vector2D e2 = m0.scale(tCub-2*tSqr+t);
		Vector2D e3 = p1.scale(-2*tCub + 3*tSqr);
		Vector2D e4 = m1.scale(tCub - tSqr);

		return Vector2D.sum(Vector2D.sum(e1, e2), Vector2D.sum(e3, e4));
	}

	private Vector2D hermiteDerivative(Vector2D p0, Vector2D p1, Vector2D m0, Vector2D m1, float t) {
		float tSqr = t*t;
		Vector2D e1 = p0.scale((6*tSqr - 6*t));
		Vector2D e2 = m0.scale(3*tSqr - 4*t + 1);
		Vector2D e3 = p1.scale(6*t - 6*tSqr);
		Vector2D e4 = m1.scale(3*tSqr - 2*t);

		return Vector2D.sum(Vector2D.sum(e1, e2), Vector2D.sum(e3, e4));
	}

	@Override
	public Vector2D path(List<Vector2D> waypoints, Vector2D position, int next) {
		Vector2D p0, p1, m1, p2, m2, p3;
		int last = waypoints.size()-1;

		// TODO: This is SO BAD
		p0 = waypoints.get(next == 0 ? last : next-1);
		p1 = position;
		p2 = waypoints.get(next);
		p3 = waypoints.get(next == last ? 0 : next+1);

		m1 = Vector2D.minus(p1, p0);
		if(next > 1) { m1 = m1.scale(0.5f); }
		m2 = Vector2D.minus(p3, p2);
	
		float amount = Vector2D.dot(Vector2D.minus(position, p0), Vector2D.minus(p2, p0).normalize()) / Vector2D.minus(p2, p0).module();
		Vector2D result = hermiteDerivative(p1, p2, m1, m2, amount);
		System.out.println("Last: " + p0 + " Next: " + p2 + " Pos: " + position + " Amo: " + amount + " Dir: " + result);
		DebuggingService debug = Directory.getDebuggingService();
		
		int m = 0;
		for(float f=0; f<1f; f+=0.01f) {
			debug.displayPoint("spline" + m++, hermite(p1, p2, m1, m2, f));
		}
		
		for(int i=0; i<waypoints.size(); i++) {
			debug.displayPoint("point" + i, waypoints.get(i));
		}
		debug.displayVector("path_amo", new Line2D(p0, Vector2D.sum(p0, Vector2D.minus(p2, p0).scale(amount))));
		debug.displayVector("dir", new Line2D(position, Vector2D.minus(position, result)));
		
		return result;
	}

	public static SplinePath getInstance() {
		if(instance == null) {
			instance = new SplinePath();
		}
		return instance;
	}
}
