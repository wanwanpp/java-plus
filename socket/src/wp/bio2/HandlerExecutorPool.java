package wp.bio2;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class HandlerExecutorPool {

	private ExecutorService executor;
	public HandlerExecutorPool(int maxPoolSize, int queueSize){
		this.executor = new ThreadPoolExecutor(
				Runtime.getRuntime().availableProcessors(),
				maxPoolSize, 
				120L, //当线程数量大于corepoolsize时，若线程空闲时间大于keepAliveTime，则会被回收。
				TimeUnit.SECONDS,
				new ArrayBlockingQueue<Runnable>(queueSize));
	}
	
	public void execute(Runnable task){
		this.executor.execute(task);
	}
	
	
	
}
