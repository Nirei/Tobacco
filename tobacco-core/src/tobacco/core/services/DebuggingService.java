package tobacco.core.services;

import tobacco.core.datatypes.GVector2D;
import tobacco.core.util.Line2D;

public interface DebuggingService {

	public void displayText(String tag, String text, GVector2D position);
	public void displayPoint(String tag, GVector2D position);
	public void displayVector(String tag, Line2D position);
	public void removeText(String tag);
	public void removePoint(String tag);
	public void removeVector(String tag);
	public void clearText();
	public void clearPoint();
	public void clearVectors();

}
