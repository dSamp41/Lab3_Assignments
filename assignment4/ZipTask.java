public class ZipTask implements Runnable {
    String name;
    
    public ZipTask(String fName){
        this.name = fName;
    }

    public void run(){
        System.out.printf("File %s viewed\n", name);

    }
    
}
