import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
* This is the main class for the music Library App
* It displays the GUI and the details of each album
* @author Brian Moyles - 21333461
*/

public class MusicApp {
    private JFrame frame;
    private List<Album> album = new ArrayList<>();
    String trackNumber;
    String trackName;
    String trackLength;

    /**
     * Constructor
     * Loads the albums
     * Displays the GUI
     */
    public MusicApp() {
        album = new ArrayList<>();
        loadAlbums();
        showGUI();
    }

    /**
     * Method to load the albums from the specified file
     * Adds the albums to the list of albums
     */
    private void loadAlbums() {
        try (BufferedReader reader = new BufferedReader(new FileReader("./LibraryData/music_library.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                album.add(new Album(line));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method to display the GUI
     * It goes through the album list and creates a panel for each one 
     * Each panel has the album details and can be clicked to view the songs
     */
    private void showGUI() {
        frame = new JFrame("Music Library");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close Window Operation
        JPanel p = new JPanel(new GridLayout(1,1));
        // For every element in the album list create a button that is the album image
        // When the button is clicked it shows the songs on that album 
        for (Album a : album) {
            JPanel albumPanel = new JPanel(new BorderLayout()); 
            JButton button = new JButton("", a.coverImage);
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    showAlbum(a); // Display the songs in the selected album
                    frame.dispose(); // Close the main GUI
                }
            });
            // Labels for each album
            JLabel artistLabel = new JLabel("Artist: " +a.artist);
            JLabel albumLabel = new JLabel("Album: " +a.albumName);
            JPanel buttonPanel = new JPanel();
            // Add the elements to the panel 
            albumPanel.add(buttonPanel, BorderLayout.CENTER);
            albumPanel.add(artistLabel, BorderLayout.NORTH);
            albumPanel.add(albumLabel, BorderLayout.SOUTH);
            buttonPanel.add(button);
            // Add the panel to the frame 
            p.add(albumPanel);
        }
        frame.add(p); // Add the panel to the frame
        frame.pack(); // Adjusts the frame size based on its contents
        frame.setVisible(true); // Sets the GUI as visible
    }

    /**
     * Method to display the songs in the selected album
     * New frame is opened for the specific album
     * Table is created to display the song details 
     * A button is also added to return to the main GUI
     * @param a The album that was selected to retrieve further details
     */
    private void showAlbum(Album a) {
        JFrame albumDetails = new JFrame("Album Details");
        albumDetails.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create table with 3 columns 
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Track Number");
        model.addColumn("Track Name");
        model.addColumn("Track Length");
        JTable albumTable = new JTable(model);
        albumTable.setEnabled(false);

        // Fill the table with data from the album object after it is split correctly
        for (String s : a.songs) {
            String[] parts = s.replaceAll("\"", "").split(",");
            trackNumber = parts[0].trim();
            trackName = parts[1].trim();
            trackLength = parts[2].trim();
            model.addRow(new Object[]{trackNumber, trackName, trackLength});
        }

        // Button to go back to the previous GUI
        JPanel buttonPanel = new JPanel();
        JButton backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showGUI(); // Show the main GUI
                albumDetails.dispose(); // Close the album GUI
            }
        });
        buttonPanel.add(backButton);
        
        // Put everything together and set up the layout
        JScrollPane albumScrollPane = new JScrollPane(albumTable);
        JPanel main = new JPanel(new BorderLayout());
        main.add(albumScrollPane, BorderLayout.CENTER);
        main.add(buttonPanel, BorderLayout.SOUTH);
        albumDetails.add(main);
        albumDetails.pack();
        albumDetails.setVisible(true);
    }

    /**
     * Main method to run the application 
     * @param args args
     */
    public static void main(String[] args) {
        new MusicApp();
    }
}