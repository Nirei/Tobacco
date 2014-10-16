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
import javax.media.opengl.GLProfile;

import com.jogamp.opengl.util.Animator;

import tobacco.core.components.Component;
import tobacco.core.components.Entity;
import tobacco.core.components.ScreenComponent;
import tobacco.core.services.Directory;
import tobacco.core.util.Vector2D;
import tobacco.render.pc.input.CommonListener;

public class NewtGLEventListener extends CustomGLEventListener {

	private GLWindow gw;
	protected GLProfile glProfile;
	protected GLCapabilities glCaps;

	public NewtGLEventListener(String title, Renderer renderer) {
		super(renderer);

		glProfile = GLProfile.getDefault();
		glCaps = new GLCapabilities(glProfile);
		gw = GLWindow.create(glCaps);

		Entity root = Directory.getDataService().getRoot();
		Vector2D scrSize = ((ScreenComponent) root.get(Component.SCREEN_C)).getScreenSize();
		gw.setSize((int) scrSize.getX(), (int) scrSize.getY());
		gw.setPosition(10, 10);

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
