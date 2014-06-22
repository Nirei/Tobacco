package tobacco.render.pc.input;

import com.jogamp.newt.event.KeyEvent;
import com.jogamp.newt.event.MouseEvent;

import tobacco.core.components.Entity;
import tobacco.core.components.KeymapComponent;
import tobacco.render.pc.components.MouseComponent;
import tobacco.render.pc.renderers.AbstractRenderer;

// TODO: A listener that adds itself? Don't think so...
public class PcInputListener implements CommonListener {

	private KeymapComponent keyMapComp = new KeymapComponent();
	private MouseComponent mouseComp = new MouseComponent();

	public PcInputListener(Entity root, AbstractRenderer renderer) {
		root.putComponent(keyMapComp);
		root.putComponent(mouseComp);
		renderer.addListener(this);
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