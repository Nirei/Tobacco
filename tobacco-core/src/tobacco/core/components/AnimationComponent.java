package tobacco.core.components;

public class AnimationComponent implements Component {
	
	private long period = 0;
	private long startTime = System.currentTimeMillis();
	private boolean loop = true;
	private boolean playing = true;
	private int frame = 0;
	private int startFrame = 0;
	
	/**
	 * Creates an animation component
	 */
	public AnimationComponent() {}
	
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
	
	public void setPeriod(Long period) {
		this.period = period;
	}

	public boolean isLoop() {
		return loop;
	}

	public void setLoop(Boolean loop) {
		this.loop = loop;
	}
	
	public boolean isPlaying() {
		return playing;
	}

	public void setPlaying(Boolean playing) {
		this.playing = playing;
	}

	public int getFrame() {
		return frame;
	}

	public void setFrame(Integer frame) {
		this.frame = frame;
	}
	
	public long getStartTime() {
		return startTime;
	}
	
	public void setStartTime(Long frameDelta) {
		this.startTime = frameDelta;
	}

	public int getStartFrame() {
		return this.startFrame;
	}

	public void setStartFrame(Integer startFrame) {
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

	@Override
	public String toString() {
		return "Animation: [period=" + period + ", startTime=" + startTime + ", loop=" + loop + ", playing="
				+ playing + ", frame=" + frame + ", startFrame=" + startFrame + "]";
	}
}
