/*******************************************************************************
 * Tobacco - A portable and reusable game engine written in Java.
 * Copyright © 2016 Nirei
 *
 * This file is part of Tobacco
 *
 * Tobacco is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *******************************************************************************/
package tobacco.core.services;

import tobacco.core.entities.EntityService;
import tobacco.core.game.GameService;

/**
 * Directory of services provided by the engine.
 * @author nirei
 */
public final class Directory {

	private static DebuggingService debugging;
	private static GameService game;
	private static EntityService entities;
	private static RenderingService rendering;
	private static ConfigurationService configuration;
	
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
	
	public static ConfigurationService getConfigurationService() {
		return configuration;
	}
	
	public static void setConfigurationService(ConfigurationService configuration) {
		Directory.configuration = configuration;
	}
}
