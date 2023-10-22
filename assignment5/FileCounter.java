import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;

public class FileCounter implements Runnable{
    File file;
    ConcurrentHashMap<Character, Integer> map;

    public FileCounter(File f, ConcurrentHashMap<Character, Integer> hashmap){
        this.file = f;
        this.map = hashmap;
    }

    public void run(){
        try(FileInputStream fis = new FileInputStream(file))
        {  
            String fileText = readFile(file).replaceAll("[0-9]", "");
            //System.out.println(fileText);

            for(int i=0; i<fileText.length(); i++){
                char c = fileText.charAt(i);
                //this.map.computeIfPresent(c, (k, v) -> v+1);
                this.map.compute(c, (k, v) -> {
                    if(k != null) return v+1;
                    else return 0;
                    }
                );
            }

            System.out.println(this.map);
        }
        catch(IOException e){
            e.printStackTrace();
        }


    }

    private String readFile(File file) throws IOException {

        StringBuilder fileContents = new StringBuilder((int)file.length());        

        try (Scanner scanner = new Scanner(file)) {
            while(scanner.hasNextLine()) {
                fileContents.append(scanner.nextLine() + System.lineSeparator());
            }
            return fileContents.toString();
        }
    }


}