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
package tobacco.core.services;

import tobacco.core.components.Entity;
import tobacco.core.systems.main.AbstractMainSystem;

public class DefaultDataService implements DataService {

	private Entity root = new Entity();
	private AbstractMainSystem main = null;

	@Override
	public Entity getRoot() {
		return root;
	}

	@Override
	public void setRoot(Entity root) {
		this.root = root;
	}

	@Override
	public AbstractMainSystem getMainSystem() {
		return main;
	}

	@Override
	public void setMainSystem(AbstractMainSystem main) {
		this.main = main;
	}
}
