package tobacco.render.pc.systems;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

import com.jogamp.newt.event.KeyEvent;
import com.jogamp.newt.event.MouseEvent;

import tobacco.core.actions.Command;
import tobacco.core.components.CommandBufferComponent;
import tobacco.core.components.Component;
import tobacco.core.components.PlayerComponent;
import tobacco.core.entities.Entity;
import tobacco.core.systems.InputSystem;
import tobacco.render.pc.renderers.AbstractRenderer;
import tobacco.render.pc.util.CommonListener;
import tobacco.render.pc.util.InputCode;

public class PcInputSystem extends InputSystem implements CommonListener {
	
	public static final String INPUT_COMMAND = "input";
	public static final String RELEASED_EVENT = "released";
	public static final String PRESSED_EVENT = "pressed";
	public static final String MOVED_EVENT = "moved";
	public static final String DEV_KEYBOARD = "keyboard";
	public static final String DEV_MOUSE = "mouse";

	private Entity player;
	private CommandBufferComponent cbcomp;
	private Queue<Command> queue = new ArrayBlockingQueue<Command>(200);

	public PcInputSystem(Entity _player, PcRenderSystem prs) {
		player = _player;
		PlayerComponent playerComp = new PlayerComponent();
		player.putComponent(playerComp);
		cbcomp = (CommandBufferComponent) player.getComponent(Component.COMMAND_BUFFER_C);
		((AbstractRenderer) prs.getRenderer()).addListener(this);
	}
	
	@Override
	public void work(Entity root) {
		if(player.contains(Component.COMMAND_BUFFER_C)) {
			cbcomp = (CommandBufferComponent) player.getComponent(Component.COMMAND_BUFFER_C);
			synchronized(queue) {
				for(Command c : queue) {
					cbcomp.add(c);
				}
				queue.clear();
			}
		}
	}
	
	private void processInputEvent(String ...args) {
		synchronized (queue) {
			queue.offer(new Command(INPUT_COMMAND, args));
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO: Diferenciar entre Shift, etc. derecho e izquierdo
		if(!e.isAutoRepeat()) {
			InputCode key = InputCode.getKeyByCode(e.getKeyCode());
			if(key.isCharacter()) {
				processInputEvent(DEV_KEYBOARD, PRESSED_EVENT, key.toString(), String.valueOf(e.getKeyChar()));
			} else {
				processInputEvent(DEV_KEYBOARD, PRESSED_EVENT, key.toString());
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(!e.isAutoRepeat()) {
			InputCode key = InputCode.getKeyByCode(e.getKeyCode());
			if(key.isCharacter()) {
				processInputEvent(DEV_KEYBOARD, RELEASED_EVENT, key.toString(), String.valueOf(e.getKeyChar()));
			} else {
				processInputEvent(DEV_KEYBOARD, RELEASED_EVENT, key.toString());
			}		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		processInputEvent(DEV_MOUSE, PRESSED_EVENT, InputCode.getKeyByCode(-e.getButton()).toString());
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		processInputEvent(DEV_MOUSE, PRESSED_EVENT, InputCode.getKeyByCode(-e.getButton()).toString());
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		processInputEvent(DEV_MOUSE, MOVED_EVENT, String.valueOf(e.getX()), String.valueOf(e.getY()));		
	}

	@Override public void mouseWheelMoved(MouseEvent e) {}
	@Override public void mouseClicked(MouseEvent e) {}
	@Override public void mouseEntered(MouseEvent e) {}
	@Override public void mouseExited(MouseEvent e) {}
	@Override public void mouseDragged(MouseEvent e) {}

}