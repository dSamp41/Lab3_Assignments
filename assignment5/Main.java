import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main{
    public static void main(String[] args){
        if(args.length <= 0){
            System.err.println("At least one path is needed");
            return ;
        }

        ExecutorService pool = Executors.newCachedThreadPool();
        
        for(String path: args){
            File f = new File(path);
            pool.submit(new FileCounter(f));
        }

        pool.shutdown();
    }
}