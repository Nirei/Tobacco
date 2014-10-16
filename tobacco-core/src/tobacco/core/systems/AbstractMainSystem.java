package tobacco.core.systems;

import java.util.ArrayList;
import java.util.List;

/**
 * This is an special system in charge of every other system.
 * It mantains a list of EngineSystem and tells each one to
 * do their job when necessary.
 * @author nirei
 *
 */
public abstract class AbstractMainSystem implements EngineSystem {

	protected List<EngineSystem> systemList = new ArrayList<EngineSystem>();
	
	public void addSystem(EngineSystem system) {
		systemList.add(system);
	}

	public void clearSystems() {
		systemList.clear();
	}
}
