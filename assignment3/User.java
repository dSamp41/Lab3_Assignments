import java.util.Random;

public abstract class User implements Runnable{
    int priority;
    
    public void run(){
        Random random = new Random();
        long time = random.nextInt(1000);
        
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}