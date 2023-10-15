import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.GZIPOutputStream;

public class ZipTask implements Runnable {
    File inpFile;
    
    public ZipTask(File f){
        this.inpFile = f;
    }

    public void run(){
        System.out.printf("Zipping file %s\n", inpFile.getName());
        try(
            FileInputStream fis = new FileInputStream(inpFile);
            GZIPOutputStream gos = new GZIPOutputStream(
                new FileOutputStream(this.inpFile.getPath() + ".gz"))
            )
        {  
            int len = (int) this.inpFile.length();
            byte[] buffer = new byte[len];
            
            fis.read(buffer);
            gos.write(buffer, 0, len);
            
            //this.inpFile.delete();
        }
        catch(IOException e){
            e.printStackTrace();
        }

    }

}
