import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import javax.swing.*;

public class CodeCracker {
    private List<Character> crackedPasswd;
    private List<Character> characters;
    private JTextArea outputTextArea;
    private JTextField passwordField;
    private JButton crackButton;
    private boolean isAdmin;
    private StringBuilder crackedPassword;
    private ImageIcon icon;

    public CodeCracker(LoginGUI loginGUI) {
        icon = IconFinder.findIcon();
        this.isAdmin = LoginGUI.isAdmin();
        crackedPasswd = new ArrayList<>();
        characters = new ArrayList<>(Arrays.asList(
                ' ', '!', '"', '#', '$', '%', '&', '\'', '(', ')', '*', '+', ',', '-', '.', '/', ':', ';', '<', '=',
                '>', '?', '@', '[', '\\', ']', '^', '_', '`', '{', '|', '}', '~', '0', '1', '2', '3', '4', '5', '6',
                '7',
                '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S',
                'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n',
                'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'));

        JFrame frame = new JFrame("Code Cracker");
        frame.setIconImage(icon.getImage());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new BorderLayout());

        outputTextArea = new JTextArea();
        outputTextArea.setEditable(false);
        outputTextArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));

        passwordField = new JPasswordField(20);
        crackButton = new JButton("Crack Password");
        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(e -> {
            frame.dispose();
            loginGUI.openWelcomeWindow(isAdmin);
        });

        JPanel inputPanel = new JPanel();
        inputPanel.add(passwordField);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(crackButton);
        buttonPanel.add(exitButton);

        frame.add(outputTextArea, BorderLayout.CENTER);
        frame.add(inputPanel, BorderLayout.NORTH);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        crackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                crackedPassword = null;
                String password = passwordField.getText().trim();
                startCracking(password);
            }
        });
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public CodeCracker() {
        crackedPasswd = new ArrayList<>();
        characters = new ArrayList<>(Arrays.asList(
                ' ', '!', '"', '#', '$', '%', '&', '\'', '(', ')', '*', '+', ',', '-', '.',
                '/', ':', ';', '<', '=',
                '>', '?', '@', '[', '\\', ']', '^', '_', '`', '{', '|', '}', '~', '0', '1',
                '2', '3', '4', '5', '6',
                '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L',
                'M', 'N', 'O', 'P', 'Q', 'R',
                'S',
                'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h',
                'i', 'j', 'k', 'l', 'm', 'n',
                'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'));
//        characters = new ArrayList<>();
//        for (int i = 32; i <= 2556; i++) {
//            characters.add((char) i);
//        }
        System.out.println(characters + " " + characters.size());
        JFrame frame = new JFrame("Code Cracker");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new BorderLayout());

        outputTextArea = new JTextArea();
        outputTextArea.setEditable(false);
        outputTextArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));

        passwordField = new JPasswordField(20);
        crackButton = new JButton("Crack Password");
        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(e -> {
            frame.dispose();
        });

        JPanel inputPanel = new JPanel();
        inputPanel.add(passwordField);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(crackButton);
        buttonPanel.add(exitButton);

        frame.add(outputTextArea, BorderLayout.CENTER);
        frame.add(inputPanel, BorderLayout.NORTH);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        crackButton.addActionListener (_ -> {
            crackedPasswd = new ArrayList<>();
            String password = passwordField.getText().trim();
            startCracking(password);
        });
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public void startCracking(String password) {
        crackedPasswd = new ArrayList<>();
        SwingWorker<Void, String> worker = new SwingWorker<Void, String>() {
            @Override
            protected Void doInBackground() throws Exception {
                crackedPassword = new StringBuilder();

                for (int i = 0; i < password.length(); i++) {
                    List<Character> tempList = new ArrayList<>(characters);
                    char targetChar = password.charAt(i);
                    boolean found = false;

                    Random random = new Random();
                    for (int k = 0; k < characters.size(); k++) {
                        int pos = random.nextInt(tempList.size());
                        char c = tempList.get(pos);
                        publish(crackedPassword.toString() + c);
                        Thread.sleep(100); // Adjust the delay as needed

                        if (c == targetChar) {
                            found = true;
                            break;
                        } else {
                            tempList.remove(pos);
                        }
                    }

                    if (found) {
                        crackedPasswd.add(targetChar);
                        crackedPassword.append(targetChar);
                    } else {
                        crackedPassword.append("?");
                    }
                }

                return null;
            }

            @Override
            protected void process(List<String> chunks) {
                String latestChunk = chunks.get(chunks.size() - 1);
                outputTextArea.setText(latestChunk);
            }

            @Override
            protected void done() {
                String crackedPassword = crackedPasswd.toString();
                crackedPassword = crackedPassword.replaceAll("[\\[\\], ]", "");
                outputTextArea.append("\n\nPassword cracked: " + crackedPassword);
                crackedPasswd = new ArrayList<>();
            }
        };

        worker.execute();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new CodeCracker();
            }
        });
    }
}
