import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import org.joda.money.Money;

/**
 * Bank class
 * Initializes a Bank
 * Allows for adding accounts and transactions to the Bank
 * Allows for viewing the data added to the bank
 * @author Brian Moyles - 21333461
 */
public class Bank {
    // Variables
    String name;
    private Map<Integer, Account> bankAccounts;
    private LinkedBlockingQueue<Transaction> transactions;

    /**
     * Constructor
     * @param name name of bank 
     */
    public Bank(String name){
        this.name = name;
        this.bankAccounts = new HashMap<>();
        this.transactions = new LinkedBlockingQueue<>();
    }

    /**
     * Method to add the account to the bank
     * Account is put into the bankAccounts map
     * @param a Account to be added to the bank 
     */
    public void addAccountToBank(Account a) {
        bankAccounts.put(a.getAccountNumber(), a);
    }

    /**
     * Method to get the details of the bank account requested 
     * @param a account to get the details of  
     * @return  returns the toString in the accounts class that displays the details
     */
    public Account getAccountByNumber(int a) {
        for (Map.Entry<Integer, Account> b : bankAccounts.entrySet()) {
            if (a == b.getKey()){
                return b.getValue();
            }
        }
        return null;
    }
        
    /**
     * Method to submit a transaction to the Linked Blocking Queue
     * Try_Catch is used to check for any errors
     * @param t The transaction to submit
     */
    public void SubmitTransactionForProcessing(Transaction t) {
        try {
            transactions.put(t);
        } catch (Exception e) {
        }
    }

    /**
     * Method to get the next transaction in the queue
     * Uses the peek and remove function to retrieve the next element in the linked blocking Queue 
     * @return  Returns next transaction to process if there is one. null if none
     */
    public synchronized Transaction getNextTransactionFromQueue() {
        try {
            if (transactions.peek() != null) {
                return transactions.remove();
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Method to get Details of all accounts in the bank
     * Iterates through the bank map that contains the account details
     * Prints the details of each account by printing the account number and balance  
     */
    public void PrintDetailsOfAccounts() {
        for (Map.Entry<Integer, Account> b : bankAccounts.entrySet()) {
            System.out.println(b.getValue());
        }
    }

    /**
     * Method to get all the account numbers in the bank
     * Iterates through the bank map and returns the account numbers
     */
    public Set<Integer> getAllAccountNumbers() {
        return bankAccounts.keySet();
    }

    /**
     * Main method that Initializes objects and runs classes 
     * @param args
     * @throws NegativeBalanceException Exception to throw 
     */
    public static void main(String[] args) throws NegativeBalanceException {
        // Instantiate Bank Object
        Bank bank = new Bank("NotPermanent XYZ");

        // Create Accounts and add them to the bank
        Account account1 = new Account(1, Money.parse("EUR 450300"));
        Account account2 = new Account(404, Money.parse("EUR 678280"));
        Account account3 = new Account(350, Money.parse("EUR 1234567890"));
        bank.addAccountToBank(account1);
        bank.addAccountToBank(account2);
        bank.addAccountToBank(account3);

        // Create Transactions and Add them for processing
        Transaction transaction1 = new Transaction(account1.getAccountNumber(), 100);
        Transaction transaction2 = new Transaction(account2.getAccountNumber(), -100);
        bank.SubmitTransactionForProcessing(transaction1);
        bank.SubmitTransactionForProcessing(transaction2);

        // Initialize Transaction Processor and Random Transaction Generator Processor  
        TransactionProcessor processor = new TransactionProcessor("TSP1", bank);
        TransactionProcessor processor2 = new TransactionProcessor("TSP2", bank);
        RandomTransactionGenerator r = new RandomTransactionGenerator(bank);

        // Execution Service threads (2 for processing & 1 for Random Generation)
        ExecutorService transactionThreads = Executors.newFixedThreadPool(2);    
        ExecutorService randomGeneratorThreads = Executors.newFixedThreadPool(1);  
        
        // Execute the threads 
        transactionThreads.execute(processor);
        transactionThreads.execute(processor2);
        randomGeneratorThreads.execute(r);

        try {
            // Wait for 10 seconds and then shutdown the Random Generator Thread 
            if (!randomGeneratorThreads.awaitTermination(10, TimeUnit.SECONDS)) {
                randomGeneratorThreads.shutdownNow(); 
            }
        } catch (InterruptedException e) {
            // Handle interruption if needed
            e.printStackTrace();
        } finally {
            // Shutdown the executor service
            System.out.println("Terminating Transaction Processor......");
            randomGeneratorThreads.shutdownNow();
            transactionThreads.shutdownNow();
        }
        // Print the final details about the Processor Transactions and the accounts after 
        processor.PrintReview();
        processor2.PrintReview();
        System.out.println("\n");
        bank.PrintDetailsOfAccounts();
    }
}