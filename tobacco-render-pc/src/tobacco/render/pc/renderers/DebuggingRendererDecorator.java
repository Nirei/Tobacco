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

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;

import tobacco.core.datatypes.GVector2D;
import tobacco.core.services.DebuggingService;
import tobacco.core.util.Line2D;

public class DebuggingRendererDecorator implements Renderer, DebuggingService {
	
	private Renderer wrapped;
	
	private Map<String, Line2D> vectors = new ConcurrentHashMap<String, Line2D>();
	private Map<String, GVector2D> points = new ConcurrentHashMap<String, GVector2D>();
	
	public DebuggingRendererDecorator(Renderer wrapped) {
		this.wrapped = wrapped;
	}

	@Override
	public void displayText(String tag, String text, GVector2D position) {
		//TODO: Not implemented
		throw new RuntimeException("Not implemented");		
	}
	
	@Override
	public void clearText() {
		//TODO: Not implemented
		throw new RuntimeException("Not implemented");		
	}

	@Override
	public void removeText(String tag) {
		throw new RuntimeException("Not implemented");		
	}
	
	@Override
	public void displayPoint(String tag, GVector2D position) {
		points.put(tag, position);
	}
	
	@Override
	public void clearPoint() {
		points.clear();
	}
	
	@Override
	public void removePoint(String tag) {
		points.remove(tag);
	}

	@Override
	public void displayVector(String tag, Line2D position) {
		vectors.put(tag, position);
	}

	@Override
	public void clearVectors() {
		vectors.clear();
	}

	@Override
	public void removeVector(String tag) {
		vectors.remove(tag);
	}

	@Override
	public void draw(GLAutoDrawable drawable) {
		wrapped.draw(drawable);
		
		GL2 gl2 = drawable.getGL().getGL2();
		
		for(Line2D line : vectors.values()) {
			gl2.glBegin(GL2.GL_LINES);
			gl2.glColor3f(255f, 255f, 255f);
			gl2.glVertex2f(line.getA().getX(), line.getA().getY());
			gl2.glVertex2f(line.getB().getX(), line.getB().getY());
			gl2.glEnd();
		}
		
		for(GVector2D point : points.values()) {
			gl2.glBegin(GL2.GL_QUADS);
			gl2.glColor3f(255f, 255f, 255f);
			gl2.glVertex2f(point.getX()+1, point.getY()+1);
			gl2.glVertex2f(point.getX()-1, point.getY()+1);
			gl2.glVertex2f(point.getX()-1, point.getY()-1);
			gl2.glVertex2f(point.getX()+1, point.getY()-1);
			gl2.glEnd();
		}
		
		gl2.glDisable(GL2.GL_LINE_SMOOTH);

	}

}
