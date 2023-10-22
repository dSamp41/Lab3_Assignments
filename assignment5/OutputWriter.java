import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class OutputWriter implements Runnable{
    String outputName = "output_log.txt";
    ConcurrentHashMap<Character, Integer> c;

    public OutputWriter(ConcurrentHashMap<Character, Integer> m){
        this.c = m;
    }

    public void run(){
        try(DataOutputStream outputStream = new DataOutputStream(new FileOutputStream(outputName))){
            for(Map.Entry<Character, Integer> entry: c.entrySet()){
                String line = entry.getKey() + ", " + Integer.toString(entry.getValue()) + "\n";
                outputStream.writeChars(line);
            }
            System.out.println("Output file created successfully!");
        }
        catch(Exception e){
            System.err.println(e.getMessage());
        }

    }
}