package tobacco.core.components;

import tobacco.core.movement.Trajectory;

public class TrajectoryComponent implements Component {

	private Trajectory trajectory;
	private int step = 0;
	private boolean loop = false;

	public Trajectory getTrajectory() {
		return trajectory;
	}

	public void setTrajectory(Trajectory trajectory) {
		this.trajectory = trajectory;
	}

	public int getStep() {
		return step;
	}

	public void setStep(int step) {
		this.step = step;
	}

	public boolean isLoop() {
		return loop;
	}

	public void setLoop(boolean loop) {
		this.loop = loop;
	}

	@Override
	public String getComponentType() {
		return TRAJECTORY_C;
	}

}
