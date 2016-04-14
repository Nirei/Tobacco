/*******************************************************************************
 * Tobacco - A portable and reusable game engine written in Java.
 * Copyright © 2015 Nirei
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
package tobacco.core.services;

import java.util.ArrayList;
import java.util.List;

import tobacco.core.systems.EngineSystem;

public class DefaultGameService implements GameService {

	private Thread gameThread = null;
	private List<EngineSystem> systems;

	@Override
	public synchronized void start() {
		gameThread = new Thread(new Runnable() {
			
			long lastCall = System.currentTimeMillis();

			@Override
			public void run() {
				while (true) {
					
					long now = System.currentTimeMillis();
					long delta = now - lastCall;
					lastCall = now;
					
					for(EngineSystem s : systems) {
						s.work(delta);
					}
					try {
						Thread.sleep(5);
					} catch (InterruptedException e) {
						// TODO SO WHAT
					}
				}
			}
		});
		
		gameThread.start();
	}

	@Override
	public List<EngineSystem> getSystems() {
		return new ArrayList<EngineSystem>(systems);
	}

	@Override
	public void setSystems(List<EngineSystem> systems) {
		this.systems = systems;
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}
}
