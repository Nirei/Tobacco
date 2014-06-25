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
public class TrajectorySystem extends AbstractTreeSystem {

	private static final String[] requiredComponents = {
			Component.TRAJECTORY_C, Component.MOVEMENT_C, Component.POSITION_C };

	public TrajectorySystem() {
		super(requiredComponents);
	}

	@Override
	public Object process(Entity entity, Object data) {
		if (qualifies(entity)) {
			PositionComponent posComp = (PositionComponent) entity
					.getComponent(Component.POSITION_C);
			MovementComponent movComp = (MovementComponent) entity
					.getComponent(Component.MOVEMENT_C);
			TrajectoryComponent trajComp = (TrajectoryComponent) entity
					.getComponent(Component.TRAJECTORY_C);

			Vector2D pos = posComp.getPosition();
			int step = trajComp.getStep();
			List<Vector2D> waypoints = trajComp.getTrajectory().getWaypoints();
			Vector2D current = waypoints.get(step);
			if (pos.isNear(current, 1f)) {
				trajComp.setStep(++step);
			}
			Vector2D mov = trajComp.getTrajectory().getTraverseFunction()
					.path(waypoints, pos, step);
			movComp.setDirection(mov);

		}
		return null;
	}

	@Override
	public void setUp() {
	}

	@Override
	public void tearDown() {
	}

}
