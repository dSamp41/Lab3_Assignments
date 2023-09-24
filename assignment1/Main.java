package Assignments.Lab3_Assignments.assignment1;

public class Main {
    public static void main(String[] args){
        int numThreads = Integer.parseInt(System.console().readLine());

        for(int i=0; i<numThreads; i++){
            Task task = new Task();
            Thread thread = new Thread(task);
            thread.start();
        }
    
    }
}
