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
package tobacco.core.game;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;

import tobacco.core.entities.EntityService;
import tobacco.core.services.Directory;
import tobacco.core.systems.EngineSystem;

public class DefaultGameService implements GameService {

	private Thread gameThread = null;
	private GameState state = GameState.LOAD;
	private EnumMap<GameState, List<EngineSystem>> stateSystems = new EnumMap<>(GameState.class);
	private EnumMap<GameState, EntityService> stateWorlds = new EnumMap<>(GameState.class);
	
	public DefaultGameService() {
		List<EngineSystem> empty = new ArrayList<EngineSystem>(0);
		for(GameState gs : GameState.values())
			stateSystems.put(gs, empty);
	}
	
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
					GameState currentState;
					
					synchronized (state) {
						currentState = state;
					}
					
					for(EngineSystem s : stateSystems.get(currentState)) {
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
	public void setSystems(GameState state, List<EngineSystem> systems) {
		if(systems == null) throw new NullPointerException("Systems list cannot be null");
		this.stateSystems.put(state, systems);
	}

	@Override
	public GameState getState() {
		synchronized (state) {
			return state;
		}
	}

	@Override
	public void setState(GameState state) {
		synchronized (state) {
			this.state = state;
		}
		Directory.setEntityService(stateWorlds.get(state));
	}

	@Override
	public void setWorld(GameState state, EntityService world) {
		this.stateWorlds.put(state, world);
	}

}
