package tobacco.game.test.components;

import tobacco.core.components.Type;

public class TeamComponent implements GameComponent {

	private String team;
	
	public TeamComponent() {}

	public TeamComponent(String team) {
		this.team = team;
	}

	@Override
	public Type getComponentType() {
		return TEAM_C;
	}
	
	public String getTeam() {
		return team;
	}
	
	public void setTeam(String team) {
		this.team = team;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((team == null) ? 0 : team.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TeamComponent other = (TeamComponent) obj;
		if (team == null) {
			if (other.team != null)
				return false;
		} else if (!team.equals(other.team))
			return false;
		return true;
	}
}
