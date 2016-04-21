/*******************************************************************************
 * Tobacco - A portable and reusable game engine written in Java.
 * Copyright © 2016 Nirei
 *
 * This file is part of Tobacco
 *
 * Tobacco is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *******************************************************************************/
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
	public void process(Entity entity, long delta) {
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
