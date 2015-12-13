package tobacco.core.services;

/**
 * Directory of services provided by the engine.
 * @author nirei
 */
public final class Directory {

	private static DebuggingService debugging;
	private static GameService game;
	private static EntityService entities;
	private static RenderingService rendering;
	
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
	
	public static RenderingService getRenderingService() {
		return rendering;
	}
	
	public static void setRenderingService(RenderingService rendering) {
		Directory.rendering = rendering;
	}
}
