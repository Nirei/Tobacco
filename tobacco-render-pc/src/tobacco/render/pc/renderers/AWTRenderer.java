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
import tobacco.core.entities.Entity;

public class AWTRenderer extends Frame implements Renderer, GLEventListener {

	private static final long serialVersionUID = -5492953791399313270L;
	
	GLProfile glProfile;
    GLCapabilities glCaps;
    GLCanvas glCanvas;
    AnimatorBase animator;
	
	public AWTRenderer(String title) {
		super(title);
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
		
	}
	
	void update() {}
	
	void draw(GLAutoDrawable drawable) {
	    GL2 gl = drawable.getGL().getGL2();
	    
	    // Clear buffer
		gl.glClear(GL.GL_COLOR_BUFFER_BIT);

	    // Draw a triangle
	    gl.glBegin(GL.GL_TRIANGLES);
	    gl.glColor3f(1, 0, 0);
	    gl.glVertex2f(-1, -1);
	    gl.glColor3f(0, 1, 0);
	    gl.glVertex2f(0, 1);
	    gl.glColor3f(0, 0, 1);
	    gl.glVertex2f(1, -1);
	    gl.glEnd();
	}

	@Override public void init(GLAutoDrawable drawable) {
		animator.start();
		drawable.getGL().setSwapInterval(1);
	}

	@Override public void dispose(GLAutoDrawable drawable) {
		animator.stop();
	}

	@Override
	public void display(GLAutoDrawable drawable) {
		update();
		draw(drawable);
	}

	@Override public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {}

}
