import java.io.File;
import java.io.FileNotFoundException;
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
        String fileText = readFile(file).replaceAll("[0-9]", "");

        for(int i=0; i<fileText.length(); i++){
            char c = fileText.charAt(i);
            this.map.computeIfPresent(c, (k, v) -> v+1);
        }
    }

    private String readFile(File file) {
        StringBuilder fileContents = new StringBuilder((int)file.length());        

        try (Scanner scanner = new Scanner(file)) {
            while(scanner.hasNextLine()) {
                fileContents.append(scanner.nextLine() + System.lineSeparator());
            }
            return fileContents.toString();
        }
        catch(FileNotFoundException e){
            System.out.println("Il file non è stato trovato");
            return "";
        }
    }


}