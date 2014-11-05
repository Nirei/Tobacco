package tobacco.core.systems;

public abstract class AbstractSystem implements EngineSystem {

	private boolean enabled = true;
	private long ticks = 0;

	/**
	 * Counts a tick
	 */
	protected void tick() {
		++ticks;
	}

	public AbstractSystem() {
		super();
	}

	@Override
	public void enable(boolean enabled) {
		this.enabled = enabled;
	}

	@Override
	public boolean isEnabled() {
		return enabled;
	}
	
	@Override
	public long getTicks() {
		return ticks;
	}
}