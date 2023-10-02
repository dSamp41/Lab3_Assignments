import java.util.concurrent.BlockingQueue;

public class Producer implements Runnable{
    BlockingQueue<Runnable> blockingQueue;

    public Producer(BlockingQueue<Runnable> queue){
        this.blockingQueue = queue;
    }

    public void run(){
        //Filling salaAttesa (NON flusso continuo)
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