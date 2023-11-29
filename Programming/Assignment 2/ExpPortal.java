 /**
  * Class that manages the list of expenses 
  * Allows for classes to be added, printed and the toal calculated in Euro
  * @author Brian Moyles 21333461
  */
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;

public class ExpPortal {

    /**
     * List to store the expenses 
     */
    private List<Expense> expenses = new ArrayList<>();

    /**
     * Empty constructor 
     */
    public ExpPortal(){}

    /**
     * Method to add the expense to the expense list 
     * @param e the expense variable to be added 
     */
    public void AddExpense(Expense e) {
        expenses.add(e);
    }

    /**
     * Method to print the expenses uses the implemented interface
     * @param printer variable passed through whihc is used for printing
     */
    public void PrintExpenses(ExpensePrinter printer){
        printer.Print(expenses);
    }

    /**
     * Method to convert expense costs to euro & total the sum of expenses
     * @param expenses variable passed through that has a list of the expenses
     * @param exchangeRate variable used to store the exchange rate
     * @param total variable to store the total costs of expenses
     * @param moneyEUR variable used to convert USD to EUR 
     * @return sum of all the expenses in euro
     */
    public static Money SumExpenses(List<Expense> expenses) {
        BigDecimal exchangeRate = new BigDecimal(0.97);
        Money total = Money.zero(CurrencyUnit.EUR);

        for (Expense e : expenses) {
            if (e.getMoney().getCurrencyUnit() == CurrencyUnit.USD) {
                Money moneyEUR = e.getMoney().convertedTo(CurrencyUnit.EUR, exchangeRate, RoundingMode.HALF_UP);
                total = total.plus(moneyEUR);
            }
            else {
                total = total.plus(e.getMoney());
            }
        }
        return (total);
    }
}
