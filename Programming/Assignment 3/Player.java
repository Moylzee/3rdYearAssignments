import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Class representing a Player
 * implements serializable interface
 * @author Brian Moyles - 21333461
 */
public class Player implements Serializable {

    private String id;
    private String username;
    private Country country;
    private LocalDate joinDate;
    private transient List<Achievement> achievements; // transient - Won't be serialized
    
    /**
    * Default Constructor 
    */
    public Player(){}

    /**
    * Constructor to create Player with attributes 
    * @param id        unique id for player 
    * @param username  username for player 
    * @param country   country player is from 
    * @param joinDate  date when player joined 
    */
    public Player(String id, String username, Country country, LocalDate joinDate) {
        this.id = id;
        this.username = username;
        this.country = country;
        this.joinDate = joinDate;
        this.achievements = new ArrayList<>();
    }

    /**
    * Method to add achievement to the player achievement list
    * uses parameters to create achievement and then adds it to list
    * @param achievementName   Name of achievement 
    * @param desc              Achievement Description
    * @param dateOfAward       Date of award
    */ 
    public void AddAchievement(String achievementName, String desc, LocalDate dateOfAward){
        Achievement a = new Achievement(achievementName, desc, dateOfAward);
        achievements.add(a);
    }
    
    /**
    * Method to serialize the player to the output file
    * Updates the Achievements file 
    * @param out           Parameter passed through representing the output file to write to
    * @throws IOException  Thrown if I/O exception occurs
    */
    public void writeObject(ObjectOutputStream out) throws IOException {
        out.writeObject(this);
        try (FileWriter writer = new FileWriter("achievements.csv", true); // Open in append mode
                BufferedWriter bufferedWriter = new BufferedWriter(writer)) {
            for (Achievement a : achievements) {
                bufferedWriter.write("Player id: " +id+ " - " + a.toCSV());
                bufferedWriter.newLine(); // Write a new line
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
    * Method to deserialize from the file 'players.ser' which is passed through as a parameter
    * Get the achievements also from the achievements file 
    * @param in                        The ObjectInputStream passed through 
    * @return                          Returns the Deserialized player object
    * @throws IOException              If an I/O error occurs 
    * @throws ClassNotFoundException   If the class can't be found 
    */
    public Player readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        Player player = (Player)in.readObject();
        achievements = new ArrayList<>();
        
        try (BufferedReader reader = new BufferedReader(new FileReader("achievements.csv"))){
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    Achievement achievement = new Achievement(parts[1], parts[2], LocalDate.parse(parts[3]));
                    achievements.add(achievement);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return player;
    }

    /**
    * Method to print the Details 
    */
    public String toString() {
        return ("id: " +getId()+
                ", Username: " +getUsername()+
                ", Country: " +getCountry()+
                ", Join Date: " +getJoinDate() 
        );
    }

    /**
    * Getter and setter methods 
    */
    public String getId() {return id;}
    public String getUsername() {return username;}
    public Country getCountry() {return country;}
    public LocalDate getJoinDate() {return joinDate;}
    public List<Achievement> getAchievements() {return achievements;}
    
    public void setId(String id) {this.id = id;}
    public void setUsername(String username) {this.username = username;}
    public void setCountry(Country country) {this.country = country;}
    public void setJoinDate(LocalDate joinDate) {this.joinDate = joinDate;}
    public void setAchievements(List<Achievement> achievements) {this.achievements = achievements;}
}