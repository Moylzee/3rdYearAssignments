import java.io.Serializable;
import java.time.LocalDate;

/**
 * This class represents the achievements
 * it implements the Serializable interface for object serialization
 * Each achievement has a name, description and date of award
 * @author Brian Moyles - 21333461
 */
public class Achievement implements Serializable{
    /**
     * Variables for name, Description and date of award
     */
    String achievementName;
    String desc;
    LocalDate dateOfAward;
    
    /**
     * Constructor for achievement object
     * @param achievementName   Achievement Name 
     * @param desc              Achievement Description 
     * @param dateOfAward       Date of Achievement award
     */
    public Achievement(String achievementName, String desc, LocalDate dateOfAward){
        this.achievementName = achievementName;
        this.desc = desc;
        this.dateOfAward = dateOfAward;
    }

    /**
     * Method to return a string of the achievement details
     * @return achievement data in CSV format
     */
    public String toCSV() {
        return achievementName + ", " + desc + ", " + dateOfAward.toString();
    }
}