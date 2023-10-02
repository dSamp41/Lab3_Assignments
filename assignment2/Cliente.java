import java.util.concurrent.RejectedExecutionException;

public class Cliente implements Runnable {
    int n;

    public Cliente(int n){
        this.n = n;
    }
    public void run(){
        try{
            System.out.printf("Handling mail n. %d\n", n);
            
        }
        catch(RejectedExecutionException e){
            System.out.println(e.getMessage());
        }
    }
}