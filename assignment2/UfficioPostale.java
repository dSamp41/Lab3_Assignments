import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class UfficioPostale {
    public static void main(String[] args){
        System.out.println("Size of inner room: ");
        int k = Integer.parseInt(System.console().readLine());

        BlockingQueue<Runnable> salaAttesa = new LinkedBlockingQueue<>();
        BlockingQueue<Runnable> salaSportelli = new ArrayBlockingQueue<>(k);
        ThreadPoolExecutor sportelli = new ThreadPoolExecutor(4, 4, 10, TimeUnit.MILLISECONDS, salaSportelli, new BlockingPolicy());
        
        Producer producer = new Producer(salaAttesa);
        Consumer consumer = new Consumer(salaAttesa, sportelli);
        
        Thread producerThread = new Thread(producer);
        Thread consumerThread = new Thread(consumer);
        
        producerThread.start();
        consumerThread.start();
    }
}
