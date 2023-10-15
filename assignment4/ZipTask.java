import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.GZIPOutputStream;

public class ZipTask implements Runnable {
    String inpName;
    String outName; 
    
    public ZipTask(String fName){
        this.inpName = fName;
        this.outName = fName.split(".")[0].concat(".gz");
    }

    public void run(){
        //System.out.printf("File %s viewed\n", name);
        try(
            FileInputStream fis = new FileInputStream(inpName);
            GZIPOutputStream gos = new GZIPOutputStream(new FileOutputStream(outName)))
        {
            byte[] buffer = new byte[1024];
        }
        catch(IOException e){
            e.printStackTrace();
        }

    }
    
}
