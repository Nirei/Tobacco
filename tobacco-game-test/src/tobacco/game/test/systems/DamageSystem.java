package tobacco.game.test.systems;

import tobacco.core.collision.Collision;
import tobacco.core.components.CollisionMapComponent;
import tobacco.core.components.Entity;
import tobacco.core.components.RemoveComponent;
import tobacco.core.systems.EngineSystem;
import tobacco.game.test.components.DamageComponent;
import tobacco.game.test.components.GameComponent;
import tobacco.game.test.components.HealthComponent;
import tobacco.game.test.components.TeamComponent;

/**
 * This system detects bullet collisions and does as needed.
 * It applies damage and marks the bullet for removal.
 * @author nirei
 *
 */
public class DamageSystem implements EngineSystem {

	private void damage(Entity e1, Entity e2) {
		if(e1.has(GameComponent.DAMAGE_C) && e2.has(GameComponent.HEALTH_C)) {
			DamageComponent damComp = (DamageComponent) e1.get(GameComponent.DAMAGE_C);
			HealthComponent healthComp = (HealthComponent) e2.get(GameComponent.HEALTH_C);
			TeamComponent t1 = (TeamComponent) e1.get(GameComponent.TEAM_C);
			TeamComponent t2 = (TeamComponent) e2.get(GameComponent.TEAM_C);

			// Check bullets belong to different teams
			if(!t1.equals(t2)) {
				healthComp.hurt(damComp.getDamage());
			}

			if(e1.has(GameComponent.BULLET_C)) {
				e1.put(new RemoveComponent());
			}
		}
	}

	@Override
	public void work(Entity root) {
		CollisionMapComponent collMapComp = (CollisionMapComponent) root.get(GameComponent.COLLISIONMAP_C);
		for(Collision c : collMapComp) {
			Entity e1 = c.getE1();
			Entity e2 = c.getE2();
	
			damage(e1, e2);
			damage(e2, e1);
		}
	}

}
