import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Class to Randomly Generate a Transaction for a random account within the bank
 * @author Brian Moyles - 21333461 
 */
public class RandomTransactionGenerator implements Runnable{
    // Variables 
    private Bank bank;
    private final int UPPER_BOUND = 10000;
    private final int LOWER_BOUND = -10000;
    private List<Integer> accounts;
    int accountNum;
    final Transaction POISON_PILL = new Transaction(-999, -999);

    /**
     * Constructor
     * Initializes the bank and accounts list  
     * accounts list takes all the account numbers from the method in the bank class
     * @param bank Class is created with a bank instance 
     */
    public RandomTransactionGenerator(Bank bank) {
        this.bank = bank;
        this.accounts = new ArrayList<>(bank.getAllAccountNumbers());
    }

    /**
     * Method to run the class
     * While the thread is alive it will run the 'CreateAndAddTransaction'
     * Poison pill will be inserted into the Processing queue when the thread ends
     * Posion pill allows for Processing termination 
     */
    @Override
    public void run() {
        try {
            while (Thread.currentThread().isAlive()) {
                CreateAndAddTransaction();
                // Random Thread sleep (Between 0 and 1) between each transaction
                Thread.sleep((long) (Math.random() * 1000));
            }
        } catch (Exception e) {
        } finally {
            bank.SubmitTransactionForProcessing(POISON_PILL); // Insert the poison pill to the queue 
        }
    }

    /**
     * Method to Create and add a transaction to the Processing queue 
     * Uses the random account and random value methods to create a new transaction to be processed 
     */
    public void CreateAndAddTransaction() {
        Transaction t = new Transaction(RandomlySelectAccount(), CreateRandomTransactionAmount());            
        bank.SubmitTransactionForProcessing(t);  // add the new transaction to the processing queue
    }

    /**
     * Method to randomly select an account 
     * @return Random account number is returned to be used as the transaction account
     */
    public Integer RandomlySelectAccount() {
        Random r = new Random();
        int accountNum = accounts.get(r.nextInt(accounts.size()));
        return accountNum;
    }

    /**
     * Method to generate a random value between -10,000 and 10,000
     * this value will be used a s either a deposit or withdrawal based on its value 
     * @return value is returned to be used as the transaction value
     */
    public long CreateRandomTransactionAmount() {
        Random r = new Random();
        long amount = r.nextLong(UPPER_BOUND - LOWER_BOUND) + LOWER_BOUND;
        return amount;
    }  
}