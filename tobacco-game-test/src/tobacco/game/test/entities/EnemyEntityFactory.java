package tobacco.game.test.entities;

import java.util.Arrays;
import java.util.List;

import tobacco.core.components.DebuggingComponent;
import tobacco.core.components.DrawableComponent;
import tobacco.core.components.Entity;
import tobacco.core.components.MovementComponent;
import tobacco.core.components.PositionComponent;
import tobacco.core.components.TrajectoryComponent;
import tobacco.core.movement.Trajectory;
import tobacco.core.movement.TraverseFunction;
import tobacco.core.util.Vector2D;

public class EnemyEntityFactory {

	String texture;
	Vector2D size;

	public EnemyEntityFactory(String texture, Vector2D size) {
		this.texture = texture;
		this.size = size;
	}

	public Entity create() {
		Entity entity = new Entity();

		DrawableComponent drawComp = new DrawableComponent(texture, size);
		drawComp.setSize(new Vector2D(50f, 50f));
		entity.putComponent(drawComp);
		entity.putComponent(new PositionComponent(new Vector2D(20f, 200f)));
		entity.putComponent(new MovementComponent(100f));
		entity.putComponent(new DebuggingComponent());
		TrajectoryComponent trajComp = new TrajectoryComponent();
		Trajectory traj = new Trajectory() {
			
			@Override
			public List<Vector2D> getWaypoints() {
				Vector2D p0 = new Vector2D(20f, 200f);
				Vector2D p1 = new Vector2D(200f, 200f);
				Vector2D p2 = new Vector2D(200f, 20f);
				Vector2D p3 = new Vector2D(20f, 20f);
				Vector2D[] points = {p0, p1, p2, p3};
				return Arrays.asList(points);
			}
			
			@Override
			public TraverseFunction getTraverseFunction() {
				return new TraverseFunction() {
					
					@Override
					public Vector2D path(List<Vector2D> waypoints, Vector2D position, int next) {
						Vector2D dest = waypoints.get(next);
						return Vector2D.minus(dest, position);
					}
				};
			}
		};
		trajComp.setTrajectory(traj);
		trajComp.setLoop(true);
		entity.putComponent(trajComp);

		return entity;
	}
}