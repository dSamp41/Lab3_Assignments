import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class UfficioPostale {
    public static void main(String[] args){
        //System.out.println("Size of inner room: ");
        //int k = Integer.parseInt(System.console().readLine());
        int k = 10;

        BlockingQueue<Runnable> salaAttesa = new LinkedBlockingQueue<>();
        BlockingQueue<Runnable> salaSportelli = new ArrayBlockingQueue<>(k);
        ThreadPoolExecutor sportelli = new ThreadPoolExecutor(4, 4, 60, TimeUnit.SECONDS, salaSportelli);

        
        Producer producer = new Producer(salaAttesa);
        Consumer consumer = new Consumer(salaAttesa, sportelli);
        
        Thread producerThread = new Thread(producer);
        Thread consumerThread = new Thread(consumer);
        
        producerThread.start();
        consumerThread.start();

        /*if(salaAttesa.isEmpty()){
            sportelli.shutdown();
        }*/
    }
}