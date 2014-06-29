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
package tobacco.render.pc.systems;

import javax.media.opengl.GLProfile;

import tobacco.core.components.Entity;
import tobacco.core.systems.EngineSystem;
import tobacco.render.pc.renderers.NewtRenderer;
import tobacco.render.pc.renderers.Renderer;

// TODO: Is there any actual need for this to be a System?
// Only work
public class PcRenderSystem implements EngineSystem {

	Renderer renderer;

	public PcRenderSystem(Entity root) {
		GLProfile.initSingleton(); // Recomended before anything else
		renderer = new NewtRenderer("The Game", root);
	}

	@Override
	public void work(Entity root) {
		// This only updates the root for rendering.
		// The renderer DOES act as a System, though
		renderer.render(root);
	}

	public Renderer getRenderer() {
		return renderer;
	}

	public void setRenderer(Renderer renderer) {
		this.renderer = renderer;
	}

}
