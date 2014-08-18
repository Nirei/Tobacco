package tobacco.game.test.components;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class DamageComponent implements GameComponent {
	
	private float damage;
	
	public DamageComponent() {}

	public DamageComponent(float damage) {
		this.damage = damage;
	}

	@Override
	public String getComponentType() {
		return GameComponent.DAMAGE_C;
	}

	@XmlAttribute
	public float getDamage() {
		return damage;
	}

	public void setDamage(float damage) {
		this.damage = damage;
	}

}
