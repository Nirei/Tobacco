package tobacco.core.systems.main;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import tobacco.core.systems.AbstractSystem;

/**
 * This is an special system in charge of every other system.
 * It mantains a list of EngineSystem and tells each one to
 * do their job when necessary.
 * @author nirei
 *
 */
public abstract class AbstractMainSystem extends AbstractSystem {

	private static final Logger LOGGER = Logger.getLogger(AbstractMainSystem.class.getName());
	
	protected List<AbstractSystem> systemList = new ArrayList<AbstractSystem>();
	
	public void add(AbstractSystem system) {
		LOGGER.log(Level.INFO, "Loaded system: " + system.getClass().getSimpleName());
		systemList.add(system);
	}
	
	public List<AbstractSystem> getSystems() {
		return new ArrayList<AbstractSystem>(systemList);
	}

	public void clear() {
		systemList.clear();
	}
}
