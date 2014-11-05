package tobacco.core.systems.main;

import tobacco.core.systems.EngineSystem;

public class ThreadedMainSystem extends AbstractMainSystem {
	
	private CustomExecutor executor = new CustomExecutor();
	
	@Override
	public void work() {
		for (EngineSystem s : systemList) {
			executor.execute(new Runnable() {
				
				@Override
				public void run() {
					s.work();		
				}
			});
		}
	}
}
