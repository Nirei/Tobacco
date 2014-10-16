package tobacco.core.systems;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import tobacco.core.components.Entity;

public class ThreadedMainSystem extends AbstractMainSystem {

	private Executor executor = new ThreadPoolExecutor(4, 8, 6, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(32));
	private List<EngineSystem> systemList = new ArrayList<EngineSystem>();

	public ThreadedMainSystem() {
		super();
	}
	
	@Override
	public void work(final Entity entity) {
		for (EngineSystem s : systemList) {
			executor.execute(() -> new Runnable() {
				@Override
				public void run() {
					System.out.println(Thread.currentThread() + " is doing its job...");
					s.work(entity);		
				}
			});
		}
	}
}
