package tobacco.render.pc.renderers;

import com.jogamp.newt.event.WindowAdapter;
import com.jogamp.newt.opengl.GLWindow;

import javax.media.nativewindow.WindowClosingProtocol.WindowClosingMode;
import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.GLProfile;

import com.jogamp.opengl.util.Animator;

import tobacco.core.entities.Entity;
import tobacco.render.pc.util.CommonListener;

public class NewtRenderer extends AbstractRenderer implements Renderer, GLEventListener {

	GLWindow gw;
	
	public NewtRenderer(String title, Entity root) {
		glProfile = GLProfile.getDefault();
	    glCaps = new GLCapabilities(glProfile);
	    gw = GLWindow.create(glCaps);
		rootEntity = root;
		
		gw.setSize(480, 640);
		gw.requestFocus();
		gw.addGLEventListener(this);
		gw.setDefaultCloseOperation(WindowClosingMode.DISPOSE_ON_CLOSE);
		gw.setAnimator(animator);

		animator = new Animator(gw);		
	    
		// Set window closing operation
		gw.addWindowListener(new WindowAdapter() {
	    	@Override
	    	public void windowDestroyed(com.jogamp.newt.event.WindowEvent e) {
	    		super.windowDestroyed(e);
	    		System.exit(0);
	    	}
	    });
		
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
