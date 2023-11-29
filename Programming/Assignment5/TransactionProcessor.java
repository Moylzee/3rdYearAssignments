import org.joda.money.Money;

/**
 * Class that processes the transactions for the bank
 * @author Brian Moyles - 21333461  
 */
public class TransactionProcessor implements Runnable {
    // Variables 
    private String name;
    private Bank bank;
    private int numDeposits;
    private int numWithdrawals; 
    private long lastTransactionTime;

    /**
     * Constructor 
     * Initialize number of deposits and withdrawals to 0 each run
     * @param name Name of the Transaction Processor 
     * @param bank Created with bank instance 
     */
    public TransactionProcessor(String name, Bank bank){
        this.name = name;
        this.bank = bank;
        numDeposits = 0;
        numWithdrawals = 0;
        lastTransactionTime = System.currentTimeMillis();
    }

    /**
     * Run method to be run when the threads are executed
     * Gets the next transaction from the queue
     * Finishes executing if it has been waiting for 5 seconds or more for another transaction  
     * Checks if the next transaction is the poison pill, which will terminate execution
     * Processes the next transaction if all conditions are met:
     *  - (Not null, not waiting over 5 seconds, not Poison pill)
     * Resets last transaction time to accurately time how long it waits for next transaction
     * Random thread sleep between 0-1 seconds 
     * Re-initializes t as the next transaction in queue after transaction is processed
     */
    @Override
    public void run() {
        Transaction t = bank.getNextTransactionFromQueue();
        while ((System.currentTimeMillis() - lastTransactionTime) < 5000) {
            if (t != null) { // Checks if the transaction is null
                if (t.getAccountNumber() == -999 && t.getAmount() == -999) {
                    break; // Terminate the processing loop
                }
                ProcessTransaction(t);
                lastTransactionTime = System.currentTimeMillis();
                try {
                    // Random Thread sleep (Between 0 and 1) between each transaction
                    Thread.sleep((long) (Math.random() * 1000));
                } catch (Exception e) {}
            } 
            t = bank.getNextTransactionFromQueue();
        }
    }
    
    /**
     * Method to process a transaction 
     * Gets the account number and amount to process from the Transaction  
     * @param t Transaction parameter passed in to process
     */
    public void ProcessTransaction(Transaction t) {
        float amountToProcess = t.getAmount();
        // Use the account number to ensure transaction occurs on the correct class  
        Account a = bank.getAccountByNumber(t.getAccountNumber()); 
        try {
            if(a != null) { // Check that the account exists 
                if (amountToProcess >= 0) { // Deposit 
                    a.makeDeposit(Money.parse("EUR " +amountToProcess)); // Call deposit function from account with the amount 
                    numDeposits++; // Increment the number of deposits made
                    System.out.println(name+ " is processing a deposit of EUR " +amountToProcess+ " to Account [" +a.getAccountNumber()+ "]");
                } else { // Withdrawal
                    a.makeWithdrawal(Money.parse("EUR " +amountToProcess)); // Call withdrawal function from account with the amount 
                    numWithdrawals++; // Increment number of withdrawals made  
                    System.out.println(name+ " is processing a withdrawal of EUR " +amountToProcess+ " to Account [" +a.getAccountNumber()+ "]");
                }
            } else { // Print invalid account number if account is null 
                System.out.println("Invalid Account Number");
            }
        } catch (Exception e) { // Handle any other errors
            System.err.println("Error Processing Transaction: " +e.getMessage());
        }
    }

    /**
     * Method to print the review at the end of the Processing 
     * Implementing this function is optional but in my opinion provides a neat output
     * Allows for viewing of all the review statements in one place 
     */
    public void PrintReview() {
        System.out.println("\n" +name);
        System.out.println("Number of Completed Transactions: " +(numDeposits + numWithdrawals));
        System.out.println("Number of Deposits: " +numDeposits);
        System.out.println("Number of Withdrawals: " +numWithdrawals);
    }
}