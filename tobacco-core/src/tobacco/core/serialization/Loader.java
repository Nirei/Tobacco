/*******************************************************************************
 * Tobacco - A portable and reusable game engine written in Java.
 * Copyright © 2015 Nirei
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
package tobacco.core.serialization;

import tobacco.core.entities.EntityService;
import tobacco.core.game.GameService;
import tobacco.core.services.ConfigurationService;
import tobacco.core.services.Directory;
import tobacco.core.services.RenderingService;

public abstract class Loader {

	public abstract EntityService loadEntityService();
	public abstract GameService loadGameService();
	public abstract RenderingService loadRenderingService();
	public abstract ConfigurationService loadConfigurationService();
	
	public void load() {
		Directory.setEntityService(loadEntityService());
		Directory.setGameService(loadGameService());
		Directory.setRenderingService(loadRenderingService());
		Directory.setConfigurationService(loadConfigurationService());
	}

}
