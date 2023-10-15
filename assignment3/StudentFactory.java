import java.util.concurrent.PriorityBlockingQueue;

public class StudentFactory implements Runnable{
    int num;
    PriorityBlockingQueue<User> queue;

    public StudentFactory(int num, PriorityBlockingQueue<User> queue){
        this.num = num;
        this.queue = queue;
    }

    public void run(){
        for(int i=1; i<=num; i++){
            queue.add(new Student(i));
        }
    }
}
