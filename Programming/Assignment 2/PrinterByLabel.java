/**
 * Implements the Printer Interface
 * Class to print the expenses in order of category 
 * @author Brian Moyles 21333461
 */
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class PrinterByLabel implements ExpensePrinter{

    /**
     * Prints expenses in order of category using an EnumMap 
     * Stringbuilder is made for each category of expense
     * append each expense to the correct category
     * Print in order of category 
     * @param expenses List of expenses 
     */
    @Override
    public void Print(List<Expense> expenses) {
        Map<ExpType, StringBuilder> e = new EnumMap<>(ExpType.class);

        for (ExpType label : ExpType.values()) {
            e.put(label, new StringBuilder(label.toString() + "\n"));
        }

        for (Expense expense : expenses) {
            ExpType label = expense.getExpType();
            e.get(label).append(expense.toString()).append("\n");
        }
        for (StringBuilder expenseText : e.values()) {
            System.out.println("-"+expenseText);
        }
    }
    
}
