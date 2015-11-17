package tobacco.core.systems;

import tobacco.core.components.AnimationComponent;
import tobacco.core.components.Component;
import tobacco.core.components.TextureComponent;
import tobacco.core.components.Type;
import tobacco.core.entities.Entity;

public class AnimationSystem extends AbstractListSystem {
	
	private static final Type[] requiredComponents = {Component.ANIMATION_C, Component.TEXTURE_C};

	public AnimationSystem() {
		super(requiredComponents);
	}

	@Override
	public void process(Entity entity) {
		if(qualifies(entity)) {
			AnimationComponent animationComp = (AnimationComponent) entity.get(Component.ANIMATION_C);
			TextureComponent textureComp = (TextureComponent) entity.get(Component.TEXTURE_C);
			if(animationComp.isPlaying()) {
				int spriteFrames = textureComp.getFrames();
				int startFrame = animationComp.getStartFrame();
				long time = System.currentTimeMillis();
				long period = animationComp.getPeriod();
				long startTime = animationComp.getStartTime();
				long frameDelta = ((time - startTime) / period);
				
				// If the number of frames exceeds the end of the animation
				// we check if it's a loop and in that case we animate.
				// Else we stop the playing and save the current frame.
				if(frameDelta < spriteFrames || animationComp.isLoop()) {
					int frame = (int) ((startFrame + frameDelta) % spriteFrames);
					animationComp.setFrame(frame);
				} else {
					animationComp.stop();
				}
			}
		}
	}

	@Override
	public void setUp() {}

	@Override
	public void tearDown() {}

}
