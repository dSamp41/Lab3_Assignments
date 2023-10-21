import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class FileCounter implements Runnable{
    File file;

    public FileCounter(File f){
        this.file = f;
    }

    public void run(){
        try(FileInputStream fis = new FileInputStream(file))
        {  
            String fileText = readFile(file).replaceAll("[0-9]", "");
            System.out.println(fileText);
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