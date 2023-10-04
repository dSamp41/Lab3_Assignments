import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

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
                Runnable extractedTask = blockingQueue.poll(1, TimeUnit.SECONDS);
                
                if(extractedTask == null){
                    System.out.println("No more Clients");
                    service.shutdown();
                    break;
                }

                service.execute(extractedTask);

            }
            catch(Exception e){
                System.out.println(e.getMessage());
            }
        }
    }
}