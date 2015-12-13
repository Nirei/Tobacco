package tobacco.core.components;

public class AnimationComponent implements Component {
	
	private long period;
	private long startTime = System.currentTimeMillis();
	private boolean loop = true;
	private boolean playing = true;
	private int frame = 0;
	private int startFrame;
	
	/**
	 * Creates an animation component.
	 * @param period - Duration of a frame in milliseconds.
	 */
	public AnimationComponent(long period) {
		this.period = period;
	}
	
	/**
	 * Creates an animation component.
	 * @param period - Duration of a frame in milliseconds.
	 * @param loop - Should this animation loop or just play once.
	 */
	public AnimationComponent(long period, boolean loop) {
		this(period);
		this.loop = loop;
	}
	
	/**
	 * Creates an animation component.
	 * @param period - Duration of a frame in milliseconds.
	 * @param loop - Should this animation loop or just play once.
	 * @param startFrame - This animations starting frame.
	 */
	public AnimationComponent(long period, boolean loop, int startFrame) {
		this(period, loop);
		this.startFrame = startFrame;
	}

	@Override
	public Type getComponentType() {
		return ANIMATION_C;
	}
	
	public long getPeriod() {
		return period;
	}
	
	public void setPeriod(long period) {
		this.period = period;
	}

	public boolean isLoop() {
		return loop;
	}

	public void setLoop(boolean loop) {
		this.loop = loop;
	}
	
	public boolean isPlaying() {
		return playing;
	}

	public void setPlaying(boolean playing) {
		this.playing = playing;
	}

	public int getFrame() {
		return frame;
	}

	public void setFrame(int frame) {
		this.frame = frame;
	}
	
	public long getStartTime() {
		return startTime;
	}
	
	public void setStartTime(long frameDelta) {
		this.startTime = frameDelta;
	}

	public int getStartFrame() {
		return this.startFrame;
	}

	public void setStartFrame(int startFrame) {
		this.startFrame = startFrame;
	}
	
	/**
	 * Starts the animation.
	 */
	public void start() {
		setPlaying(true);
		setFrame(0);
		setStartTime(System.currentTimeMillis());
	}
	
	/**
	 * Pauses the animation. Successive calls to start will
	 * play the animation from the frame where it was stopped.
	 */
	public void pause() {
		setPlaying(false);
		setStartFrame(this.frame);
	}
	
	/**
	 * Stops the animation. Successive calls to start will
	 * play the animation from frame 0.
	 */
	public void stop() {
		setPlaying(false);
		setStartFrame(0);
	}
}
