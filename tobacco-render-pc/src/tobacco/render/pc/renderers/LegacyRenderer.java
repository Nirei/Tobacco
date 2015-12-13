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

import tobacco.core.components.AnimationComponent;
import tobacco.core.components.Component;
import tobacco.core.components.PositionComponent;
import tobacco.core.components.RotationComponent;
import tobacco.core.components.ScaleComponent;
import tobacco.core.components.TextureComponent;
import tobacco.core.components.TintComponent;
import tobacco.core.datatypes.GVector2D;
import tobacco.core.entities.Entity;
import tobacco.core.services.Directory;
import tobacco.render.pc.components.RendererComponent;
import tobacco.render.pc.components.ZIndexComponent;
import tobacco.render.pc.util.TextureStorage;
import tobacco.render.pc.util.exceptions.TextureNotFoundException;

import java.util.List;
import java.util.Comparator;

import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.util.texture.Texture;
import com.jogamp.opengl.util.texture.TextureCoords;

/**
 * Legacy OpenGL renderer for Tobacco
 * @author nirei
 */
public class LegacyRenderer implements Renderer {

	private void drawEntity(GLAutoDrawable drawable, Entity entity) {

		GL2 gl = drawable.getGL().getGL2();

		if (entity.has(RendererComponent.TEXTURE_C) && entity.has(Component.POSITION_C)) {
			gl.glPushMatrix();

			GVector2D pos = GVector2D.ZERO;
			GVector2D sca = new GVector2D(1f, 1f);
			float x, y;
			// float zIndex = 0f;
			float rot = 0f;
			float tintR=1f,tintG=1f,tintB=1f;
			Texture texture = null;
			int frame = 0;

			PositionComponent posComp = (PositionComponent) entity.get(Component.POSITION_C);
			pos = posComp.getPosition();
			// zIndex = posComp.getZIndex();
			
			if (entity.has(Component.ROTATION_C))
				rot = ((RotationComponent) entity.get(Component.ROTATION_C)).getRotation();
			if (entity.has(Component.SCALE_C))
				sca = ((ScaleComponent) entity.get(Component.SCALE_C)).getScale();
			if (entity.has(Component.ANIMATION_C))
				frame = ((AnimationComponent) entity.get(Component.ANIMATION_C)).getFrame();
			if (entity.has(Component.TINT_C)) {
				TintComponent tintComp= (TintComponent) entity.get(Component.TINT_C);
				tintR = tintComp.getRed();
				tintG = tintComp.getGreen();
				tintB = tintComp.getBlue();
			}

			x = pos.getX();
			y = pos.getY();


			// Apply transformations
			gl.glTranslatef(x, y, 0);
			gl.glRotatef(rot, 0, 0, 1);
			gl.glScalef(sca.getX(), sca.getY(), 0);

			TextureComponent textureComp = (TextureComponent) entity.get(RendererComponent.TEXTURE_C);
			
			try {
				texture = TextureStorage.getTexture(textureComp.getImagePath());
			} catch (TextureNotFoundException e1) {
				try {
					System.err.println(e1.getMessage());
					texture = TextureStorage.getErrorTexture();
				} catch (TextureNotFoundException e2) {
					System.err.println(e2.getMessage());
				}
			}

			if(texture != null) {
				
				// Calculate sprite frame pixels
				int rows = textureComp.getRows();
				int columns = textureComp.getColumns();
				int height = textureComp.getHeight()/rows;
				int width = textureComp.getWidth()/columns;
				int xStart = width * (frame % columns);
				int xEnd = xStart + width;
				int yStart = height * (frame / columns);
				int yEnd = yStart + height;
				
				// Calculate quad vertices
				float localXEnd = width / 2f;
				float localXInit = -localXEnd;
				float localYEnd = height / 2f;
				float localYInit = -localYEnd;
				
				// Texture image flips vertically. Shall use TextureCoords class
				// to retrieve the top, bottom, left and right coordinates,
				// instead of using 0.0f and 1.0f.
				TextureCoords spriteCoords = texture.getSubImageTexCoords(xStart, yStart, xEnd, yEnd);
				float spriteTop = spriteCoords.top();
				float spriteBottom = spriteCoords.bottom();
				float spriteLeft = spriteCoords.left();
				float spriteRight = spriteCoords.right();

				// Enables this texture's target in the current GL context's
				// state.
				texture.enable(gl);
				// Binds this texture to the current GL context.
				texture.bind(gl);

				gl.glBegin(GL2.GL_QUADS);
				gl.glColor4f(tintR, tintG, tintB, 1f);
				gl.glTexCoord2f(spriteLeft, spriteBottom);
				gl.glVertex2f(localXInit, localYInit);
				gl.glTexCoord2f(spriteLeft, spriteTop);
				gl.glVertex2f(localXInit, localYEnd);
				gl.glTexCoord2f(spriteRight, spriteTop);
				gl.glVertex2f(localXEnd, localYEnd);
				gl.glTexCoord2f(spriteRight, spriteBottom);
				gl.glVertex2f(localXEnd, localYInit);
				gl.glEnd();

				texture.disable(gl);
			} else {
				//TODO: Log this
			}

			gl.glPopMatrix();
		}
	}
	
	private boolean isRenderable(Entity e) {
		return e.has(RendererComponent.POSITION_C)
		&& e.has(RendererComponent.TEXTURE_C)
		&& e.has(RendererComponent.ZINDEX_C);
	}

	private void renderWorld(GLAutoDrawable drawable) {
		// Fetch all entities
		List<Entity> list = Directory.getEntityService().getEntityList();
		// Filter unrenderables
		list.removeIf(e -> !isRenderable(e));
		// Sort them by ZIndex
		list.sort(new Comparator<Entity>() {
			@Override
			public int compare(Entity o1, Entity o2) {
				ZIndexComponent z1 = (ZIndexComponent) o1.get(RendererComponent.ZINDEX_C);
				ZIndexComponent z2 = (ZIndexComponent) o2.get(RendererComponent.ZINDEX_C);
				return Integer.compare(z1.getZIndex(), z2.getZIndex());
			}
		});
		// And draw
		for(Entity e : list) drawEntity(drawable, e);
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
		
		renderWorld(drawable);

		gl.glDisable(GL.GL_BLEND);
	}
}