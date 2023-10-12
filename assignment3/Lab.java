import java.util.concurrent.PriorityBlockingQueue;

public class Lab {
    public static void main(String[] args){
        int prof = Integer.parseInt(args[0]);
        int thes = Integer.parseInt(args[1]);
        int stud = Integer.parseInt(args[2]);
        
        PriorityBlockingQueue<User> prQueue = new PriorityBlockingQueue<>(10, new UserComparator());
        
        prQueue.add(new Student(1));
        prQueue.add(new Professor(1));
        prQueue.add(new Thesist(1, 5));
        prQueue.add(new Professor(1));

        System.out.println("Users in queue:");
        while (!prQueue.isEmpty()) {
            System.out.println(prQueue.poll());
        }
    
    }

    
}
