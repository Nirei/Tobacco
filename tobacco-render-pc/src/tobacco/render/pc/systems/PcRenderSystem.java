package tobacco.render.pc.systems;

import javax.media.opengl.GLProfile;

import tobacco.core.components.Component;
import tobacco.core.entities.Entity;
import tobacco.core.systems.EngineSystem;
import tobacco.render.pc.renderers.AWTRenderer;
import tobacco.render.pc.renderers.NewtRenderer;
import tobacco.render.pc.renderers.Renderer;

public class PcRenderSystem implements EngineSystem {
	
	Renderer renderer;
	
	public PcRenderSystem(Entity root) {
		GLProfile.initSingleton(); // Recomended before anything else
		renderer = new NewtRenderer("testnewt",root);
	}

	@Override
	public void work(Entity entity) {
		if(entity.contains(Component.DRAWABLE_C)) {
			renderer.render(entity);
		}
		/*if(entity.contains(Component.CONTAINER_C))
		{
			for(Entity e : (ContainerComponent) entity.getComponent(Component.CONTAINER_C)) {
				work(e);
			}
		}*/
	}

	public Renderer getRenderer() {
		return renderer;
	}

	public void setRenderer(Renderer renderer) {
		this.renderer = renderer;
	}



}
