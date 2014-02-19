package tobacco.core.util;

public class Angle {
	private float value;

	public float getValue() {
		return value;
	}

	public void setValue(float value) {
		this.value = value;
	}

	public Angle(float value) {
		super();
		this.value = value;
	} 
	
	public void normalize ()
	{
		if(value<-Math.PI)
		{
			value+=2*(Math.PI);
			normalize();
		}
		else if(value>Math.PI)
		{
			value-=2*(Math.PI);
			normalize();				
		}			
	}
	
}
