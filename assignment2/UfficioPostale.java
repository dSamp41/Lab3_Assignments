import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class UfficioPostale {
    private class Cliente implements Runnable {
        public void run(){
            Random rand = new Random();
            int sleepTime = rand.nextInt(5000); //sleep max 5 sec

            try{
                System.out.println("Handling mail");
                Thread.sleep(sleepTime);
            }
            catch(Exception e){
                System.out.println(e.getMessage());
            }
        }
    }

    private class Filler() implements Runnable{
        BlockingQueue<Runnable> blockingQueue;

        public Filler(BlockingQueue<Runnable> queue){
            this.blockingQueue = queue;
        }

        public void run(){
            UfficioPostale up = new UfficioPostale();

            //Filling salaAttesa (NON flusso continuo)
            for(int i=0; i<1000; i++){
                try{
                    blockingQueue.add(up.new Cliente());
                    System.out.printf("Add Cliente %d\n", i);
                }
                catch(Exception e){
                    System.out.println(e.getMessage());
                }
            }

        }
    }
    

    public static void main(String[] args){
        System.out.println("Size of inner room: ");
        int k = Integer.parseInt(System.console().readLine());
        
        LinkedBlockingQueue<Cliente> salaAttesa = new LinkedBlockingQueue<Cliente>();
        ArrayBlockingQueue<Runnable> salaSportelli = new ArrayBlockingQueue<Runnable>(k);
        ThreadPoolExecutor sportelli = new ThreadPoolExecutor(4, 4, 60, TimeUnit.SECONDS, salaSportelli);

        UfficioPostale up = new UfficioPostale();

        
        //Filling salaAttesa (NON flusso continuo)
        for(int i=0; i<1000; i++){
            try{
                salaAttesa.add(up.new Cliente());
                System.out.printf("Add Cliente %d\n", i);
            }
            catch(Exception e){
                System.out.println(e.getMessage());
            }
        }

        try{
            salaSportelli.put(salaSportelli.take());
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }

        sportelli.


        sportelli.shutdown();
    }


}