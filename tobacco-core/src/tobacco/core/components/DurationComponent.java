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
package tobacco.core.components;

public class DurationComponent implements Component {

	private long millis;

	public DurationComponent() {}
	
	public DurationComponent(long _millis) {
		millis = _millis;
	}

	@Override
	public Type getComponentType() {
		return DURATION_C;
	}

	public long getDuration() {
		return millis;
	}

	public void setDuration(long _millis) {
		millis = _millis;
	}

	@Override
	public String toString() {
		return "Duration: " + millis;
	}
}
