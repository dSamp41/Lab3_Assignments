import java.util.Random;

public class Cliente implements Runnable {
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