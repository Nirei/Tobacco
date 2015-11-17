package tobacco.game.test.entities;

import static tobacco.core.util.InputType.TYPE_HOLD;
import static tobacco.core.util.InputType.TYPE_PRESS;
import static tobacco.core.util.InputType.TYPE_RELEASE;
import tobacco.core.components.Component;
import tobacco.core.components.ContainerComponent;
import tobacco.core.components.DebuggingComponent;
import tobacco.core.components.MovementComponent;
import tobacco.core.components.PlayerComponent;
import tobacco.core.components.PositionComponent;
import tobacco.core.components.SizeComponent;
import tobacco.core.components.SolidityComponent;
import tobacco.core.components.TextureComponent;
import tobacco.core.entities.Entity;
import tobacco.core.services.Directory;
import tobacco.core.util.Command;
import tobacco.core.util.InputEvent;
import tobacco.core.util.Vector2D;
import tobacco.game.test.components.GameComponent;
import tobacco.game.test.components.GunComponent;
import tobacco.game.test.components.HealthComponent;
import tobacco.game.test.components.TeamComponent;
import tobacco.render.pc.input.PcInputCode;

public class PlayerEntityFactory implements EntityFactory {

	private String texturePath;
	private int textureWidth, textureHeight;
	private PcInputCode keyUp;
	private PcInputCode keyDown;
	private PcInputCode keyLeft;
	private PcInputCode keyRight;
	private PcInputCode keyShoot;
	private PcInputCode keySpell;
	private PcInputCode keyFocus;
	private PcInputCode keyMenu;
	
	public PlayerEntityFactory(String texturePath, int textureWidth, int textureHeight) {
		super();
		this.texturePath = texturePath;
		this.textureWidth = textureWidth;
		this.textureHeight = textureHeight;
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
		
		player = Directory.getEntityService().create();
		player.add(new DebuggingComponent());
		player.add(new TextureComponent(texturePath, textureWidth, textureHeight));
		player.add(new SizeComponent(new Vector2D(textureWidth, textureHeight)));
		player.add(new PositionComponent(new Vector2D(0f, -200f)));

		PlayerComponent playerComp = new PlayerComponent();
		Command up = moveCommand(0, 1);
		Command down = moveCommand(0, -1);
		Command left = moveCommand(-1, 0);
		Command right = moveCommand(1, 0);
		Command suicide = (rootEntity, entity) -> ((HealthComponent) entity.get(GameComponent.HEALTH_C)).setHealth(0f);

		playerComp.put(new InputEvent(keyUp, TYPE_HOLD), up);
		playerComp.put(new InputEvent(keyDown, TYPE_HOLD), down);
		playerComp.put(new InputEvent(keyLeft, TYPE_HOLD), left);
		playerComp.put(new InputEvent(keyRight, TYPE_HOLD), right);
		playerComp.put(new InputEvent(keyMenu, TYPE_RELEASE), suicide);

		playerComp.put(new InputEvent(keyShoot, TYPE_PRESS),
				(rootEntity, entity) -> ((GunComponent) entity.get(GameComponent.GUN_C)).setShooting(true));

		playerComp.put(new InputEvent(keyShoot, TYPE_RELEASE),
				(rootEntity, entity) -> ((GunComponent) entity.get(GameComponent.GUN_C)).setShooting(false));
		
		player.add(playerComp);

		ContainerComponent containerComponent = new ContainerComponent();
		GunComponent gunComponent = new GunComponent();
		
		BulletEntityFactory bef = new BulletEntityFactory("/tobacco/game/test/textures/reimubullet.png", new Vector2D(52f, 12f), new Vector2D(-1f, 5f), 150, 2000f, 50f);
		containerComponent.addChild(bef.create());
		bef.setDirection(new Vector2D(0, 1));
		containerComponent.addChild(bef.create());
		bef.setDirection(new Vector2D(1, 5));
		containerComponent.addChild(bef.create());

		player.add(gunComponent);
		player.add(containerComponent);
		player.add(new MovementComponent(500f));
		player.add(new HealthComponent(100f));
		player.add(new SolidityComponent(10f));
		player.add(new TeamComponent("PLAYER"));
		
		return player;
	}
	
	public String getSpritePath() {
		return texturePath;
	}

	/**
	 * Sets the path of the texture for the created player.
	 * @param texturePath - path of the sprite.
	 */
	public void setTexturePath(String texturePath) {
		this.texturePath = texturePath;
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

	public int getTextureWidth() {
		return textureWidth;
	}

	public void setTextureWidth(int textureWidth) {
		this.textureWidth = textureWidth;
	}

	public int getTextureHeight() {
		return textureHeight;
	}

	public void setTextureHeight(int textureHeight) {
		this.textureHeight = textureHeight;
	}

}
