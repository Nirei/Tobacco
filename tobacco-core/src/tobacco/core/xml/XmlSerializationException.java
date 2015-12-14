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
package tobacco.core.xml;

public class XmlSerializationException extends Exception {
	
	private Object element;

	public XmlSerializationException(String message, Object element) {
		super(message);
		this.element = element;
	}
	
	public XmlSerializationException(String message, Throwable cause, Object element) {
		super(message, cause);
		this.element = element;
	}
	
	public XmlSerializationException(Throwable cause, Object element) {
		super(cause);
		this.element = element;
	}
	
	public Object getElement() {
		return element;
	}

	@Override
	public void printStackTrace() {
		System.out.println(element);
		super.printStackTrace();
	}
	
	private static final long serialVersionUID = 2511585420020146585L;

}
