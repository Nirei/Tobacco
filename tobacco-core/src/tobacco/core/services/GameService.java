package tobacco.core.services;

import tobacco.core.systems.main.AbstractMainSystem;

/**
 * Provides access to entities and main system
 * @author nirei
 */
public interface GameService {

	public AbstractMainSystem getMainSystem();
	public void setMainSystem(AbstractMainSystem main);
	public void start();
	public void stop();
}
