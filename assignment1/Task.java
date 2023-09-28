package Assignments.Lab3_Assignments.assignment1;

import java.util.ArrayList;

public class Task implements Runnable {
    private final int MAX = 1_000_000;


    private void getPrimes(){ // ~O(n^2)
        ArrayList<Integer> primes = new ArrayList<Integer>();
        primes.add(2);
        primes.add(3);
        
        for(int i=4; i<=MAX; i++){
            boolean isPrime = true;
            for (Integer p : primes){
                if(i % p == 0){
                    isPrime = false;
                    break;
                }
            }
            if (isPrime) {
                primes.add(i);
            }
        }
        
        System.out.printf("Num of primes: %d\n", primes.size());

    }

    public void run(){
        long startTime = System.nanoTime();
        getPrimes();
        long time = System.nanoTime() - startTime;

        long timeMilli = time / (1_000_000);
        System.out.println("Thread execution time: " + timeMilli + " ms");
    }
}
