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
package tobacco.render.pc.renderers;

import com.jogamp.newt.event.WindowAdapter;
import com.jogamp.newt.opengl.GLWindow;

import javax.media.nativewindow.WindowClosingProtocol.WindowClosingMode;
import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.GLProfile;

import com.jogamp.opengl.util.Animator;

import tobacco.core.components.Entity;
import tobacco.render.pc.input.CommonListener;

public class NewtRenderer extends AbstractRenderer implements Renderer,
		GLEventListener {

	private GLWindow gw;

	public NewtRenderer(String title, Entity root) {
		glProfile = GLProfile.getDefault();
		glCaps = new GLCapabilities(glProfile);
		gw = GLWindow.create(glCaps);
		rootEntity = root;

		gw.setSize(480, 640);
		gw.requestFocus();
		gw.addGLEventListener(this);
		gw.setDefaultCloseOperation(WindowClosingMode.DISPOSE_ON_CLOSE);
		gw.setAnimator(animator);

		animator = new Animator(gw);

		// Set window closing operation
		gw.addWindowListener(new WindowAdapter() {
			@Override
			public void windowDestroyed(com.jogamp.newt.event.WindowEvent e) {
				super.windowDestroyed(e);
				System.exit(0);
			}
		});

		gw.setVisible(true);
	}

	@Override
	public int getWidth() {
		return gw.getWidth();
	}

	@Override
	public int getHeight() {
		return gw.getHeight();
	}

	@Override
	public void addListener(CommonListener l) {
		gw.addKeyListener(l);
		gw.addMouseListener(l);
	}
}
