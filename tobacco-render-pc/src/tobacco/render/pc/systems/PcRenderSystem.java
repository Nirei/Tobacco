package tobacco.render.pc.systems;

import javax.media.opengl.GLProfile;

import tobacco.core.components.Component;
import tobacco.core.components.Entity;
import tobacco.core.systems.EngineSystem;
import tobacco.render.pc.renderers.NewtRenderer;
import tobacco.render.pc.renderers.Renderer;

public class PcRenderSystem implements EngineSystem {

	Renderer renderer;

	public PcRenderSystem(Entity root) {
		GLProfile.initSingleton(); // Recomended before anything else
		renderer = new NewtRenderer("The Game", root);
	}

	@Override
	public void work(Entity root) {
		if (root.contains(Component.DRAWABLE_C)) {
			renderer.render(root);
		}
	}

	public Renderer getRenderer() {
		return renderer;
	}

	public void setRenderer(Renderer renderer) {
		this.renderer = renderer;
	}

}
