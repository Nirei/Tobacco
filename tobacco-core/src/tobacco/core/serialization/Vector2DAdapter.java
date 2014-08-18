package tobacco.core.serialization;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import tobacco.core.util.Vector2D;

public class Vector2DAdapter extends XmlAdapter<AdaptedVector2D, Vector2D> {

	@Override
	public Vector2D unmarshal(AdaptedVector2D v) throws Exception {
		return new Vector2D(v.getX(), v.getY());
	}

	@Override
	public AdaptedVector2D marshal(Vector2D v) throws Exception {
		return new AdaptedVector2D(v.getX(), v.getY());
	}

}
