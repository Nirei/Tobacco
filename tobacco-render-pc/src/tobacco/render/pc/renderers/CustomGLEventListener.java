package tobacco.render.pc.renderers;

import tobacco.core.services.RenderingService;
import tobacco.render.pc.input.CommonListener;

import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.util.AnimatorBase;

public abstract class CustomGLEventListener implements GLEventListener, RenderingService {
	
	protected AnimatorBase animator;
	protected Renderer renderer;
	
	public CustomGLEventListener(Renderer renderer) {
		GLProfile.initSingleton(); // Recommended before anything else
		this.renderer = renderer;
	}
	
	public void start() {
		animator.start();
	}
	
	public void stop() {
		animator.stop();
	}

	@Override
	public void init(GLAutoDrawable drawable) {
		drawable.getGL().setSwapInterval(1); // V-sync
	}

	@Override
	public void dispose(GLAutoDrawable drawable) {
		animator.stop();
	}

	@Override
	public void display(GLAutoDrawable drawable) {
		GL2 gl = drawable.getGL().getGL2();
		gl.glClear(GL.GL_COLOR_BUFFER_BIT);

		renderer.draw(drawable);
	}

	@Override
	public void reshape(GLAutoDrawable drawable, int x, int y, int width,
			int height) {
		GL2 gl = drawable.getGL().getGL2();

		gl.glMatrixMode(GL2.GL_PROJECTION);
		gl.glLoadIdentity();
		gl.glOrtho(-width/2f, width/2f, -height/2f, height/2f, 1f, -1f);
		gl.glMatrixMode(GL2.GL_MODELVIEW);
	}

	public abstract void addListener(CommonListener l);
}
