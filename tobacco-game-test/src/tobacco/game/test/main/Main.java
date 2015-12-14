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
package tobacco.game.test.main;

import tobacco.core.serialization.Loader;
import tobacco.core.services.Directory;
import tobacco.core.xml.XmlLoader;
import tobacco.game.test.loader.ManualLoader;

public class Main {
	
	static {
		// This block of code initializes components from all
		// the hierarchy. This is necessary since component
		// initialization must be done in order for Type to
		// gather all the available component information.
		try {
			ClassLoader systemCL = ClassLoader.getSystemClassLoader();
			Class.forName("tobacco.core.components.Component",true,systemCL);
			Class.forName("tobacco.render.pc.components.RendererComponent",true,systemCL);
			Class.forName("tobacco.game.test.components.GameComponent",true,systemCL);
		} catch (ClassNotFoundException e) {
			System.err.println("Coulnd't load components");
			System.exit(1);
		}
	}

	public static void main(String[] args) {
		// TODO: Substitute all unnecessary lists for arrays.

		Loader manLoader = new ManualLoader();
		Loader xmlLoader = new XmlLoader("","output.xml");
		//Saver xmlSaver = new XmlSaver("", "output.xml");
		Directory.setEntityService(xmlLoader.loadEntityService());
		Directory.setGameService(manLoader.loadGameService());
		Directory.setRenderingService(manLoader.loadRenderingService());
		//manLoader.load();
		Directory.getGameService().start();
		Directory.getRenderingService().start();
		//xmlSaver.saveEntityService(Directory.getEntityService());
	}
}
