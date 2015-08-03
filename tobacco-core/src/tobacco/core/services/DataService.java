package tobacco.core.services;

import tobacco.core.systems.main.AbstractMainSystem;

// TODO: Make every access to root go through here
/**
 * Provides access to the current entity tree and main system
 * @author nirei
 */
public interface DataService {

	public AbstractMainSystem getMainSystem();
	public void setMainSystem(AbstractMainSystem main);

}