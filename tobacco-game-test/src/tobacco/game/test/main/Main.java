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
package tobacco.game.test.main;

import static tobacco.render.pc.input.PcInputCode.KEY_DOWN;
import static tobacco.render.pc.input.PcInputCode.KEY_ESCAPE;
import static tobacco.render.pc.input.PcInputCode.KEY_LEFT;
import static tobacco.render.pc.input.PcInputCode.KEY_RIGHT;
import static tobacco.render.pc.input.PcInputCode.KEY_SHIFT;
import static tobacco.render.pc.input.PcInputCode.KEY_UP;
import static tobacco.render.pc.input.PcInputCode.KEY_X;
import static tobacco.render.pc.input.PcInputCode.KEY_Z;

import java.util.logging.Level;
import java.util.logging.Logger;

import tobacco.core.components.ContainerComponent;
import tobacco.core.components.TextureComponent;
import tobacco.core.serialization.Loader;
import tobacco.core.services.Directory;
import tobacco.core.util.Vector2D;
import tobacco.core.xml.XmlLoader;
import tobacco.game.test.components.GameComponent;
import tobacco.game.test.entities.PlayerEntityFactory;
import tobacco.game.test.loader.ManualLoader;
import tobacco.tools.ui.EntityWindow;

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
			Logger.getGlobal().log(Level.SEVERE, "Coulnd't load components");
			System.exit(1);
		}
	}

	public static void main(String[] args) {
		Loader manLoader = new ManualLoader();
		Loader xmlLoader = new XmlLoader("","levels/testing.xml", "config/game.properties");
		//Saver xmlSaver = new XmlSaver("", FIXME"levels/output.xml");
		Directory.setEntityService(xmlLoader.loadEntityService());
		Directory.setGameService(manLoader.loadGameService());
		Directory.setRenderingService(manLoader.loadRenderingService());
		
		// Create the player
		TextureComponent playerTexture = new TextureComponent("textures/reimusprite.png", 128, 48, 4, 1, 4);
		PlayerEntityFactory pef = new PlayerEntityFactory(Directory.getEntityService(), playerTexture, new Vector2D(30, 44));
		pef.setMovementKeys(KEY_UP, KEY_DOWN, KEY_LEFT, KEY_RIGHT);
		pef.setActionKeys(KEY_Z, KEY_X, KEY_SHIFT, KEY_ESCAPE);
		((ContainerComponent) Directory.getEntityService().getRoot().get(GameComponent.CONTAINER_C)).addChild(pef.create());
		
		Directory.getGameService().start();
		Directory.getRenderingService().start();
		//xmlSaver.saveEntityService(Directory.getEntityService());
		
		EntityWindow entWin = new EntityWindow("Entities");
		entWin.setVisible(true);
	}
}
