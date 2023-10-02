import java.util.concurrent.BlockingQueue;

public class Producer implements Runnable{
    BlockingQueue<Runnable> blockingQueue;

    public Producer(BlockingQueue<Runnable> queue){
        this.blockingQueue = queue;
    }

    public void run(){
        //Filling salaAttesa (NON flusso continuo)
        for(int i=0; i<100; i++){
            try{
                Cliente c = new Cliente(i);
                blockingQueue.add(c);
                System.out.printf("Add Cliente %d\n", i);
            }
            catch(Exception e){
                System.out.println(e.getMessage());
            }
        }

    }
}