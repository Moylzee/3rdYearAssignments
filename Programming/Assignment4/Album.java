import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * This class represents albums in the music app Library 
 * It is used to organise the album details and song details
 * @author Brian Moyles - 21333461
 */

public class Album {
    String artist;
    String albumName;
    public String albumData;
    ImageIcon coverImage;
    List<String> songs;
    
    /**
     * Constructs an album
     * Parses the data
     * @param line line containing the data 
     */
    public Album(String line) {
        // Split the lines into parts, separated by commas 
        String[] parts = line.split(",");
        if (parts.length == 4) {
            // trim the lines into artist, albumName, coverImage, Songs
            artist = parts[0].trim();
            albumName = parts[1].trim();
            // Use the bufferedImage class to read the images and scale them to size
            try {
                BufferedImage bi = ImageIO.read(new File(parts[2].trim()));
                Image dimg = bi.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
                // assign the scaled image to coverImage
                coverImage = new ImageIcon(dimg);
            } catch (IOException e) {
                e.printStackTrace();
            }
            // Songs are passed through a method to read them in the correct sequence 
            albumData = parts[3].trim(); 
            songs = readSongs(albumData);
        }
    }

    /**
     * Method to read the songs from the specified file 
     * Songs are added to a list line by line
     * @param filePath path to the file that contains the data of the songs
     * @return Returns the songgs in a list 
     */
    public List<String> readSongs(String filePath) {
        List<String> songs = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                songs.add(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } 
        return songs;
    }
}