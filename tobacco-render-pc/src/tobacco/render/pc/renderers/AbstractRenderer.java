package tobacco.render.pc.renderers;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.GLProfile;

import tobacco.core.components.Component;
import tobacco.core.components.ContainerComponent;
import tobacco.core.components.DrawableComponent;
import tobacco.core.components.PositionComponent;
import tobacco.core.entities.Entity;
import tobacco.core.util.Vector2D;
import tobacco.render.pc.util.CommonListener;

import com.jogamp.newt.NewtFactory;
import com.jogamp.opengl.util.AnimatorBase;

public abstract class AbstractRenderer implements Renderer, GLEventListener {

	NewtFactory nf;
	protected static GLProfile glProfile;
	protected static GLCapabilities glCaps;
	protected AnimatorBase animator;
	protected static Entity rootEntity;

	public AbstractRenderer() {
		super();
	}

	@Override
	public void render(Entity entity) {
		rootEntity = entity;
	}

	private void update() {}

	private void drawEntityTree(GLAutoDrawable drawable, Entity root) {
	    if(root.contains(Component.DRAWABLE_C)) 
	    {
	    	GL2 gl = drawable.getGL().getGL2();
			
	    	float x=0, y=0, width=50, height=50;
	    	Vector2D pos = ((PositionComponent) root.getComponent(Component.POSITION_C)).getPosition();
	    	Vector2D size = ((DrawableComponent) root.getComponent(Component.DRAWABLE_C)).getSize();
	    	if(pos != null) {
	    		x = pos.getX();
	    		y = pos.getY();
	    	}
	    	if(size != null) {
	    		width = size.getX();
	    		height = size.getY();
	    	}
	
		    gl.glBegin(GL2.GL_QUADS);
		    gl.glColor3f(1, 0, 0);
		    gl.glVertex2f(x, y);
		    gl.glColor3f(0, 1, 0);
		    gl.glVertex2f(x, y+height);
		    gl.glColor3f(0, 0, 1);
		    gl.glVertex2f(x+width, y+height);
		    gl.glColor3f(1, 0, 1);
		    gl.glVertex2f(x+width, y);
		    gl.glEnd();
	    
	    }
		
		if(root.contains(Component.CONTAINER_C)) {
			ContainerComponent children = (ContainerComponent) root.getComponent(Component.CONTAINER_C);
			for(Entity e : children) {
				drawEntityTree(drawable, e);
			}
		}
	}

	private void draw(GLAutoDrawable drawable) {
	    GL2 gl = drawable.getGL().getGL2();
	    gl.glClear(GL.GL_COLOR_BUFFER_BIT);
	    
	    drawEntityTree(drawable, rootEntity);
	}

	@Override
	public void init(GLAutoDrawable drawable) {
		animator.start();
		drawable.getGL().setSwapInterval(1); // V-sync
	}

	@Override
	public void dispose(GLAutoDrawable drawable) {
		animator.stop();
	}

	@Override
	public void display(GLAutoDrawable drawable) {
		update();
		draw(drawable);
	}

	@Override
	public void reshape(GLAutoDrawable drawable, int x, int y, int width,
			int height) {
				GL2 gl = drawable.getGL().getGL2();
				
				gl.glMatrixMode(GL2.GL_PROJECTION);
				gl.glLoadIdentity();
				gl.glOrtho(0, getWidth(), 0, getHeight(), 1, -1);
				gl.glMatrixMode(GL2.GL_MODELVIEW);
	}
	
	public abstract int getWidth();
	public abstract int getHeight();
	
	public abstract void addListener(CommonListener l);

}