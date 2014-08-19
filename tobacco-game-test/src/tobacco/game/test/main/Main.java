/*
* 	Tobacco - A portable and reusable game engine written in Java.
*	Copyright © 2014 Nirei
*
*	This file is part of Tobacco
*
*   Tobacco is free software: you can redistribute it and/or modify
*   it under the terms of the GNU General Public License as published by
*   the Free Software Foundation, either version 3 of the License, or
*   (at your option) any later version.
*
*   This program is distributed in the hope that it will be useful,
*   but WITHOUT ANY WARRANTY; without even the implied warranty of
*   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
*   GNU General Public License for more details.
*
*   You should have received a copy of the GNU General Public License
*   along with this program.  If not, see <http://www.gnu.org/licenses/>.
*
*/
package tobacco.game.test.main;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

import tobacco.core.components.Entity;
import tobacco.core.loader.Loader;
import tobacco.core.serialization.XmlEntityConverter;
import tobacco.core.systems.MainSystem;
import tobacco.game.test.loader.ManualLoader;

public class Main {
	
	public static final Class<?>[] MARSHALLIZABLES = new Class[] {
		tobacco.core.components.Entity.class,
		tobacco.core.components.CollisionMapComponent.class,
		tobacco.core.components.ContainerComponent.class,
		tobacco.core.components.DebuggingComponent.class,
		tobacco.core.components.DurationComponent.class,
		tobacco.core.components.KeymapComponent.class,
		tobacco.core.components.MovementComponent.class,
		tobacco.core.components.PlayerComponent.class,
		tobacco.core.components.PositionComponent.class,
		tobacco.core.components.RemoveComponent.class,
		tobacco.core.components.RotationComponent.class,
		tobacco.core.components.ScaleComponent.class,
		tobacco.core.components.ScreenComponent.class,
		tobacco.core.components.SizeComponent.class,
		tobacco.core.components.SolidityComponent.class,
		tobacco.core.components.TextureComponent.class,
		tobacco.core.components.TrajectoryComponent.class,
		tobacco.render.pc.components.MouseComponent.class,
		tobacco.game.test.components.TeamComponent.class,
		tobacco.game.test.components.DamageComponent.class,
		tobacco.game.test.components.HealthComponent.class,
		tobacco.game.test.components.BulletComponent.class,
		tobacco.game.test.components.DirectionComponent.class,
		tobacco.game.test.components.GunComponent.class,
	};


	public static void main(String[] args) throws JAXBException {
		Loader loader = new ManualLoader();
		Entity root = loader.loadEntityTree();
		MainSystem mainSystem = loader.loadMainSystem(root);
				
		XmlEntityConverter entToXml = new XmlEntityConverter(JAXBContext.newInstance(MARSHALLIZABLES));
		entToXml.toXml(root, System.out);

		while (true) {
			mainSystem.work(root);
			try {
				Thread.sleep(5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
