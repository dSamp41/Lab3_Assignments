import java.util.concurrent.ThreadLocalRandom;

public abstract class User implements Runnable{
    int priority;
    int id;
    int k;      //num volte accesso in lab

    String who;
    final int maxMillis = 1000;
    
    public void run(){
        long time = ThreadLocalRandom.current().nextLong(1, maxMillis);
        
        System.out.printf("%s %d entered in lab\n", who, id);
        try {
            Thread.sleep(time);
        } 
        catch (InterruptedException e) {
            System.err.println("Interrupted sleep");
        }
        finally{
            System.out.printf("%s %d left lab\n", who, id);
        }
    }

}