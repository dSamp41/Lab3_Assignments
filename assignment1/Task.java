package Assignments.Lab3_Assignments.assignment1;

public class Task implements Runnable {
    private final int MAX = 10000000;
    
    private boolean isPrime(int n){
        boolean flag = false;
        for (int i = 2; i <= n / 2; ++i) {
            // condition for nonprime number
            if (n % i == 0) {
                flag = true;
                break;
            }
        }

        return !flag;
    }

    public void run(){
        int primes = 0;
        for(int i=0; i<MAX; i++){
            if(isPrime(i)){ 
                primes++;
            }
        }

        System.out.printf("Num of primes: %d\n", primes);

    }
    
}
