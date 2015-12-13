package tobacco.core.movement;

import java.util.Arrays;

import tobacco.core.components.TrajectoryComponent;
import tobacco.core.datatypes.GVector2D;

public final class SplineTrajectoryFactory {

	private static int resolution = 20;
	
	private SplineTrajectoryFactory() {}
	
	public static TrajectoryComponent create(GVector2D[] waypoints) {
		GVector2D[] spline = new GVector2D[resolution * waypoints.length];
		GVector2D p0, p1, m1, p2, m2, p3;
		int size = waypoints.length;
		
		for(int i=0; i<waypoints.length; i++) {
			p0 = waypoints[((i+size)-1) % size];
			p1 = waypoints[i];
			p2 = waypoints[(i+1) % size];
			p3 = waypoints[(i+2) % size];

			m1 = GVector2D.minus(p2, p0);
			m2 = GVector2D.minus(p3, p1);
			
			for(int j=0; j<resolution; j++) {
				float amount = (float) j / (float) resolution;
				spline[i*resolution + j] = hermite(p1, p2, m1, m2, amount);
			}
		}
		
		return new TrajectoryComponent(Arrays.asList(spline));
	}
	
	private static GVector2D hermite(GVector2D src, GVector2D dst, GVector2D dsrc, GVector2D ddst, float t) {
		float tSqr = t*t;
		float tCub = tSqr*t;
		GVector2D e1 = src.scale((2*tCub - 3*tSqr + 1));
		GVector2D e2 = dsrc.scale(tCub-2*tSqr+t);
		GVector2D e3 = dst.scale(-2*tCub + 3*tSqr);
		GVector2D e4 = ddst.scale(tCub - tSqr);

		return GVector2D.sum(GVector2D.sum(e1, e2), GVector2D.sum(e3, e4));
	}
}
