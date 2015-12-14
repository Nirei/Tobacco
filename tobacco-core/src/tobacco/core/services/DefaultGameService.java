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

import tobacco.core.systems.main.AbstractMainSystem;

public class DefaultGameService implements GameService {

	private AbstractMainSystem main = null;
	private Thread gameThread = null;

	@Override
	public AbstractMainSystem getMainSystem() {
		return main;
	}

	@Override
	public void setMainSystem(AbstractMainSystem main) {
		this.main = main;
	}

	@Override
	public synchronized void start() {
		gameThread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				boolean run = true;
				while (run) {
					getMainSystem().work();
					try {
						Thread.sleep(5);
					} catch (InterruptedException e) {
						run = false;
					}
				}
			}
		});
		
		gameThread.start();
	}

	@Override
	public void stop() {
		gameThread.interrupt();
	}
}
