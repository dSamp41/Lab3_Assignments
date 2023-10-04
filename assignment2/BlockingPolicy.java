/*
Custom Rejection Policy
    if the workQueue of the pool is full, wait until there is space thene submit the task
*/

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

public class BlockingPolicy implements RejectedExecutionHandler {
    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor){
        try {
            // Block until space becomes available in the work queue
            executor.getQueue().put(r);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

