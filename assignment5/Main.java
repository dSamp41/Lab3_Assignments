import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main{
    public static void main(String[] args){
        if(args.length <= 0){
            System.err.println("At least one path is needed");
            return ;
        }
        
        ExecutorService pool = Executors.newCachedThreadPool();
        ConcurrentHashMap<Character, Integer> c = new ConcurrentHashMap<>();
        
        
        //guarantees that all letters are in hasmap => no check => no computeIfAbsent
        char[] alphabet = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z','A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'}; 
        for(int i=0; i<alphabet.length; i++){
            c.put(alphabet[i], 0);
        }


        for(String path: args){
            File f = new File(path);
            pool.submit(new FileCounter(f, c));
        }

        pool.shutdown();

        while(!pool.isTerminated()){
            //System.out.println("Waiting for termination");
        }
        writeMapToFile(c);
    }

    private static void writeMapToFile(ConcurrentHashMap<Character, Integer> c){
        final String outputName = "output_log.txt";

        try(FileWriter outputWriter = new FileWriter(outputName)){
            for(Map.Entry<Character, Integer> entry: c.entrySet()){
                String line = entry.getKey() + ", " + Integer.toString(entry.getValue()) + "\n";
                outputWriter.write(line);
            }
            
            System.out.println("Output file created successfully!");

        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }   
}