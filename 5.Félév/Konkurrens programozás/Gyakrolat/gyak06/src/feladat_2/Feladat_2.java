package feladat_2;

import java.util.Arrays;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class Feladat_2 {
    static final int NUM_OF_CLIENTS = 10;
    
    public static void main(String[] args){
        ExecutorService exec = Executors.newFixedThreadPool(NUM_OF_CLIENTS);
        Bank bank = new Bank();
        Future loanCounter[] = new Future [NUM_OF_CLIENTS];
        
        for (int i = 0; i < NUM_OF_CLIENTS; i++) {
            loanCounter[i] = exec.submit(new Callable<Integer>(){
                public int allLoans;
                
                @Override
                public Integer call(){
                    allLoans = 0;
                    for (int j = 0; j < 10_000; j++) {
                        int loan = ThreadLocalRandom.current().nextInt(1,10_000);
                        allLoans += loan;
                        bank.addToLoan(loan);
                    }
                    return allLoans;
                }
            });
        }
        exec.shutdown();
        try{
            exec.awaitTermination(1, TimeUnit.DAYS);
        }catch(InterruptedException e){
            
        }
        int sum = 0;
        for (int i = 0; i < NUM_OF_CLIENTS; i++) {
            try{
                sum += (int)loanCounter[i].get();
            }
            catch(InterruptedException | ExecutionException e){
            
            }
        }
        System.out.println(bank.getTotalLoans());
        System.out.println(sum);
        System.out.println(Arrays.toString(loanCounter));
        
    }
}

class Bank
{
    private int totalLoans;
    
    Bank(){
        totalLoans = 0;
    }
    
    int getTotalLoans(){
        return totalLoans;
    }
    
    synchronized void addToLoan(int value){
        totalLoans += value;
    }
}
