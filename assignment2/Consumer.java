import java.util.concurrent.BlockingQueue;

public class Consumer implements Runnable{
    BlockingQueue<Runnable> blockingQueue;

    public Consumer(BlockingQueue<Runnable> queue){
        this.blockingQueue = queue;
    }

    public void run(){
        for(int i=0; i<1000; i++){
            try{
                blockingQueue.add(new Cliente());
                System.out.printf("Add Cliente %d\n", i);
            }
            catch(Exception e){
                System.out.println(e.getMessage());
            }
        }

    }
}