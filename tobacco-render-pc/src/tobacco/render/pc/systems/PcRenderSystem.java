package tobacco.render.pc.systems;


import javax.swing.JFrame;

import tobacco.core.components.Component;
import tobacco.core.entities.Entity;
import tobacco.core.systems.EngineSystem;

public class PcRenderSystem implements EngineSystem {

	private JFrame frame;
	
	public PcRenderSystem() {
		this.frame = new JFrame();
	}

	@Override
	public void work(Entity entity) {
		if(entity.contains(Component.DRAWABLE_C))
		{
			
		}
	}

}
