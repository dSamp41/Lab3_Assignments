import java.util.concurrent.PriorityBlockingQueue;

public class Lab {
    public static void main(String[] args){
        PriorityBlockingQueue<User> prQueue = new PriorityBlockingQueue<>(10, new UserComparator());
        
        prQueue.add(new Student());
        prQueue.add(new Professor());
        prQueue.add(new Thesist(5));
        prQueue.add(new Professor());

        System.out.println("Users in queue:");
        while (!prQueue.isEmpty()) {
            System.out.println(prQueue.poll());
        }
    
    }

    
}
