package tobacco.core.actions;

import java.util.Arrays;

public class Command {
	
	private String command;
	private String[] arguments;
	
	public Command(String _command, String[] _arguments) {
		command = _command;
		arguments = _arguments;
	}
	
	public String getCommand() {
		return command;
	}
	
	public String[] getArguments() {
		return arguments;
	}
	
	@Override
	public String toString() {
		return command + " " + Arrays.toString(arguments);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((command == null) ? 0 : command.hashCode());
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
		Command other = (Command) obj;
		if (command == null) {
			if (other.command != null)
				return false;
		} else if (!command.equals(other.command))
			return false;
		return true;
	}
}
