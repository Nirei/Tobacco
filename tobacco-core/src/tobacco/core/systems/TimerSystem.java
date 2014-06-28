package tobacco.core.systems;

import tobacco.core.components.Component;
import tobacco.core.components.DurationComponent;
import tobacco.core.components.Entity;
import tobacco.core.components.RemoveComponent;

public class TimerSystem extends AbstractListSystem {

	private static final String[] requiredComponents = { Component.DURATION_C };
	private long lastCall = System.currentTimeMillis();
	private long delta;
	
	public TimerSystem() {
		super(requiredComponents);
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

	@Override
	public void setUp() {
		long now = System.currentTimeMillis();
		delta = now - lastCall;
		lastCall = now;
	}

	@Override
	public void process(Entity entity) {
		if(qualifies(entity)) {
			checkDuration(entity, delta);
		}
	}

	@Override public void tearDown() {}
}
