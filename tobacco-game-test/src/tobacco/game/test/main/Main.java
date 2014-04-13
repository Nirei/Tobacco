package tobacco.game.test.main;

import tobacco.core.components.Entity;
import tobacco.core.loader.Loader;
import tobacco.core.systems.MainSystem;
import tobacco.game.test.loader.ManualLoader;

public class Main {

	public static void main(String[] args) {
		Loader loader = new ManualLoader();
		Entity root = loader.loadRootEntity();
		MainSystem mainSystem = loader.loadMainSystem(root);

		while(true) {
			mainSystem.work(root);
			try {
				Thread.sleep(5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
