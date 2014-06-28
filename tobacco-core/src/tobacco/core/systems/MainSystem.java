package tobacco.core.systems;

import java.util.ArrayList;
import java.util.List;

import tobacco.core.components.Entity;

/**
 * This is an special system in charge of every other system.
 * It mantains a list of EngineSystem and tells each one to
 * do their job sequentially each tick.
 * @author nirei
 *
 */
// TODO: Concurrency if it was necessary.
public class MainSystem implements EngineSystem {

	private List<EngineSystem> systemList = new ArrayList<EngineSystem>();

	@Override
	public void work(Entity entity) {
		for (EngineSystem s : systemList) {
			s.work(entity);
		}
	}

	public void addSystem(EngineSystem system) {
		systemList.add(system);
	}

	public void clearSystems() {
		systemList.clear();
	}
}
