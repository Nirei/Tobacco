package tobacco.core.systems;

import tobacco.core.components.Component;
import tobacco.core.components.ContainerComponent;
import tobacco.core.entities.Entity;

public class InfoSystem implements EngineSystem {


	private static  void printEntity(Entity entity, String str) {
		System.out.println(str + entity + " {");
		for(Component c : entity) {
			if(c.getComponentType() != Component.DEBUGGING_C)
				System.out.println(str + "\t" + c);
		}
		System.out.println(str + "}");
	}
	
	private static void printTreeAux(Entity entity, String str) {
		if(entity.contains(Component.DEBUGGING_C)) {
			printEntity(entity,str);
		}

		if(entity.contains(Component.CONTAINER_C)) {
			for(Entity e : (ContainerComponent) entity.getComponent(Component.CONTAINER_C)) {
				printTreeAux(e, str+str);
			}
		}
	}
	
	public static void printTree(Entity entity) {
		printTreeAux(entity, "\t");
	}
	
	@Override
	public void work(Entity entity) {
		printTree(entity);
	}
}
