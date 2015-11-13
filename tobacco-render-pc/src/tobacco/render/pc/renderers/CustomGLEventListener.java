package tobacco.render.pc.renderers;

import tobacco.render.pc.input.CommonListener;

import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.util.AnimatorBase;

public abstract class CustomGLEventListener implements GLEventListener {
	
	protected AnimatorBase animator;
	protected Renderer renderer;
	
	public CustomGLEventListener(Renderer renderer) {
		GLProfile.initSingleton(); // Recommended before anything else
		this.renderer = renderer;
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
		gl.glOrtho(-getWidth()/2f, getWidth()/2f, -getHeight()/2f, getHeight()/2f, 1f, -1f);
		gl.glMatrixMode(GL2.GL_MODELVIEW);
	}

	public abstract int getWidth();

	public abstract int getHeight();

	public abstract void addListener(CommonListener l);
}
