import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Zipper{
    public static void main(String[] args){
        if(args.length <= 0){
            System.err.println("At least one directory is needed");
            return ;
        }

        ExecutorService pool = Executors.newCachedThreadPool();
        ConcurrentLinkedQueue<String> dirList = new ConcurrentLinkedQueue<>();
        for(String d: args){
            dirList.add(d);
        }

        for(String dirName: dirList){
            File dir = new File(dirName);

            if(dir.exists() && dir.isDirectory()){
                String[] fileList = dir.list();

                //for every file, wrap in a task and submit to pool
                for(String f: fileList){
                    String fullName = dir + "/" + f;
                    File file = new File(fullName);
                    System.out.printf("File: %s \t\t Dir: %c\n", fullName, file.isDirectory()?'T':'F');

                    //handle file/directory
                    if(file.isDirectory()){
                        System.out.printf("Adding %s to dirList\n", f);
                        dirList.add(f);
                    }
                    else{
                        pool.execute(new ZipTask(file));
                    }
                    
                }
            }
        }

        pool.shutdown();
    }
}