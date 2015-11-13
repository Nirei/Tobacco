package tobacco.game.test.systems;

import java.util.List;

import tobacco.core.components.ContainerComponent;
import tobacco.core.components.PositionComponent;
import tobacco.core.components.Type;
import tobacco.core.entities.Entity;
import tobacco.core.services.Directory;
import tobacco.core.systems.AbstractListSystem;
import tobacco.core.util.Vector2D;
import tobacco.game.test.components.DirectionComponent;
import tobacco.game.test.components.GameComponent;
import tobacco.game.test.components.GunComponent;

public class EnemyControlSystem extends AbstractListSystem {
	
	private static final Type[] requiredComponents = {
		GameComponent.GUN_C,
		GameComponent.CONTAINER_C,
		GameComponent.POSITION_C
	};
	
	private List<Entity> players;

	public EnemyControlSystem() {
		super(requiredComponents);
	}

	@Override
	public void process(Entity entity) {
		if(qualifies(entity) && !entity.has(GameComponent.PLAYER_C)) {
			PositionComponent closestPlayerPos = null;
			float closestDist = Float.MAX_VALUE;
			
			PositionComponent enemyPos = (PositionComponent) entity.get(GameComponent.POSITION_C);
			ContainerComponent enemyContainer = (ContainerComponent) entity.get(GameComponent.CONTAINER_C);
			GunComponent enemyGun = (GunComponent) entity.get(GameComponent.GUN_C);
			
			// Select closest player
			for(Entity p : players) {
				PositionComponent playerPos;
				
				if(p.has(GameComponent.POSITION_C)) {
					playerPos = (PositionComponent) p.get(GameComponent.POSITION_C);
					float distance = Vector2D.minus(playerPos.getPosition(), enemyPos.getPosition()).module();
					if(distance < closestDist) {
						closestDist = distance;
						closestPlayerPos = playerPos;
					}
				}
			}
			
			// Shoot 'em
			if(closestPlayerPos != null) {
				for(Entity b : enemyContainer) {
					if(b.has(GameComponent.BULLET_DATA_C)) {
						DirectionComponent dirComp = (DirectionComponent) b.get(GameComponent.DIRECTION_C);
						dirComp.setDirection(Vector2D.minus(closestPlayerPos.getPosition(), enemyPos.getPosition()));
					}
				}
				enemyGun.setShooting(true);
			} else {
				enemyGun.setShooting(false);
			}
		}
	}

	@Override
	public void setUp() {
		players = Directory.getEntityService().findAll(GameComponent.PLAYER_C);
	}

	@Override
	public void tearDown() {}

}
