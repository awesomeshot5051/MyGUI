import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class SongLibraryGUI extends JFrame implements ActionListener {
    private Clip clip;
    private String songTitle;
    private JFrame songFrame;
    private JPanel songJPanel;

    public SongLibraryGUI() {
        songFrame = new JFrame("Song Library");
        songJPanel = new JPanel();
        songFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        songJPanel.setLayout(new BoxLayout(songJPanel, BoxLayout.PAGE_AXIS));

        // Create song buttons
        JButton song1Button = new JButton("The Joe Biden Song");
        JButton song2Button = new JButton("Imperial March");
        JButton song3Button = new JButton("Song 3");

        // Create stop button
        JButton stopButton = new JButton("Stop");

        // Add action listeners to song buttons
        song1Button.addActionListener(this);
        song2Button.addActionListener(this);
        song3Button.addActionListener(this);

        // Add action listener to stop button
        stopButton.addActionListener(this);

        // Add buttons to the frame
        songJPanel.add(song1Button);
        songJPanel.add(song2Button);
        songJPanel.add(song3Button);
        songJPanel.add(stopButton);
        songFrame.setContentPane(songJPanel);
        songFrame.pack();
        songFrame.setLocationRelativeTo(null);
        songFrame.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Stop")) {
            stopSong();
        } else {
            songTitle = e.getActionCommand();
            playSong();
        }
    }

    private void playSong() {
        if (clip != null && clip.isRunning()) {
            stopSong(); // Stop the current song if it's playing
        } else {
            // Do nothing if no song is currently playing
        }
        try {
            // Specify the path to your song files
            String filePath = "C:\\Users\\Traee.TRAES_GAMING_PC\\OneDrive - University of North Florida\\Programming\\College Programming\\Programming 2\\Codes\\MyGUI\\Song Library\\"
                    + songTitle.toLowerCase().replace(" ", "_") + ".wav";
            File audioFile = new File(filePath);
            JOptionPane.showMessageDialog(songFrame, "Playing " + songTitle, "Playing",
                    JOptionPane.INFORMATION_MESSAGE);
            // Create an AudioInputStream from the file
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);

            // Get the clip for playback
            clip = AudioSystem.getClip();
            clip.open(audioStream);

            // Start playing the song
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
            JOptionPane.showMessageDialog(songFrame, "No audio found!", "Play Error",
                    JOptionPane.ERROR_MESSAGE);
        }

    }

    private void stopSong() {
        if (clip == null) {
            JOptionPane.showMessageDialog(songFrame, "Nothing is playing", "Song Error",
                    JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(songFrame, "Stopping " + songTitle, "Stopping",
                    JOptionPane.INFORMATION_MESSAGE);
        }
        if (clip != null && clip.isRunning()) {
            clip.stop();
        }
        clip = null;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new SongLibraryGUI();
            }
        });
    }
}
