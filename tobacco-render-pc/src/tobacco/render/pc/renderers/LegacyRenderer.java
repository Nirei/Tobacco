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

import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;

import tobacco.core.components.Component;
import tobacco.core.components.ContainerComponent;
import tobacco.core.components.PositionComponent;
import tobacco.core.components.RotationComponent;
import tobacco.core.components.ScaleComponent;
import tobacco.core.components.SizeComponent;
import tobacco.core.entities.Entity;
import tobacco.core.services.Directory;
import tobacco.core.util.Vector2D;
import tobacco.render.pc.components.RendererComponent;
import tobacco.render.pc.components.TextureComponent;
import tobacco.render.pc.util.TextureStorage;
import tobacco.render.pc.util.exceptions.TextureNotFoundException;

import com.jogamp.opengl.util.texture.Texture;
import com.jogamp.opengl.util.texture.TextureCoords;

public class LegacyRenderer implements Renderer {

	private void drawEntity(GLAutoDrawable drawable, Entity entity) {

		GL2 gl = drawable.getGL().getGL2();

		if (entity.has(RendererComponent.TEXTURE_C) && entity.has(Component.SIZE_C)) {
			gl.glPushMatrix();

			Vector2D pos = Vector2D.ZERO;
			Vector2D size = new Vector2D(50f, 50f);
			Vector2D sca = new Vector2D(1f, 1f);
			float x, y, width, height;
			// float zIndex = 0f;
			float rot = 0f;

			if (entity.has(Component.POSITION_C)) {
				PositionComponent posComp = (PositionComponent) entity.get(Component.POSITION_C);
				pos = posComp.getPosition();
				// zIndex = posComp.getZIndex();
			}
			if (entity.has(Component.ROTATION_C))
				rot = ((RotationComponent) entity.get(Component.ROTATION_C)).getRotation();
			if (entity.has(Component.SCALE_C))
				sca = ((ScaleComponent) entity.get(Component.SCALE_C)).getScale();
			if (entity.has(Component.SIZE_C))
				size = ((SizeComponent) entity.get(Component.SIZE_C)).getSize();

			Texture texture = null;
			float textureTop = 0f, textureBottom = 0f, textureLeft = 0f, textureRight = 0f;

			width = size.getX();
			height = size.getY();

			x = pos.getX();
			y = pos.getY();

			float localXEnd = width / 2f, localXInit = -localXEnd, localYEnd = height / 2f, localYInit = -localYEnd;
			// Apply transformations
			gl.glTranslatef(x, y, 0);
			gl.glRotatef(rot, 0, 0, 1);
			gl.glScalef(sca.getX(), sca.getY(), 0);

			try {
				TextureComponent textureComp = (TextureComponent) entity.get(RendererComponent.TEXTURE_C);
				texture = TextureStorage.getTexture(textureComp.getImagePath());
			} catch (TextureNotFoundException e1) {
				try {
					texture = TextureStorage.getErrorTexture();
				} catch (TextureNotFoundException e2) {
					System.err.println(e1.getMessage());
					System.err.println(e2.getMessage());
				}
			}

			if(texture != null) {
				// Texture image flips vertically. Shall use TextureCoords class
				// to retrieve
				// the top, bottom, left and right coordinates, instead of using
				// 0.0f and 1.0f.
				TextureCoords textureCoords = texture.getImageTexCoords();
				textureTop = textureCoords.top();
				textureBottom = textureCoords.bottom();
				textureLeft = textureCoords.left();
				textureRight = textureCoords.right();

				// Enables this texture's target in the current GL context's
				// state.
				texture.enable(gl);
				// Binds this texture to the current GL context.
				texture.bind(gl);

				gl.glBegin(GL2.GL_QUADS);
				gl.glTexCoord2f(textureLeft, textureBottom);
				gl.glVertex2f(localXInit, localYInit);
				gl.glTexCoord2f(textureLeft, textureTop);
				gl.glVertex2f(localXInit, localYEnd);
				gl.glTexCoord2f(textureRight, textureTop);
				gl.glVertex2f(localXEnd, localYEnd);
				gl.glTexCoord2f(textureRight, textureBottom);
				gl.glVertex2f(localXEnd, localYInit);
				gl.glEnd();

				texture.disable(gl);
			} else {
				//TODO: Log this
			}

			gl.glPopMatrix();
		}
	}

	private void drawEntityTree(GLAutoDrawable drawable, Entity entity) {
		
		drawEntity(drawable, entity);

		if (entity.has(Component.CONTAINER_C)) {
			ContainerComponent children = (ContainerComponent) entity.get(Component.CONTAINER_C);
			for (Entity e : children) {
				drawEntityTree(drawable, e);
			}
		}

	}

	public void draw(GLAutoDrawable drawable) {
		GL2 gl = drawable.getGL().getGL2();

		gl.glEnable(GL.GL_BLEND);
		gl.glBlendFunc(GL.GL_SRC_ALPHA, GL.GL_ONE_MINUS_SRC_ALPHA);

		// Use linear filter for texture if image is larger than the original
		// texture
		gl.glTexParameteri(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_MAG_FILTER, GL.GL_LINEAR);
		// Use linear filter for texture if image is smaller than the original
		// texture
		gl.glTexParameteri(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_MIN_FILTER, GL.GL_LINEAR);
		
		Entity root = Directory.getEntityService().getRoot();
		if(root != null) drawEntityTree(drawable, root);

		gl.glDisable(GL.GL_BLEND);
	}
}