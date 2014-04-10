package tobacco.core.systems;

import tobacco.core.components.Component;
import tobacco.core.entities.Entity;

public class InfoSystem extends AbstractTreeSystem {
	
	private static final String[] requiredComponents = {Component.DEBUGGING_C};
	private int tick = 0;
	
	public InfoSystem() {
		super(requiredComponents);
	}

	@Override
	public Object process(Entity entity, Object data) {
		String str = (String) data;
		if(str == null) str = "";

		if(qualifies(entity)) {
			System.out.println(str + entity + " {");
			for(Component c : entity)
				if(c.getComponentType() != Component.DEBUGGING_C)
					System.out.println(str + "\t" + c);
			System.out.println(str + "}");
		}

		return str + "\t";
	}

	@Override public void setUp() {
		System.out.println("Tick: " + tick++ + " ---");
	}

	@Override public void tearDown() {}
}
