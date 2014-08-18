package tobacco.game.test.components;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class TeamComponent implements GameComponent {

	private String team;
	
	public TeamComponent() {}

	public TeamComponent(String team) {
		this.team = team;
	}

	@Override
	public String getComponentType() {
		return TEAM_C;
	}
	
	@XmlAttribute
	public String getTeam() {
		return team;
	}
	
	public void setTeam(String team) {
		this.team = team;
	}
}
