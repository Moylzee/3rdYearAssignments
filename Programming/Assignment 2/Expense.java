/**
 * This Class is used to represent the expense, the date, description, type, amount.
 * @author Brian Moyles 21333461
 */

import java.time.LocalDate;
import org.joda.money.Money;

public class Expense {

    private LocalDate date = LocalDate.now();
    private String desc;
    private boolean approved = false;
    private ExpType expType;    
    private Money money;

    /**
     * Constructor for the expense object
     * @param date      date of expense 
     * @param desc      expense description 
     * @param approved  if the expense has been approved    
     * @param expType   the type of the expense
     * @param money     the cost of the expense 
     */
    public Expense(LocalDate date, String desc, Boolean approved, ExpType expType, Money money) {
        this.date = date;
        this.desc = desc;
        this.approved = approved;
        this.expType = expType;
        this.money = money;
    }

    /**
     * Method to approve the expense 
     */
    public void approveExp() {
        approved = true;
    }

    /**
     * Method to return the expense in a String output
     * @return  String containing date, description, Expense type and cost
     */
    @Override
    public String toString() {
        return(date+ ": " +desc+ " - " +expType+ " - " +money);
    }

    /**
     *  Getter Methods
     * @return Return the paramaters passed through
     */
    public LocalDate getDate() {return date;}
    public String getDesc() {return desc;}
    public boolean isApproved() {return approved;}
    public ExpType getExpType() {return expType;}
    public Money getMoney() {return money;}
    
    /**
     * Setter Methods 
     * @param date      date of expense 
     * @param desc      expense description 
     * @param approved  if the expense has been approved    
     * @param expType   the type of the expense
     * @param money     the cost of the expense 
     */
    public void setDate(LocalDate date) {this.date = date;}
    public void setDesc(String desc) {this.desc = desc;}
    public void setApproved(boolean approved) {this.approved = approved;}
    public void setExpType(ExpType expType) {this.expType = expType;}
    public void setMoney(Money money) {this.money = money;}
}
