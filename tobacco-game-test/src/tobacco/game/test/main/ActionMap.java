package tobacco.game.test.main;

import tobacco.core.actions.Action;
import tobacco.core.actions.Command;
import tobacco.core.components.Component;
import tobacco.core.components.ContainerComponent;
import tobacco.core.components.ControlableComponent;
import tobacco.core.components.MovementComponent;
import tobacco.core.entities.Entity;
import tobacco.core.util.Vector2D;
import tobacco.game.test.components.GameComponent;
import tobacco.game.test.entities.BulletEntityFactory;
import tobacco.render.pc.systems.PcInputSystem;
import tobacco.render.pc.util.InputCode;

public class ActionMap {

	private ActionMap() {}
	
	public static void addLogic(final Entity player) {
		ControlableComponent ccomp = (ControlableComponent) player.getComponent(GameComponent.CONTROLABLE_C);
		if(ccomp != null) {
			ccomp.put(PcInputSystem.INPUT_COMMAND, new Action() {
				
				private final Vector2D up = new Vector2D(0,1);
				private final Vector2D down = new Vector2D(0,-1);
				private final Vector2D left = new Vector2D(-1,0);
				private final Vector2D right = new Vector2D(1,0);
				private BulletEntityFactory bulletFactory = new BulletEntityFactory(player);
				
				private void moveEntity(Entity entity, Vector2D direction) {
					MovementComponent movComp = (MovementComponent) entity.getComponent(Component.MOVEMENT_C);
					Vector2D current = movComp.getDirection();
					Vector2D newDirection = Vector2D.sum(current, direction);
					movComp.setDirection(newDirection);
				}
				
				private void shoot(Entity root) {
					ContainerComponent children = (ContainerComponent) root.getComponent(GameComponent.CONTAINER_C);
					Entity bullet;
					if((bullet = bulletFactory.create()) != null)
						children.addChild(bullet);
				}
				
				@Override
				public void process(Command call, Entity root, Entity entity) {
					String args[] = call.getArguments();
					String device = args[0];
					String eventType = args[1];
					if(device.equals(PcInputSystem.DEV_KEYBOARD)) {
						InputCode key;
						switch(eventType) {
						case PcInputSystem.PRESSED_EVENT :
							key = InputCode.valueOf(args[2]);
							switch(key) {
							case KEY_UP :
								moveEntity(entity, up);
								break;
							case KEY_DOWN :
								moveEntity(entity, down);
								break;
							case KEY_LEFT :
								moveEntity(entity, left);
								break;
							case KEY_RIGHT :
								moveEntity(entity, right);
								break;
							case KEY_SPACE :
								shoot(root);
							default :
								break;
							}
							break;
						case PcInputSystem.RELEASED_EVENT :
							key = InputCode.valueOf(args[2]);
							switch(key) {
							case KEY_UP :
								moveEntity(entity, down);
								break;
							case KEY_DOWN :
								moveEntity(entity, up);
								break;
							case KEY_LEFT :
								moveEntity(entity, right);
								break;
							case KEY_RIGHT :
								moveEntity(entity, left);
								break;
							default :
								break;
							}
							break;
						}
					}
				}
			});
		}
	}
}
