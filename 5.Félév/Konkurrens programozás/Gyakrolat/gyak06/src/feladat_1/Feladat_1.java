package feladat_1;

import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class Feladat_1 {
    static final int NUM_OF_CLIENTS = 10;
    
    public static void main(String[] args){
        ExecutorService exec = Executors.newFixedThreadPool(NUM_OF_CLIENTS);
        Bank bank = new Bank();
        int loanCounter[] = new int [NUM_OF_CLIENTS];
        
        for (int i = 0; i < NUM_OF_CLIENTS; i++) {
            int num = i;
            exec.submit(new Runnable(){
                public int allLoans;
                
                @Override
                public void run(){
                    allLoans = 0;
                    for (int j = 0; j < 10_000; j++) {
                        int loan = ThreadLocalRandom.current().nextInt(1,10_000);
                        allLoans += loan;
                        bank.addToLoan(loan);
                    }
                    loanCounter[num] = allLoans;
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
            sum += loanCounter[i];
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
