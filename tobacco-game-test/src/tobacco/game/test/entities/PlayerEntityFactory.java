/*******************************************************************************
 * Tobacco - A portable and reusable game engine written in Java.
 * Copyright © 2015 Nirei
 *
 * This file is part of Tobacco
 *
 * Tobacco is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *******************************************************************************/
package tobacco.game.test.entities;

import static tobacco.core.util.InputType.TYPE_HOLD;
import static tobacco.core.util.InputType.TYPE_PRESS;
import static tobacco.core.util.InputType.TYPE_RELEASE;

import tobacco.core.components.AnimationComponent;
import tobacco.core.components.Component;
import tobacco.core.components.ContainerComponent;
import tobacco.core.components.DebuggingComponent;
import tobacco.core.components.MovementComponent;
import tobacco.core.components.NameComponent;
import tobacco.core.components.PlayerComponent;
import tobacco.core.components.PositionComponent;
import tobacco.core.components.SizeComponent;
import tobacco.core.components.SolidityComponent;
import tobacco.core.components.TextureComponent;
import tobacco.core.entities.Entity;
import tobacco.core.entities.EntityFactory;
import tobacco.core.services.Directory;
import tobacco.core.services.EntityService;
import tobacco.core.util.Command;
import tobacco.core.util.InputEvent;
import tobacco.core.util.Vector2D;
import tobacco.game.test.components.GameComponent;
import tobacco.game.test.components.GunComponent;
import tobacco.game.test.components.HealthComponent;
import tobacco.game.test.components.TeamComponent;
import tobacco.render.pc.components.ZIndexComponent;
import tobacco.render.pc.input.PcInputCode;

public class PlayerEntityFactory extends EntityFactory {

	private float normalSpeed = 400f;
	private float focusSpeed = 160f;
	
	private BulletEntityFactory bef;
	private TextureComponent texture;
	private Vector2D size;
	private PcInputCode keyUp;
	private PcInputCode keyDown;
	private PcInputCode keyLeft;
	private PcInputCode keyRight;
	private PcInputCode keyShoot;
	private PcInputCode keySpell;
	private PcInputCode keyFocus;
	private PcInputCode keyMenu;
	
	public PlayerEntityFactory(EntityService entServ, TextureComponent texture, Vector2D size) {
		super(entServ);
		this.texture = texture;
		this.size = size;
		
		TextureComponent bulletTexture = new TextureComponent("textures/reimubullet.png", 52, 12);
		bef = new BulletEntityFactory(entServ, bulletTexture, new Vector2D(52f, 12f), new Vector2D(-1f, 5f), 150, 2000f, 50f, 90f);

	}
	
	private Command moveCommand(final float x, final float y) {
		return (rootEntity, entity) -> {
				if (entity.has(Component.MOVEMENT_C)) {
					Vector2D direction;
					MovementComponent movComp = (MovementComponent) entity.get(Component.MOVEMENT_C);
					direction = Vector2D.sum(movComp.getDirection(), new Vector2D(x, y));
					movComp.setDirection(direction);
				}
			};
	}

	@Override
	public Entity create() {
		Entity player;
		
		player = super.create();
		player.add(new DebuggingComponent());
		player.add(texture);
		player.add(new AnimationComponent(125));
		player.add(new SizeComponent(size));
		player.add(new PositionComponent(new Vector2D(0f, -200f)));
		player.add(new NameComponent("player"));

		PlayerComponent playerComp = new PlayerComponent();
		Command up = moveCommand(0, 1);
		Command down = moveCommand(0, -1);
		Command left = moveCommand(-1, 0);
		Command right = moveCommand(1, 0);
		Command pause = (rootEntity, entity) -> {
			((HealthComponent) entity.get(GameComponent.HEALTH_C)).setHealth(0f);
		};

		playerComp.put(new InputEvent(keyUp, TYPE_HOLD), up);
		playerComp.put(new InputEvent(keyDown, TYPE_HOLD), down);
		playerComp.put(new InputEvent(keyLeft, TYPE_HOLD), left);
		playerComp.put(new InputEvent(keyRight, TYPE_HOLD), right);
		playerComp.put(new InputEvent(keyMenu, TYPE_RELEASE), pause);

		playerComp.put(new InputEvent(keyShoot, TYPE_PRESS),
				(rootEntity, entity) -> ((GunComponent) entity.get(GameComponent.GUN_C)).setShooting(true));

		playerComp.put(new InputEvent(keyShoot, TYPE_RELEASE),
				(rootEntity, entity) -> ((GunComponent) entity.get(GameComponent.GUN_C)).setShooting(false));
		
		playerComp.put(new InputEvent(keyFocus, TYPE_PRESS),
				(rootEntity, entity) -> ((MovementComponent) entity.get(GameComponent.MOVEMENT_C)).setSpeed(focusSpeed));

		playerComp.put(new InputEvent(keyFocus, TYPE_RELEASE),
				(rootEntity, entity) -> ((MovementComponent) entity.get(GameComponent.MOVEMENT_C)).setSpeed(normalSpeed));

		player.add(playerComp);

		ContainerComponent containerComponent = new ContainerComponent();
		GunComponent gunComponent = new GunComponent();
		
		containerComponent.addChild(bef.create());
		bef.setDirection(new Vector2D(0, 1));
		containerComponent.addChild(bef.create());
		bef.setDirection(new Vector2D(1, 5));
		containerComponent.addChild(bef.create());

		player.add(gunComponent);
		player.add(containerComponent);
		player.add(new MovementComponent(normalSpeed));
		player.add(new HealthComponent(400f));
		player.add(new SolidityComponent(2f));
		player.add(new TeamComponent("PLAYER"));
		player.add(new ZIndexComponent());
		
		return player;
	}
	
	public TextureComponent getTexture() {
		return texture;
	}

	/**
	 * Sets the {@link TextureComponent} for the created player.
	 * @param texturePath - path of the sprite.
	 */
	public void setTexture(TextureComponent texture) {
		this.texture = texture;
	}

	/**
	 * Sets the keys for the created player's movement.
	 * @param keyUp - The key code for up movement.
	 * @param keyDown - The key code for down movement.
	 * @param keyLeft - The key code for left movement.
	 * @param keyRight - The key code for right.
	 */
	public void setMovementKeys(PcInputCode keyUp, PcInputCode keyDown, PcInputCode keyLeft, PcInputCode keyRight) {
		this.keyUp = keyUp;
		this.keyDown = keyDown;
		this.keyLeft = keyLeft;
		this.keyRight = keyRight;
	}

	/**
	 * Sets the keys for the created player's actions.
	 * @param keyShoot - The key code for shooting.
	 * @param keySpell - The key code for launching a spell.
	 * @param keyFocus - The key code for focusing.
	 * @param keyMenu - The key code for entering the menu.
	 */
	public void setActionKeys(PcInputCode keyShoot, PcInputCode keySpell, PcInputCode keyFocus, PcInputCode keyMenu) {
		this.keyShoot = keyShoot;
		this.keySpell = keySpell;
		this.keyFocus = keyFocus;
		this.keyMenu = keyMenu;
	}

}
