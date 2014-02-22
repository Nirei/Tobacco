package tobacco.game.test.main;

import java.util.ArrayList;
import java.util.List;

import com.jogamp.newt.event.KeyEvent;

import tobacco.core.components.Component;
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
					PositionComponent comp = ((PositionComponent) entity.getComponent(Component.POSITION_C));
					Vector2D pos = comp.getPosition();
					if(rawIn.getValue() == RawInputElement.VALUE_PRESSED)
						comp.setPosition(new Vector2D(pos.getX(), pos.getY()+20));
				}
			});

			actionList.add(new AbstractAction(new RawInputElement(KeyEvent.VK_DOWN), "Down") {				
				@Override
				public void process(RawInputElement rawIn, Entity entity) {
					PositionComponent comp = ((PositionComponent) entity.getComponent(Component.POSITION_C));
					Vector2D pos = comp.getPosition();
					if(rawIn.getValue() == RawInputElement.VALUE_PRESSED)
						comp.setPosition(new Vector2D(pos.getX(), pos.getY()-20));
				}
			});
			
			actionList.add(new AbstractAction(new RawInputElement(KeyEvent.VK_LEFT), "Left") {				
				@Override
				public void process(RawInputElement rawIn, Entity entity) {
					PositionComponent comp = ((PositionComponent) entity.getComponent(Component.POSITION_C));
					Vector2D pos = comp.getPosition();
					if(rawIn.getValue() == RawInputElement.VALUE_PRESSED)
						comp.setPosition(new Vector2D(pos.getX()-20, pos.getY()));
				}
			});
			
			actionList.add(new AbstractAction(new RawInputElement(KeyEvent.VK_RIGHT), "Right") {
				@Override
				public void process(RawInputElement rawIn, Entity entity) {
					PositionComponent comp = ((PositionComponent) entity.getComponent(Component.POSITION_C));
					Vector2D pos = comp.getPosition();
					if(rawIn.getValue() == RawInputElement.VALUE_PRESSED)
						comp.setPosition(new Vector2D(pos.getX()+20, pos.getY()));
				}
			});
		}

		return actionList;
	}
}
