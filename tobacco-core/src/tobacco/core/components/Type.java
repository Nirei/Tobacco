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
package tobacco.core.components;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents the type of a {@link Component}.
 * @author nirei
 */
public final class Type {

	private static final Map<String, Type> types = new HashMap<String, Type>();

	private final String name;
	private final Class<?> implementer;

	public Type(String name, Class<?> implementer) {
		this.name = name;
		this.implementer = implementer;
		types.put(name, this);
	}
	
	public static Type findByName(String name) {
		return types.get(name);
	}
	
	public String getName() {
		return name;
	}
	
	public Class<?> getImplementer() {
		return implementer;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Type other = (Type) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
}
