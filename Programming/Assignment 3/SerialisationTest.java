import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 * Class that contains test method and main method 
 * @author Brian Moyles - 21333461
 */
public class SerialisationTest {

    /**
     * Method for serializing and deserializing Player objects
     * Test is used to check the serialization works properly
     */
    @Test
    public void UnitTestingMethod() {
        Player p1 = new Player("1", "Moylzee", Country.IRELAND, LocalDate.of(2002, 5, 29));
        p1.AddAchievement("Gold Medal", "Won a Gold Medal", LocalDate.of(2002, 7, 13));
        Player p2 = new Player("2", "User2", Country.MEXICO, LocalDate.of(2023, 4, 12));
        p2.AddAchievement("Bronze Medal", "Won a Bronze Medal", LocalDate.of(2023, 10, 4));
        Player p3 = new Player("3", "User3", Country.AUSTRALIA, LocalDate.of(2019, 1, 1));
        p3.AddAchievement("Silver Medal", "Won a Silver Medal", LocalDate.of(2020, 8, 30));
        Player p4 = new Player("4", "User4", Country.FRANCE, LocalDate.of(2005, 4, 27));
        p4.AddAchievement("Platinum Medal", "Won a Platinum Medal", LocalDate.of(2006, 12, 18));
        Player p5 = new Player("5", "User5", Country.PORTUGAL, LocalDate.of(2007, 9, 11));
        p5.AddAchievement("Diamond Medal", "Won a Diamond Medal", LocalDate.of(2008, 11, 4));
    
        // Serialize Player objects
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("players.ser"))) {
            p1.writeObject(outputStream);
            p2.writeObject(outputStream);
            p3.writeObject(outputStream);
            p4.writeObject(outputStream);
            p5.writeObject(outputStream);          
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        // Deserialize Player objects
        Player deserializedP1 = new Player();
        Player deserializedP2 = new Player();
        Player deserializedP3 = new Player();
        Player deserializedP4 = new Player();
        Player deserializedP5 = new Player();

        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("players.ser"))) {
            deserializedP1 = deserializedP1.readObject(inputStream);
            deserializedP2 = deserializedP2.readObject(inputStream);
            deserializedP3 = deserializedP3.readObject(inputStream);
            deserializedP4 = deserializedP4.readObject(inputStream);
            deserializedP5 = deserializedP5.readObject(inputStream);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }   

        // Check the Serialization works by comparison
        assertEquals(p1.toString(), deserializedP1.toString());
        assertEquals(p2.toString(), deserializedP2.toString());
        assertEquals(p3.toString(), deserializedP3.toString());
        assertEquals(p4.toString(), deserializedP4.toString());
        assertEquals(p5.toString(), deserializedP5.toString());
        }

        /**
         * Main method
         * @param args The command-line arguments.
         */
        public static void main(String[] args) {
            // Allows for the running of the methods without making them static 
            org.junit.runner.JUnitCore.main("SerialisationTest");
        }
}