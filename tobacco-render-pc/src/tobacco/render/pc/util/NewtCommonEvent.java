package tobacco.render.pc.util;

import com.jogamp.newt.event.KeyEvent;
import com.jogamp.newt.event.MouseEvent;



public class NewtCommonEvent implements CommonEvent{

	KeyEvent ke;
	MouseEvent me;
	public NewtCommonEvent(KeyEvent ke, MouseEvent me) {
		super();		
	}

	
}
