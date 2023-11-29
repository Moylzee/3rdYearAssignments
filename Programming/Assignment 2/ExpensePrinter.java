/**
 * Printer Interface to print list of expenses  
 * @author Brian Moyles 21333461
 */
import java.util.List;


public interface ExpensePrinter {
    /**
     * Print list of expenses according to how it is implemented
     * @param expenses Variable for list of expenses 
     */
    void Print(List<Expense> expenses);
}
