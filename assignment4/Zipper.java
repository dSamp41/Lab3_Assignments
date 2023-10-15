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

                for(String f: dirList){
                    ZipTask task = new ZipTask(f);
                    pool.submit(task);
                }

            }
        }

        pool.shutdown();

    }
}