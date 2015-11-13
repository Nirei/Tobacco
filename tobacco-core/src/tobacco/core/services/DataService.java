package tobacco.core.services;

import tobacco.core.systems.main.AbstractMainSystem;

/**
 * Provides access to entities and main system
 * @author nirei
 */
public interface DataService {

	public AbstractMainSystem getMainSystem();
	public void setMainSystem(AbstractMainSystem main);

}
