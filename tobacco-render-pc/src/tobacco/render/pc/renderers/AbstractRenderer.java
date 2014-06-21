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
import tobacco.core.components.Entity;
import tobacco.core.components.PositionComponent;
import tobacco.core.components.RotationComponent;
import tobacco.core.components.ScaleComponent;
import tobacco.core.util.Vector2D;
import tobacco.render.pc.util.CommonListener;
import tobacco.render.pc.util.TextureStorage;
import tobacco.render.pc.util.exceptions.TextureNotFoundException;

import com.jogamp.opengl.util.AnimatorBase;
import com.jogamp.opengl.util.texture.Texture;
import com.jogamp.opengl.util.texture.TextureCoords;

public abstract class AbstractRenderer implements Renderer, GLEventListener {

	protected static GLProfile glProfile;
	protected static GLCapabilities glCaps;
	protected AnimatorBase animator;
	protected static Entity rootEntity;

	public AbstractRenderer() {
		super();
	}

	@Override
	public void render(Entity root) {
		rootEntity = root;
	}

	private void update() {}

	private void drawEntityTree(GLAutoDrawable drawable, Entity entity) {

		GL2 gl = drawable.getGL().getGL2();
		
		gl.glPushMatrix();
		
		if(entity.contains(Component.DRAWABLE_C)) 
	    {
	    	DrawableComponent drawComp = (DrawableComponent) entity.getComponent(Component.DRAWABLE_C);
			
	    	float x=0, y=0, width=50, height=50;
	    	Vector2D pos = Vector2D.ZERO;
	    	float rot = 0f;
	    	Vector2D sca = new Vector2D(1,1);

	    	if(entity.contains(Component.POSITION_C))
	    		pos = ((PositionComponent) entity.getComponent(Component.POSITION_C)).getPosition();
	    	if(entity.contains(Component.ROTATION_C))
	    		rot = ((RotationComponent) entity.getComponent(Component.ROTATION_C)).getRotation();
	    	if(entity.contains(Component.SCALE_C))
    			sca = ((ScaleComponent) entity.getComponent(Component.SCALE_C)).getScale();
	    	
	    	Vector2D size = drawComp.getSize();
	    		    	
	    	Texture texture = null;
	    	float textureTop = 0f, textureBottom = 0f, textureLeft = 0f, textureRight = 0f;
	    	if(size != null) {
	    		width = size.getX();
	    		height = size.getY();
	    	}
	    	
    		x = pos.getX();
    		y = pos.getY();
    		
    		float localXInit = -width/2f,
    				localXEnd = width/2f,
    				localYInit = -height/2f,
    				localYEnd = height/2f;
    		
	        // Apply transformations
	        gl.glTranslatef(x, y, 0);
	        gl.glRotatef(rot, 0, 0, 1);
	        gl.glScalef(sca.getX(), sca.getY(), 0);

	    	// TODO: Proper exception handling
			try {
				texture = TextureStorage.getTexture(drawComp.getImagePath());

		    	// Texture image flips vertically. Shall use TextureCoords class to retrieve
		    	// the top, bottom, left and right coordinates, instead of using 0.0f and 1.0f.
		    	TextureCoords textureCoords = texture.getImageTexCoords();
		    	textureTop = textureCoords.top();
		    	textureBottom = textureCoords.bottom();
		    	textureLeft = textureCoords.left();
		    	textureRight = textureCoords.right();
		    	
		        // Enables this texture's target in the current GL context's state.
		        texture.enable(gl);
		        // Binds this texture to the current GL context.
		        texture.bind(gl);
		        
			    gl.glBegin(GL2.GL_QUADS);
			    gl.glTexCoord2f(textureLeft, textureBottom);
			    gl.glVertex2f(localXInit, localYInit);
			    gl.glTexCoord2f(textureLeft, textureTop);
			    gl.glVertex2f(localXInit, localYEnd);
			    gl.glTexCoord2f(textureRight, textureTop);
			    gl.glVertex2f(localXEnd, localYEnd);
			    gl.glTexCoord2f(textureRight, textureBottom);
			    gl.glVertex2f(localXEnd, localYInit);
			    gl.glEnd();

			} catch (TextureNotFoundException e) {
				// Render without texture and inform
			    gl.glBegin(GL2.GL_QUADS);
			    gl.glColor3f(1, 0, 0);
			    gl.glVertex2f(localXInit, localYInit);
			    gl.glColor3f(0, 1, 0);
			    gl.glVertex2f(localXInit, localYEnd);
			    gl.glColor3f(0, 0, 1);
			    gl.glVertex2f(localXEnd, localYEnd);
			    gl.glColor3f(1, 0, 1);
			    gl.glVertex2f(localXEnd, localYInit);
			    gl.glEnd();
			    if(e.getTexturePath() != null)
			    	System.err.println(e.getMessage());
			}
	    }
		
		if(entity.contains(Component.CONTAINER_C)) {
			ContainerComponent children = (ContainerComponent) entity.getComponent(Component.CONTAINER_C);
			for(Entity e : children) {
				drawEntityTree(drawable, e);
			}
		}
		
		gl.glPopMatrix();
	}

	private void draw(GLAutoDrawable drawable) {
	    GL2 gl = drawable.getGL().getGL2();
	    gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);

	    gl.glEnable(GL.GL_BLEND);
	    gl.glBlendFunc(GL.GL_SRC_ALPHA, GL.GL_ONE_MINUS_SRC_ALPHA);

    	// Use linear filter for texture if image is larger than the original texture
    	gl.glTexParameteri(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_MAG_FILTER, GL.GL_LINEAR);
    	// Use linear filter for texture if image is smaller than the original texture
    	gl.glTexParameteri(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_MIN_FILTER, GL.GL_LINEAR);

	    
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