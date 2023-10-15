import java.util.concurrent.PriorityBlockingQueue;

public class Lab {
    public static void main(String[] args){
        if(args.length != 3){
            System.err.println("Invalid args");
            return;
        }
        
        int prof = Integer.parseInt(args[0]);
        int thes = Integer.parseInt(args[1]);
        int stud = Integer.parseInt(args[2]);
        
        PriorityBlockingQueue<User> prQueue = new PriorityBlockingQueue<>(10, new UserComparator());

        Thread profFactoryThread = new Thread(new ProfFactory(prof, prQueue));
        profFactoryThread.run();

        Thread studFactoryThread = new Thread(new StudentFactory(stud, prQueue));
        studFactoryThread.start();

        System.out.println("Users in queue:");
        while (!prQueue.isEmpty()) {
            System.out.println(prQueue.poll());
        }
    
    }
}
