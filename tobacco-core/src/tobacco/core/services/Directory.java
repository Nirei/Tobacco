package tobacco.core.services;

import tobacco.core.entities.DefaultEntityService;

/**
 * Directory of services provided by the engine.
 * @author nirei
 */
public final class Directory {

	private static DebuggingService debugging = new NullDebuggingService();
	private static DataService data = new DefaultDataService();
	private static EntityService entities = new DefaultEntityService();
	
	private Directory() {}

	public static DebuggingService getDebuggingService() {
		return debugging;
	}
	
	public static void setDebuggingService(DebuggingService debugging) {
		Directory.debugging = debugging;
	}
	
	public static DataService getDataService() {
		return data;
	}
	
	public static void setDataService(DataService data) {
		Directory.data = data;
	}
	
	public static EntityService getEntityService() {
		return entities;
	}
	
	public static void setEntityService(EntityService entities) {
		Directory.entities = entities;
	}
}
