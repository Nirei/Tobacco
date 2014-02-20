package tobacco.render.pc.systems;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import tobacco.core.components.Component;
import tobacco.core.components.ContainerComponent;
import tobacco.core.components.DebuggingComponent;
import tobacco.core.components.KeyMapComponent;
import tobacco.core.entities.Entity;
import tobacco.core.systems.EngineSystem;
import tobacco.core.util.RawInputElement;
import tobacco.render.pc.renderers.AWTRenderer;

public class PcInputSystem implements EngineSystem, KeyListener, MouseListener, MouseMotionListener, MouseWheelListener {
	
	KeyMapComponent keyMap = new KeyMapComponent(200);
	
	public PcInputSystem(Entity root,PcRenderSystem prs) {
		Entity entity = new Entity();
		entity.putComponent(new DebuggingComponent());
		entity.putComponent(keyMap);
		((ContainerComponent) root.getComponent(Component.CONTAINER_C)).addChild(entity);;
		((AWTRenderer) prs.getRenderer()).addKeyListener(this);
		((AWTRenderer) prs.getRenderer()).addMouseListener(this);
		((AWTRenderer) prs.getRenderer()).addMouseMotionListener(this);
		((AWTRenderer) prs.getRenderer()).addMouseWheelListener(this);
	}
	
	@Override
	public void work(Entity entity) {
		keyMap.clear();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		synchronized (keyMap) 
		{
			keyMap.offer(new RawInputElement(e.getKeyCode(), RawInputElement.VALUE_PRESSED));
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		synchronized (keyMap)
		{
			keyMap.offer(new RawInputElement(e.getKeyCode(), RawInputElement.VALUE_RELEASED));
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		synchronized (keyMap) 
		{
			keyMap.offer(new RawInputElement(-e.getButton(), RawInputElement.VALUE_PRESSED));	
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		synchronized (keyMap) 
		{
			keyMap.offer(new RawInputElement(-e.getButton(), RawInputElement.VALUE_PRESSED));	
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		synchronized (keyMap) 
		{
			keyMap.offer(new RawInputElement(-e.getButton(), RawInputElement.VALUE_RELEASED));	
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		synchronized (keyMap)
		{
			keyMap.offer(new RawInputElement(RawInputElement.CODE_MOUSE_X,e.getX()));
			keyMap.offer(new RawInputElement(RawInputElement.CODE_MOUSE_Y,e.getY()));			
		}
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		synchronized (keyMap)
		{
			keyMap.offer(new RawInputElement(RawInputElement.CODE_MOUSE_Z,e.getWheelRotation()));						
		}
	}
}
