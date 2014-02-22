package tobacco.render.pc.renderers;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.GLProfile;
import javax.media.opengl.awt.GLCanvas;

import com.jogamp.opengl.util.Animator;

import tobacco.core.entities.Entity;
import tobacco.render.pc.util.CommonListener;

public class AWTRenderer extends AbstractRenderer implements Renderer, GLEventListener {

	private GLProfile glProfile;
    private GLCapabilities glCaps;
    private GLCanvas glCanvas;
    private Frame gw;

	public AWTRenderer(String title, Entity root) {
		rootEntity = root;
		gw = new Frame();
		gw.setMinimumSize(new Dimension(480, 640));
		
		glProfile = GLProfile.getDefault();
	    glCaps = new GLCapabilities(glProfile);
	    glCanvas = new GLCanvas(glCaps);
	    gw.add(glCanvas);
	    glCanvas.setFocusable(true);
	    glCanvas.requestFocus();
	    glCanvas.addGLEventListener(this);
	    gw.addWindowListener(new WindowAdapter() {
	    	@Override
			public void windowClosing(WindowEvent e) {
	    		e.getWindow().setVisible(false);
	    		e.getWindow().dispose();
				System.exit(0);
			}
		});

		animator = new Animator(glCanvas);

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