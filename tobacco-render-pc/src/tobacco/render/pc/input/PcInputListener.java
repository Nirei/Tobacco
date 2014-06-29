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
package tobacco.render.pc.input;

import com.jogamp.newt.event.KeyEvent;
import com.jogamp.newt.event.MouseEvent;

import tobacco.core.components.Entity;
import tobacco.core.components.KeymapComponent;
import tobacco.render.pc.components.MouseComponent;
import tobacco.render.pc.renderers.AbstractRenderer;

// TODO: A listener that adds itself? Don't think so...
public class PcInputListener implements CommonListener {

	private KeymapComponent keyMapComp = new KeymapComponent();
	private MouseComponent mouseComp = new MouseComponent();

	public PcInputListener(Entity root, AbstractRenderer renderer) {
		root.putComponent(keyMapComp);
		root.putComponent(mouseComp);
		renderer.addListener(this);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO: Diferenciar entre Shift, etc. derecho e izquierdo
		if (!e.isAutoRepeat()) {
			PcInputCode key = PcInputCode.getKeyByCode(e.getKeyCode());
			synchronized (keyMapComp) {
				keyMapComp.press(key);
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (!e.isAutoRepeat()) {
			PcInputCode key = PcInputCode.getKeyByCode(e.getKeyCode());
			synchronized (keyMapComp) {
				keyMapComp.release(key);
			}
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		PcInputCode key = PcInputCode.getKeyByCode(-e.getButton());
		synchronized (keyMapComp) {
			keyMapComp.press(key);
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		PcInputCode key = PcInputCode.getKeyByCode(-e.getButton());
		synchronized (keyMapComp) {
			keyMapComp.release(key);
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
	}

	@Override
	public void mouseWheelMoved(MouseEvent e) {
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mouseDragged(MouseEvent e) {
	}

}