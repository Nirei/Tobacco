package tobacco.render.pc.systems;

import com.jogamp.newt.event.KeyEvent;
import com.jogamp.newt.event.MouseEvent;

import tobacco.core.components.Entity;
import tobacco.core.components.KeymapComponent;
import tobacco.core.systems.InputSystem;
import tobacco.render.pc.components.MouseComponent;
import tobacco.render.pc.renderers.AbstractRenderer;
import tobacco.render.pc.util.CommonListener;
import tobacco.render.pc.util.PcInputCode;

// TODO: Does this really need to be a System?
public class PcInputSystem extends InputSystem implements CommonListener {

	private KeymapComponent keyMapComp = new KeymapComponent();
	private MouseComponent mouseComp = new MouseComponent();

	public PcInputSystem(Entity root, PcRenderSystem prs) {
		root.putComponent(keyMapComp);
		root.putComponent(mouseComp);
		((AbstractRenderer) prs.getRenderer()).addListener(this);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO: Diferenciar entre Shift, etc. derecho e izquierdo
		if(!e.isAutoRepeat()) {
			PcInputCode key = PcInputCode.getKeyByCode(e.getKeyCode());
			synchronized (keyMapComp) {
				keyMapComp.press(key);				
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(!e.isAutoRepeat()) {
			PcInputCode key = PcInputCode.getKeyByCode(e.getKeyCode());
			synchronized (keyMapComp) {
				keyMapComp.release(key);	
			}
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		PcInputCode key = PcInputCode.getKeyByCode(-e.getButton());
		synchronized (keyMapComp) {
			keyMapComp.press(key);			
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		PcInputCode key = PcInputCode.getKeyByCode(-e.getButton());
		synchronized (keyMapComp) {
			keyMapComp.release(key);			
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {}

	@Override public void mouseWheelMoved(MouseEvent e) {}
	@Override public void mouseClicked(MouseEvent e) {}
	@Override public void mouseEntered(MouseEvent e) {}
	@Override public void mouseExited(MouseEvent e) {}
	@Override public void mouseDragged(MouseEvent e) {}

}