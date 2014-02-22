package tobacco.core.components;

public class PlayerComponent implements Component {

	@Override
	public String getComponentType() {
		return PLAYER_C;
	}

	@Override
	public String toString() {
		return "Player";
	}
}
