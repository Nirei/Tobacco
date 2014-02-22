package tobacco.core.components;

import java.util.concurrent.ArrayBlockingQueue;

import tobacco.core.actions.Command;

public class CommandBufferComponent extends ArrayBlockingQueue<Command> implements Component {

	private static final long serialVersionUID = -3966302575968806456L;

	public CommandBufferComponent() {
		super(200);
	}

	@Override
	public String getComponentType() {
		return COMMAND_BUFFER_C;
	}
	
	@Override
	public String toString() {
		return "Command buffer: " + super.toString();
	}
}
