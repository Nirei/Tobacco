package tobacco.core.movement;

import java.util.List;

import tobacco.core.util.Vector2D;

public interface Trajectory {
	public List<Vector2D> getWaypoints();

	public TraverseFunction getTraverseFunction();
}