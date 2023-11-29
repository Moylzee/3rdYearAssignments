/**
 * Class to demonstrate that the code functions as purposed 
 * Creates expenses, adds them to the portal & prints them
 * @author Brian Moyles 21333461
 */
import java.time.LocalDate;
import java.util.List;

import org.joda.money.Money;

public class Test {
    /**
     * Main method 
     * @param e1 expense variable 
     * @param e2 expense variable 
     * @param e3 expense variable
     * @param p Instance of the expense portal
     */
    public static void main(String[] args) {
        Expense e1 = new Expense(LocalDate.now(), "TV", false, ExpType.ENTERTAINMENT, Money.parse("USD 450"));
        Expense e2 = new Expense(LocalDate.now(), "Flight", false, ExpType.TRAVEL_AND_SUBSISTENCE, Money.parse("USD 60"));
        Expense e3 = new Expense(LocalDate.now(), "Pens", false, ExpType.SUPPLIES, Money.parse("EUR 50"));
        ExpPortal p = new ExpPortal();

       /**
        * add Expenses to the portal
        */
        p.AddExpense(e1);
        p.AddExpense(e2);
        p.AddExpense(e3);

        /**
         * Print the expenses to the console using a lambda Expression
         */
        p.PrintExpenses(expenses -> {
                for (Expense e : expenses) {
                    System.out.println(e.toString());
            }
        });
        
                PrinterByLabel pr = new PrinterByLabel();
                p.PrintExpenses(pr);

        /**
         * Print the amount of expenses and total cost using anonymous inner class
         */ 
        p.PrintExpenses(new ExpensePrinter() {
            @Override
            public void Print(List<Expense> expenses){
                Money m = ExpPortal.SumExpenses(expenses);
                System.out.println("There are " +(expenses.size()+1)+ " expenses totalling " +m);                    
            }
        });
    }
}
