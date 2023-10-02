import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;

public class Consumer implements Runnable{
    BlockingQueue<Runnable> blockingQueue;
    ThreadPoolExecutor service;

    public Consumer(BlockingQueue<Runnable> queue, ThreadPoolExecutor pool){
        this.blockingQueue = queue;
        this.service = pool;
    }

    public void run(){
        while(true){
            try{
                service.execute(blockingQueue.take());
            }
            catch(Exception e){
                System.out.println(e.getMessage());
            }
        }
    }
}