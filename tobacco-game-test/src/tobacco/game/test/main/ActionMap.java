package tobacco.game.test.main;

import java.util.ArrayList;
import java.util.List;

import com.jogamp.newt.event.KeyEvent;

import tobacco.core.components.Component;
import tobacco.core.components.MovementComponent;
import tobacco.core.components.PositionComponent;
import tobacco.core.entities.Entity;
import tobacco.core.util.Action;
import tobacco.core.util.RawInputElement;
import tobacco.core.util.Vector2D;

public class ActionMap {

	private static List<Action> actionList;

	private ActionMap() {}
	
	public static List<Action> actionList() {
		if(actionList == null) {
			actionList = new ArrayList<Action>();
			
			actionList.add(new AbstractAction(new RawInputElement(KeyEvent.VK_UP), "Up") {
				@Override
				public void process(RawInputElement rawIn, Entity entity) {
					MovementComponent movComp = ((MovementComponent) entity.getComponent(Component.MOVEMENT_C));
					if(rawIn.getValue() == RawInputElement.VALUE_PRESSED)
						movComp.setDirection(Vector2D.sum(movComp.getDirection(), new Vector2D(0,1)));
					else if(rawIn.getValue() == RawInputElement.VALUE_RELEASED)
						movComp.setDirection(Vector2D.minus(movComp.getDirection(), new Vector2D(0,1)));					
					}
			});

			actionList.add(new AbstractAction(new RawInputElement(KeyEvent.VK_DOWN), "Down") {				
				@Override
				public void process(RawInputElement rawIn, Entity entity) {
					MovementComponent movComp = ((MovementComponent) entity.getComponent(Component.MOVEMENT_C));
					if(rawIn.getValue() == RawInputElement.VALUE_PRESSED)
						movComp.setDirection(Vector2D.sum(movComp.getDirection(), new Vector2D(0,-1)));
					else if(rawIn.getValue() == RawInputElement.VALUE_RELEASED)
						movComp.setDirection(Vector2D.minus(movComp.getDirection(), new Vector2D(0,-1)));
					
				}
			});
			
			actionList.add(new AbstractAction(new RawInputElement(KeyEvent.VK_LEFT), "Left") {				
				@Override
				public void process(RawInputElement rawIn, Entity entity) {
					MovementComponent movComp = ((MovementComponent) entity.getComponent(Component.MOVEMENT_C));
					if(rawIn.getValue() == RawInputElement.VALUE_PRESSED)
						movComp.setDirection(Vector2D.sum(movComp.getDirection(), new Vector2D(-1,0)));
					else if(rawIn.getValue() == RawInputElement.VALUE_RELEASED)
						movComp.setDirection(Vector2D.minus(movComp.getDirection(), new Vector2D(-1,0)));
				}
			});
			
			actionList.add(new AbstractAction(new RawInputElement(KeyEvent.VK_RIGHT), "Right") {
				@Override
				public void process(RawInputElement rawIn, Entity entity) {
					MovementComponent movComp = ((MovementComponent) entity.getComponent(Component.MOVEMENT_C));
					if(rawIn.getValue() == RawInputElement.VALUE_PRESSED)
						movComp.setDirection(Vector2D.sum(movComp.getDirection(), new Vector2D(1,0)));
					else if(rawIn.getValue() == RawInputElement.VALUE_RELEASED)
						movComp.setDirection(Vector2D.minus(movComp.getDirection(), new Vector2D(1,0)));				
				}
			});
		}

		return actionList;
	}
}
