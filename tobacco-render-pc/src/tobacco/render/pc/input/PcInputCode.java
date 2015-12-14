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
package tobacco.render.pc.input;

import com.jogamp.newt.event.KeyEvent;

import tobacco.core.util.InputCode;

public enum PcInputCode implements InputCode {
	MOUSE_LEFT(-1),
	MOUSE_CENTER(-2),
	MOUSE_RIGHT(-3),

	UNKNOWN(0),
	
	KEY_A(KeyEvent.VK_A),
	KEY_B(KeyEvent.VK_B),
	KEY_C(KeyEvent.VK_C),
	KEY_D(KeyEvent.VK_D),
	KEY_E(KeyEvent.VK_E),
	KEY_F(KeyEvent.VK_F),
	KEY_G(KeyEvent.VK_G),
	KEY_H(KeyEvent.VK_H),
	KEY_I(KeyEvent.VK_I),
	KEY_J(KeyEvent.VK_J),
	KEY_K(KeyEvent.VK_K),
	KEY_L(KeyEvent.VK_L),
	KEY_M(KeyEvent.VK_M),
	KEY_N(KeyEvent.VK_N),
	KEY_O(KeyEvent.VK_O),
	KEY_P(KeyEvent.VK_P),
	KEY_Q(KeyEvent.VK_Q),
	KEY_R(KeyEvent.VK_R),
	KEY_S(KeyEvent.VK_S),
	KEY_T(KeyEvent.VK_T),
	KEY_U(KeyEvent.VK_U),
	KEY_V(KeyEvent.VK_V),
	KEY_W(KeyEvent.VK_W),
	KEY_X(KeyEvent.VK_X),
	KEY_Y(KeyEvent.VK_Y),
	KEY_Z(KeyEvent.VK_Z),
	KEY_ALT(KeyEvent.VK_ALT),
	KEY_ALT_GR(KeyEvent.VK_ALT_GRAPH),
	KEY_UP(KeyEvent.VK_UP),
	KEY_DOWN(KeyEvent.VK_DOWN),
	KEY_LEFT(KeyEvent.VK_LEFT),
	KEY_RIGHT(KeyEvent.VK_RIGHT),
	KEY_SPACE(KeyEvent.VK_SPACE),
	KEY_SCAPE(KeyEvent.VK_SPACE),
	KEY_ENTER(KeyEvent.VK_ENTER),
	KEY_ESCAPE(KeyEvent.VK_ESCAPE),
	KEY_SHIFT(KeyEvent.VK_SHIFT),
	KEY_CONTROL(KeyEvent.VK_CONTROL),
	KEY_META(KeyEvent.VK_META),
	KEY_WINDOWS(KeyEvent.VK_WINDOWS),
	KEY_MENU(KeyEvent.VK_CONTEXT_MENU),
	KEY_TAB(KeyEvent.VK_TAB),
	KEY_TILDE(KeyEvent.VK_TILDE);

	private final int code;

	private PcInputCode(int _code) {
		code = _code;
	}

	public static PcInputCode getKeyByCode(int _code) {
		for (int i = 0; i < values().length; i++) {
			if (values()[i].code == _code) {
				return values()[i];
			}
		}
		return UNKNOWN;
	}

	public int getCode() {
		return code;
	}

	public boolean isCharacter() {
		return code == 0;
	}

}
