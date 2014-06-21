package tobacco.core.util;

public class Angle {
	private float value;
	private float degrees;

	public float getValue() {
		return value;
	}
	
	public float getDegrees() {
		return degrees;
	}

	public Angle(float value) {
		this.value = value;
		this.degrees = (180f/(float) Math.PI) * value;
	}
	
	// TODO: Qué es esta puta mierda?
//	public void normalize ()
//	{
//		if(value<-Math.PI)
//		{
//			value+=2*(Math.PI);
//			normalize();
//		}
//		else if(value>Math.PI)
//		{
//			value-=2*(Math.PI);
//			normalize();				
//		}			
//	}
	
}
