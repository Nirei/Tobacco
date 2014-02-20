package tobacco.core.systems;

import tobacco.core.components.Component;
import tobacco.core.components.ContainerComponent;
import tobacco.core.entities.Entity;

public class InfoSystem implements EngineSystem {
	
	private static void printTreeAux(Entity entity, String str) {
		if(entity.contains(Component.DEBUGGING_C)) {
			System.out.println(str + entity + " {");
			for(Component c : entity) {
				if(c.getComponentType() != Component.DEBUGGING_C
						&& c.getComponentType() != Component.CONTAINER_C)
					System.out.println(str + "\t" + c);
			}
		
			if(entity.contains(Component.CONTAINER_C)) {
				ContainerComponent ccomp = (ContainerComponent) entity.getComponent(Component.CONTAINER_C);
				System.out.println(str + "\t" + ccomp);
				for(Entity e : ccomp) {
					printTreeAux(e, str+"\t\t");
				}
			}
			
			System.out.println(str + "}");
		}
	}
	
	public static void printTree(Entity entity) {
		printTreeAux(entity, "");
	}
	
	@Override
	public void work(Entity entity) {
		printTree(entity);
	}
}
