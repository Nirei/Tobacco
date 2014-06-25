package tobacco.core.systems;

import tobacco.core.components.Component;
import tobacco.core.components.ContainerComponent;
import tobacco.core.components.DurationComponent;
import tobacco.core.components.Entity;
import tobacco.core.components.RemoveComponent;

public class TimerSystem implements EngineSystem {

	private long lastCall = System.currentTimeMillis();

	public TimerSystem() {
	}

	private void checkDuration(Entity entity, long delta) {
		DurationComponent durComp = (DurationComponent) entity.getComponent(Component.DURATION_C);
		long left = durComp.getDuration() - delta;

		// Update duration left with delta
		synchronized (durComp) {
			durComp.setDuration(left);
		}
		// If time has expired, mark Entity for removal :(
		if (left <= 0)
			entity.putComponent(new RemoveComponent());
	}

	private void processTree(Entity entity, long delta) {
		if (entity.contains(Component.DURATION_C)) {
			checkDuration(entity, delta);
		}

		if (entity.contains(Component.CONTAINER_C)) {
			for (Entity e : (ContainerComponent) entity.getComponent(Component.CONTAINER_C)) {
				processTree(e, delta);
			}
		}
	}

	@Override
	public void work(Entity root) {
		long now = System.currentTimeMillis();
		long delta = now - lastCall;
		lastCall = now;
		processTree(root, delta);
	}

}
