import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class numberGame extends JFrame {
    private int low;
    private int high;
    private int num;
    private JTextField userInputField;
    private JButton submitButton;
    private JTextArea thinkingTextArea;
    private Thread guessingThread;
    private Thread animationThread;
    private boolean isAdmin = LoginGUI.isAdmin();
    private JFrame frame = new JFrame("Number Game");
    private static LoginGUI loginGUI = null;

    public void setLoginGUI(LoginGUI loginGUI) {
        numberGame.loginGUI = loginGUI;
    }

    public void computerGuesses() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setResizable(true);

        JPanel inputPanel = new JPanel();
        userInputField = new JTextField(10);
        submitButton = new JButton("Submit");
        submitButton.setBackground(Color.BLUE);
        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(e -> {
            frame.dispose();
            new numberGame(loginGUI);
        });
        inputPanel.add(new JLabel("Enter a number: "));
        inputPanel.add(userInputField);
        inputPanel.add(submitButton);

        thinkingTextArea = new JTextArea();
        thinkingTextArea.setEditable(false);
        thinkingTextArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
        JPanel animationPanel = new JPanel(new BorderLayout());
        animationPanel.add(new JScrollPane(thinkingTextArea), BorderLayout.CENTER);
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(inputPanel, BorderLayout.NORTH);
        mainPanel.add(animationPanel, BorderLayout.CENTER);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startThinkingAnimation();
            }
        });

        frame.setContentPane(mainPanel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public numberGame(LoginGUI loginGUI) {
        setLoginGUI(loginGUI);
        setTitle("Number Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        JPanel mainPanel = new JPanel(new GridLayout(0, 1));
        JLabel header = new JLabel("Game Type");
        mainPanel.add(header);

        JButton computerGuessButton = new JButton("Computer guesses");
        computerGuessButton.addActionListener(e -> {
            dispose();
            computerGuesses();
        });
        mainPanel.add(computerGuessButton);

        JButton userGuessButton = new JButton("User Guesses");
        userGuessButton.addActionListener(e -> {
            dispose();
            userGuesses();
        });
        mainPanel.add(userGuessButton);

        JButton userVuserButton = new JButton("User VS User");
        userVuserButton.addActionListener(e -> {
            dispose();
            userVuser();
        });
        mainPanel.add(userVuserButton);

        JButton exitButton = new JButton("Exit");
        if (loginGUI != null) {
            exitButton.addActionListener(e -> {
                dispose();
                loginGUI.openWelcomeWindow(isAdmin);
            });
        } else {
            exitButton.addActionListener(e -> {
                dispose();
            });
        }
        mainPanel.add(exitButton);

        setContentPane(mainPanel);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void startThinkingAnimation() {
        int userNum = Integer.parseInt(userInputField.getText());
        low = 1;
        high = 100;
        Random random = new Random();
        num = random.nextInt(low, high);

        guessingThread = new Thread(() -> {
            long startTime = System.currentTimeMillis();
            int guesses = 0;

            try {
                while (num != userNum) {
                    if (num > userNum) {
                        high = num - 1;
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    } else if (num < userNum) {
                        low = num + 1;
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    try {
                        num = random.nextInt(low, high);
                    } catch (IllegalArgumentException e) {
                        showGuessResult("Computer failed to guess the number. User wins!");
                        break;
                    }
                    guesses++;
                }
            } catch (IllegalArgumentException e) {
                showGuessResult("Computer failed to guess the number. User wins!");
            }
            long endTime = System.currentTimeMillis();
            long elapsedTime = endTime - startTime;
            showGuessResult("Computer guessed your number in " + guesses + " attempts.\n"
                    + "Time elapsed: " + elapsedTime / 1000 + " seconds.\n Your number was: " + num);
        });

        animationThread = new Thread(() -> {
            String thinking = "thinking";
            while (!Thread.currentThread().isInterrupted()) {
                thinkingTextArea.setText(thinking);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                thinking = thinking.substring(1) + thinking.charAt(0);
            }
        });

        userInputField.setEnabled(false);
        submitButton.setEnabled(false);
        guessingThread.start();
        animationThread.start();
    }

    private void showGuessResult(String message) {
        guessingThread.interrupt();
        animationThread.interrupt();

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                thinkingTextArea.setText("");
                JOptionPane.showMessageDialog(numberGame.this, message, "Guess Result",
                        JOptionPane.INFORMATION_MESSAGE);
                frame.dispose();
                numberGame game = new numberGame(loginGUI); // Create a new instance of numberGame
                game.setVisible(true); // Make the new instance visible
            }
        });
    }

    public void userGuesses() {
        Random random = new Random();
        low = 0;
        high = 100;
        int computerNum = random.nextInt(low, high);

        while (true) {
            String userString = JOptionPane.showInputDialog(null, "Guess the number:");
            if (userString == null) {
                dispose();
                new numberGame(loginGUI);
                break;
            } else {
                try {
                    int userNum = Integer.parseInt(userString);
                    if (userNum > computerNum) {
                        JOptionPane.showMessageDialog(null, "Too high! Try again.", "Guess Result",
                                JOptionPane.INFORMATION_MESSAGE);
                    } else if (userNum < computerNum) {
                        JOptionPane.showMessageDialog(null, "Too low! Try again.", "Guess Result",
                                JOptionPane.INFORMATION_MESSAGE);
                    } else if (userNum == JOptionPane.CANCEL_OPTION) {
                        System.exit(-1);
                    } else {
                        JOptionPane.showMessageDialog(null, "Congratulations! You guessed it.", "Guess Result",
                                JOptionPane.INFORMATION_MESSAGE);
                        new numberGame(loginGUI);
                        break;
                    }
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Invalid input! Please enter a valid number.", "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    public void userVuser() {
        int userNum1 = 0;
        boolean guessed = false;
        while (true) {
            if (guessed) {
                break;
            }
            String input = JOptionPane.showInputDialog(null, "Enter a number between 0 and 100:");
            if (input == null) {
                // User clicked cancel or closed the dialog
                dispose();
                new numberGame(loginGUI);
                break;
            } else {
                try {
                    userNum1 = Integer.parseInt(input);

                    if (userNum1 > 100) {
                        JOptionPane.showMessageDialog(null, "Number is too large\nPlease enter a smaller number");
                    } else if (userNum1 < 0) {
                        JOptionPane.showMessageDialog(null, "Number is too small\nPlease enter a bigger number.");
                    } else {
                        // Valid number entered
                        ;
                    }
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Invalid number please enter a whole number");

                }
            }
            while (true) {
                try {
                    int userNum2 = Integer.parseInt(JOptionPane.showInputDialog(null, "Guess the number:"));

                    if (userNum1 > userNum2) {
                        JOptionPane.showMessageDialog(null, "Too low! Try again.", "Guess Result",
                                JOptionPane.INFORMATION_MESSAGE);
                        continue;
                    } else if (userNum1 < userNum2) {
                        JOptionPane.showMessageDialog(null, "Too high! Try again.", "Guess Result",
                                JOptionPane.INFORMATION_MESSAGE);
                        continue;
                    } else if (userNum2 == JOptionPane.CANCEL_OPTION) {
                        System.exit(-1);
                    } else {
                        JOptionPane.showMessageDialog(null, "Congratulations! You guessed it.", "Guess Result",
                                JOptionPane.INFORMATION_MESSAGE);
                        new numberGame(loginGUI);
                        guessed = true;
                        break;
                    }
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Invalid input! Please enter a valid number.", "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
                break;
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new numberGame(loginGUI);
            }
        });
    }
}
