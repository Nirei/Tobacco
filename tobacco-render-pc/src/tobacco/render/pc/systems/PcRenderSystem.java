package tobacco.render.pc.systems;

import javax.media.opengl.GLProfile;

import tobacco.core.components.Component;
import tobacco.core.entities.Entity;
import tobacco.core.systems.EngineSystem;
import tobacco.render.pc.renderers.AWTRenderer;
import tobacco.render.pc.renderers.Renderer;

public class PcRenderSystem implements EngineSystem {
	
	Renderer renderer;
	
	public PcRenderSystem() {
		GLProfile.initSingleton(); // Recomended before anything else
		renderer = new AWTRenderer("Test 0");
	}

	@Override
	public void work(Entity entity) {
		if(entity.contains(Component.DRAWABLE_C)) {
			renderer.render(entity);
		}
	}

}
