package tobacco.core.systems.main;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class CustomExecutor extends ThreadPoolExecutor {

	// private static final Logger LOGGER = Logger.getLogger(CustomExecutor.class.getName());
	
	CustomExecutor() {
		super(8,8,6,TimeUnit.SECONDS,new ArrayBlockingQueue<Runnable>(64));
	}
}
