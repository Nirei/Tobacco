package tobacco.render.pc.renderers;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.GLProfile;
import javax.media.opengl.awt.GLCanvas;

import tobacco.core.entities.Entity;

public class AWTRenderer extends Frame implements Renderer, GLEventListener {

	private static final long serialVersionUID = -5492953791399313270L;
	
	GLProfile glProfile;
    GLCapabilities glCaps;
    GLCanvas glCanvas;
	
	public AWTRenderer(String title) {
		super(title);
		setMinimumSize(new Dimension(480, 640));
		glProfile = GLProfile.getDefault();
	    glCaps = new GLCapabilities(glProfile);
	    glCanvas = new GLCanvas(glCaps);
	    add(glCanvas);
	    addWindowListener(new WindowAdapter() {
	    	@Override
			public void windowClosing(WindowEvent e) {
	    		e.getWindow().setVisible(false);
	    		e.getWindow().dispose();
				System.exit(0);
			}
		});
		setVisible(true);
	}
	
	@Override
	public void render(Entity entity) {
		
	}

	@Override
	public void init(GLAutoDrawable drawable) {
		// TODO Apéndice de método generado automáticamente
		
	}

	@Override
	public void dispose(GLAutoDrawable drawable) {
		// TODO Apéndice de método generado automáticamente
		
	}

	@Override
	public void display(GLAutoDrawable drawable) {
		for(;;);
	}

	@Override public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {}

}
