package Assignments.Lab3_Assignments.assignment1;
/*
Scrivere una applicazione JAVA che:
    - crea e attiva n thread
    - ogni thread esegue esattamente lo stesso task, ovvero conta il numero di interi minori di 10,000,000 che sono primi
    - il numero di thread che devono essere attivati e mandati in esecuzione viene richiesto all’utente, che lo inserisce tramite la CLI (Command Line Interface)

Analizzare come varia il tempo di esecuzione dei thread attivati a seconda del loro numero
Sviluppare quindi un programma in cui si creano n task, tutti eseguono la computazione descritta in precedenza e vengono sottomessi a un threadpool la cui dimensione deve essere inserita da CLI.
*/

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args){
        System.out.println("Dimensione pool: ");
        int size = Integer.parseInt(System.console().readLine());

        System.out.println("Numero di task: ");
        int numTasks = Integer.parseInt(System.console().readLine());


        ExecutorService taskExecutor = Executors.newFixedThreadPool(size);
        for(int i=0; i<numTasks; i++){
            taskExecutor.execute(new Task());
        }
        taskExecutor.shutdown();
    }
}
