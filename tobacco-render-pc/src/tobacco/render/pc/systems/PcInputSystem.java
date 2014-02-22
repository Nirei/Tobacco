package tobacco.render.pc.systems;



import java.awt.AWTEvent;
import java.awt.event.MouseWheelEvent;

import com.jogamp.newt.event.KeyEvent;
import com.jogamp.newt.event.MouseEvent;

import tobacco.core.components.Component;
import tobacco.core.components.ContainerComponent;
import tobacco.core.components.DebuggingComponent;
import tobacco.core.components.KeyMapComponent;
import tobacco.core.entities.Entity;
import tobacco.core.systems.InputSystem;
import tobacco.core.util.RawInputElement;
import tobacco.render.pc.renderers.AbstractRenderer;
import tobacco.render.pc.util.CommonListener;

public class PcInputSystem extends InputSystem implements CommonListener {
	
	KeyMapComponent keyMap = new KeyMapComponent(200);
	
	public PcInputSystem(Entity root, PcRenderSystem prs) {
		Entity entity = new Entity();
		entity.putComponent(new DebuggingComponent());
		entity.putComponent(keyMap);
		((ContainerComponent) root.getComponent(Component.CONTAINER_C)).addChild(entity);
		((AbstractRenderer) prs.getRenderer()).addListener(this);
	}
	
	@Override
	public void work(Entity entity) {}
	
	@Override
	public KeyMapComponent getKeyMap() {
		return keyMap;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		synchronized (keyMap) {
			keyMap.offer(new RawInputElement(e.getKeyCode(), RawInputElement.VALUE_PRESSED));
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		synchronized (keyMap) {
			keyMap.offer(new RawInputElement(e.getKeyCode(), RawInputElement.VALUE_RELEASED));
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		synchronized (keyMap) {
			keyMap.offer(new RawInputElement(-e.getButton(), RawInputElement.VALUE_PRESSED));	
		}
	}

	@Override public void mouseEntered(MouseEvent e) {}
	@Override public void mouseExited(MouseEvent e) {}

	@Override
	public void mousePressed(MouseEvent e) {
		synchronized (keyMap) {
			keyMap.offer(new RawInputElement(-e.getButton(), RawInputElement.VALUE_PRESSED));	
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		synchronized (keyMap) {
			keyMap.offer(new RawInputElement(-e.getButton(), RawInputElement.VALUE_RELEASED));	
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		synchronized (keyMap) {
			keyMap.offer(new RawInputElement(RawInputElement.CODE_MOUSE_X,e.getX()));
			keyMap.offer(new RawInputElement(RawInputElement.CODE_MOUSE_Y,e.getY()));			
		}
	}

	//@Override
	/*public void mouseWheelMoved(MouseEvent e) {
		synchronized (keyMap) {
			keyMap.offer(new RawInputElement(RawInputElement.CODE_MOUSE_Z,e.get));						
		}
	}*/

	
	@Override public void mouseDragged(MouseEvent e) {}

	@Override
	public void mouseWheelMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(java.awt.event.KeyEvent e) {
		synchronized (keyMap) {
			keyMap.offer(new RawInputElement(e.getKeyCode(), RawInputElement.VALUE_PRESSED));
		}
	}

	@Override
	public void keyReleased(java.awt.event.KeyEvent e) {
		synchronized (keyMap) {
			keyMap.offer(new RawInputElement(e.getKeyCode(), RawInputElement.VALUE_RELEASED));
		}
	}

	@Override public void keyTyped(java.awt.event.KeyEvent arg0) {}

	@Override
	public void mouseClicked(java.awt.event.MouseEvent e) {
		synchronized (keyMap) {
			keyMap.offer(new RawInputElement(-e.getButton(), RawInputElement.VALUE_PRESSED));	
		}
	}

	@Override
	public void mouseEntered(java.awt.event.MouseEvent e) {
		synchronized (keyMap) {
			keyMap.offer(new RawInputElement(-e.getButton(), RawInputElement.VALUE_PRESSED));	
		}
	}

	@Override
	public void mouseExited(java.awt.event.MouseEvent e) {
		synchronized (keyMap) {
			keyMap.offer(new RawInputElement(-e.getButton(), RawInputElement.VALUE_PRESSED));	
		}
	}

	@Override
	public void mousePressed(java.awt.event.MouseEvent e) {
		synchronized (keyMap) {
			keyMap.offer(new RawInputElement(-e.getButton(), RawInputElement.VALUE_PRESSED));	
		}
	}

	@Override
	public void mouseReleased(java.awt.event.MouseEvent e) {
		synchronized (keyMap) {
			keyMap.offer(new RawInputElement(-e.getButton(), RawInputElement.VALUE_PRESSED));	
		}
	}

	@Override
	public void mouseDragged(java.awt.event.MouseEvent e) {
		synchronized (keyMap) {
			keyMap.offer(new RawInputElement(-e.getButton(), RawInputElement.VALUE_PRESSED));	
		}
	}

	@Override
	public void mouseMoved(java.awt.event.MouseEvent e) {
		synchronized (keyMap) {
			keyMap.offer(new RawInputElement(RawInputElement.CODE_MOUSE_X,e.getX()));
			keyMap.offer(new RawInputElement(RawInputElement.CODE_MOUSE_Y,e.getY()));			
		}
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		synchronized (keyMap) {
			keyMap.offer(new RawInputElement(-e.getButton(), RawInputElement.VALUE_PRESSED));	
		}
	}
}
