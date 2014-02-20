package tobacco.render.pc.renderers;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.GLProfile;
import javax.media.opengl.awt.GLCanvas;

import com.jogamp.opengl.util.Animator;
import com.jogamp.opengl.util.AnimatorBase;

import tobacco.core.components.Component;
import tobacco.core.components.ContainerComponent;
import tobacco.core.components.DrawableComponent;
import tobacco.core.components.PositionComponent;
import tobacco.core.entities.Entity;
import tobacco.core.util.Vector2D;

public class AWTRenderer extends Frame implements Renderer, GLEventListener {

	private static final long serialVersionUID = -5492953791399313270L;
	
	GLProfile glProfile;
    GLCapabilities glCaps;
    GLCanvas glCanvas;
    AnimatorBase animator;
    Entity rootEntity;
	
	public AWTRenderer(String title,Entity root) {
		super(title);
		rootEntity = root;
		setMinimumSize(new Dimension(480, 640));
		
		glProfile = GLProfile.getDefault();
	    glCaps = new GLCapabilities(glProfile);
	    glCanvas = new GLCanvas(glCaps);
	    add(glCanvas);
	    glCanvas.addGLEventListener(this);
	    addWindowListener(new WindowAdapter() {
	    	@Override
			public void windowClosing(WindowEvent e) {
	    		e.getWindow().setVisible(false);
	    		e.getWindow().dispose();
				System.exit(0);
			}
		});

		animator = new Animator(glCanvas);

	    setVisible(true);
	}
	
	@Override
	public void render(Entity entity) {
		rootEntity = entity;
	}
	
	private void update() {}
	
	private void drawEntityTree(GLAutoDrawable drawable, Entity root)
	{
		
	    
	    if(root.contains(Component.DRAWABLE_C)) 
	    {
	    	GL2 gl = drawable.getGL().getGL2();
			
	    	float x=0, y=0, width=200, height=200;
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
		drawable.getGL().setSwapInterval(1);
		
		GL2 gl = drawable.getGL().getGL2();
		
		gl.glMatrixMode(GL2.GL_PROJECTION);
		gl.glLoadIdentity();
		gl.glOrtho(0, 800, 0, 600, 1, -1);
		gl.glMatrixMode(GL2.GL_MODELVIEW);
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

	@Override public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {}

}
