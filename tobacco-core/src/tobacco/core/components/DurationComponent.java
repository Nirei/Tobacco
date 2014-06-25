package tobacco.core.components;

public class DurationComponent implements Component {

	private long millis = 0;

	public DurationComponent() {
	}

	public DurationComponent(long _millis) {
		millis = _millis;
	}

	@Override
	public String getComponentType() {
		return DURATION_C;
	}

	public long getDuration() {
		return millis;
	}

	public void setDuration(long _millis) {
		millis = _millis;
	}

	@Override
	public String toString() {
		return "Duration: " + millis;
	}
}
