import java.util.concurrent.RejectedExecutionException;

public class Cliente implements Runnable {
    int n;

    public Cliente(int n){
        this.n = n;
    }
    public void run(){
        try{
            System.out.printf("Handling client %d\n", this.n);
        }
        catch(RejectedExecutionException e){
            System.out.printf("\tTask Cliente %d rejected", n);
            //System.out.println(e.getMessage());
        }
    }
}