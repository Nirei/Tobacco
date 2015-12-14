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
package tobacco.render.pc.renderers;

import tobacco.core.services.RenderingService;
import tobacco.render.pc.input.CommonListener;

import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.util.AnimatorBase;

public abstract class CustomGLEventListener implements GLEventListener, RenderingService {
	
	protected AnimatorBase animator;
	protected Renderer renderer;
	
	public CustomGLEventListener(Renderer renderer) {
		GLProfile.initSingleton(); // Recommended before anything else
		this.renderer = renderer;
	}
	
	public void start() {
		animator.start();
	}
	
	public void stop() {
		animator.stop();
	}

	@Override
	public void init(GLAutoDrawable drawable) {
		drawable.getGL().setSwapInterval(1); // V-sync
	}

	@Override
	public void dispose(GLAutoDrawable drawable) {
		animator.stop();
	}

	@Override
	public void display(GLAutoDrawable drawable) {
		GL2 gl = drawable.getGL().getGL2();
		gl.glClear(GL.GL_COLOR_BUFFER_BIT);

		renderer.draw(drawable);
	}

	@Override
	public void reshape(GLAutoDrawable drawable, int x, int y, int width,
			int height) {
		GL2 gl = drawable.getGL().getGL2();

		gl.glMatrixMode(GL2.GL_PROJECTION);
		gl.glLoadIdentity();
		gl.glOrtho(-width/2f, width/2f, -height/2f, height/2f, 1f, -1f);
		gl.glMatrixMode(GL2.GL_MODELVIEW);
	}

	public abstract void addListener(CommonListener l);
}
