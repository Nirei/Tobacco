package tobacco.render.pc.components;

import tobacco.core.components.Type;

public class ZIndexComponent implements RendererComponent {
	
	private int zIndex = 0;
	
	public ZIndexComponent() {}
	
	public ZIndexComponent(int zIndex) {
		this.zIndex = zIndex;
	}

	@Override
	public Type getComponentType() {
		return RendererComponent.ZINDEX_C;
	}

	public int getZIndex() {
		return zIndex;
	}
	public void setZIndex(Integer zIndex) {
		this.zIndex = zIndex;
	}
}
