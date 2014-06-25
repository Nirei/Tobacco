package tobacco.core.movement;

import java.util.List;

import tobacco.core.util.Vector2D;

public interface TraverseFunction {

	public Vector2D path(List<Vector2D> waypoints, Vector2D position, int next);

}