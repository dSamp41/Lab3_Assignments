import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class WeblogMT {
    

    public static void main(String[] args){
        int numCores = Runtime.getRuntime().availableProcessors(); // Get the number of available cores
        ExecutorService pool = Executors.newFixedThreadPool(numCores);

        if(args.length < 1){
            System.err.println("Inserire nome del file");
            return;
        }

        File log = new File(args[0]);
        
        try(BufferedReader reader = new BufferedReader(new FileReader(log))){
            String line = reader.readLine();

            while(line != null){
                Task task = new Task(line);
                pool.execute(task);

                line = reader.readLine();
            }

        }
        catch(IOException e){
            e.printStackTrace();
        }
        finally{
            pool.shutdown();
        }
    }
} 