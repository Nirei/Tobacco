package tobacco.core.services;

import tobacco.core.datatypes.GVector2D;
import tobacco.core.util.Line2D;

public final class NullDebuggingService implements DebuggingService {
	
	private static void printWarning(String method) {
		System.err.println("NullDebuggingService received a call to " + method);
	}

	@Override
	public void clearText() {
		printWarning("clearText()");
	}

	@Override
	public void clearPoint() {
		printWarning("clearPoint()");
	}

	@Override
	public void clearVectors() {
		printWarning("clearVectors()");
	}

	@Override
	public void displayText(String tag, String text, GVector2D position) {
		printWarning("displayText()");
		
	}

	@Override
	public void displayPoint(String tag, GVector2D position) {
		printWarning("displayPoint()");
		
	}

	@Override
	public void displayVector(String tag, Line2D position) {
		printWarning("displayVector()");
		
	}

	@Override
	public void removeText(String tag) {
		printWarning("removeText()");
		
	}

	@Override
	public void removePoint(String tag) {
		printWarning("removePoint()");
		
	}

	@Override
	public void removeVector(String tag) {
		printWarning("removeVector()");		
	}
}
