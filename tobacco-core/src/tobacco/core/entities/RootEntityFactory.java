package tobacco.core.entities;

import java.util.LinkedList;
import java.util.List;

import tobacco.core.components.Component;
import tobacco.core.components.ContainerComponent;
import tobacco.core.components.DebuggingComponent;
import tobacco.core.components.ScreenComponent;
import tobacco.core.util.Vector2D;

/**
 * Generates a root Entity with the necessary components.
 * @author nirei
 */
public class RootEntityFactory extends EntityFactory {
	
	public RootEntityFactory() {}

	@Override
	public Entity create() {
		List<Component> comps = new LinkedList<Component>();
		ContainerComponent rootContainer = new ContainerComponent();
		comps.add(new DebuggingComponent());
		comps.add(new ScreenComponent(new Vector2D(480,640)));
		comps.add(rootContainer);
		
		return super.create(comps);
	}
}
