package tobacco.game.test.components;

import java.util.HashMap;
import java.util.Map;

public class TeamComponent implements GameComponent {

	private static final Map<String, TeamComponent> teamNames = new HashMap<String, TeamComponent>();
	private static int counter = 0;

	private final String teamName;
	private final int teamNumber;

	private TeamComponent(String teamName) {
		this.teamName = teamName;
		teamNames.put(teamName, this);
		teamNumber = counter++;
	}

	@Override
	public String getComponentType() {
		return TEAM_C;
	}

	public static TeamComponent getTeam(String teamName) {
		if(teamNames.containsKey(teamName)) {
			return teamNames.get(teamName);
		} else {
			return new TeamComponent(teamName);
		}
	}
	
	public String getTeamName() {
		return teamName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + teamNumber;
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
		if (teamNumber != other.teamNumber)
			return false;
		return true;
	}

}
