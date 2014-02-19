package tobacco.core.systems;

import tobacco.core.entities.Entity;

/**
 * This contain the game's logic.
 * @author nirei
 */
public interface EngineSystem {

	/**
	 * Do this system's work.
	 * @param entity {@link Entity} to process.
	 */
	public void work(Entity entity);
}
