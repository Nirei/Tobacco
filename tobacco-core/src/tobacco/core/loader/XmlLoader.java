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
package tobacco.core.loader;

import java.net.URL;

import tobacco.core.components.Entity;
import tobacco.core.systems.MainSystem;

// TODO: This class
public class XmlLoader implements Loader {

	URL systemsList, entityTree;

	XmlLoader(URL _systemsList, URL _entityTree) {
		systemsList = _systemsList;
		entityTree = _entityTree;
	}

	@Override
	public MainSystem loadMainSystem(Entity root) {
		return null;
	}

	@Override
	public Entity loadEntityTree() {
		// TODO: Apéndice de método generado automáticamente
		return null;
	}

}
