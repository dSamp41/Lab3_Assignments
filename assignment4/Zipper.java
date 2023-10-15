import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Zipper{
    public static void main(String[] args){
        if(args.length <= 0){
            System.err.println("At least one directory is needed");
            return ;
        }

        ExecutorService pool = Executors.newCachedThreadPool();        

        for(String dirName: args){
            File dir = new File(dirName);

            if(dir.exists() && dir.isDirectory()){
                String[] dirList = dir.list();

                //for every file, wrap in a task and submit to pool
                for(String f: dirList){
                    String fullName = dir + "/" + f;
                    File file = new File(fullName);
                    System.out.printf("File: %s \n", fullName);

                    if(!file.isDirectory()){
                        pool.execute(new ZipTask(file));
                    }
                    
                }
            }
        }

        pool.shutdown();
    }
}