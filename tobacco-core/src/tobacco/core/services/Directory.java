package tobacco.core.services;

import tobacco.core.entities.DefaultEntityService;

/**
 * Directory of services provided by the engine.
 * @author nirei
 */
public final class Directory {

	private static DebuggingService debugging = new NullDebuggingService();
	private static GameService game = new DefaultGameService();
	private static EntityService entities = new DefaultEntityService();
	
	private Directory() {}

	public static DebuggingService getDebuggingService() {
		return debugging;
	}
	
	public static void setDebuggingService(DebuggingService debugging) {
		Directory.debugging = debugging;
	}
	
	public static GameService getGameService() {
		return game;
	}
	
	public static void setGameService(GameService game) {
		Directory.game = game;
	}
	
	public static EntityService getEntityService() {
		return entities;
	}
	
	public static void setEntityService(EntityService entities) {
		Directory.entities = entities;
	}
}
