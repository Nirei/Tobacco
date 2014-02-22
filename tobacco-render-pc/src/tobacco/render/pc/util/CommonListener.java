package tobacco.render.pc.util;

import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelListener;

import com.jogamp.newt.event.KeyListener;
import com.jogamp.newt.event.MouseListener;

public interface CommonListener extends KeyListener,
		java.awt.event.KeyListener, MouseListener,
		java.awt.event.MouseListener, MouseMotionListener, MouseWheelListener {

}
