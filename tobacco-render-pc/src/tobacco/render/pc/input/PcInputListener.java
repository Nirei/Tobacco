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
import tobacco.core.util.Vector2D;
import tobacco.render.pc.components.MouseComponent;
import tobacco.render.pc.renderers.AbstractRenderer;

// TODO: A listener that adds itself? Don't think so...
// TODO: Mouse coords should be given on ingame position instead absolute screen pos
public class PcInputListener implements CommonListener {

	private KeymapComponent keyMapComp = new KeymapComponent();
	private MouseComponent mouseComp = new MouseComponent();

	public PcInputListener(Entity root, AbstractRenderer renderer) {
		root.put(keyMapComp);
		root.put(mouseComp);
		renderer.addListener(this);
	}
	
	private Vector2D getMousePosition(MouseEvent e) {
		return new Vector2D(e.getX(), e.getY());
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO: Tell LEFT from RIGHT SHIFT apart.
		// I asked Xerxes Randby on twitter and he put up this:
		// https://jogamp.org/bugzilla/show_bug.cgi?id=1026
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
		
		Vector2D mousePos = getMousePosition(e);
		synchronized (mouseComp) {
			mouseComp.setPosition(mousePos);;
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		PcInputCode key = PcInputCode.getKeyByCode(-e.getButton());
		synchronized (keyMapComp) {
			keyMapComp.release(key);
		}
		
		Vector2D mousePos = getMousePosition(e);
		synchronized (mouseComp) {
			mouseComp.setPosition(mousePos);;
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		Vector2D mousePos = getMousePosition(e);
		synchronized (mouseComp) {
			mouseComp.setPosition(mousePos);;
		}
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