package tobacco.core.entities;

import tobacco.core.components.ContainerComponent;
import tobacco.core.components.DebuggingComponent;
import tobacco.core.components.ScreenComponent;
import tobacco.core.util.Vector2D;

public class RootEntityFactory {
	
	private RootEntityFactory() {}

	public static Entity create() {
		Entity root = new Entity();
		ContainerComponent rootContainer = new ContainerComponent();
		root = new Entity();
		root.add(new DebuggingComponent());
		root.add(new ScreenComponent(new Vector2D(480,640)));
		root.add(rootContainer);
		
		return root;
	}
}
