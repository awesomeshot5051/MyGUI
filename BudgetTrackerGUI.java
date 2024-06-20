import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class BudgetTrackerGUI extends LoginGUI {
    // Add your budget tracker components and logic here

    public BudgetTrackerGUI() throws InterruptedException {
        // Call the constructor of the parent class (loginGUI) if needed
        super();

        // Initialize and set up your budget tracker components
        initComponents();
    }

    private void initComponents() {
        JFrame budgetTracker = new JFrame("Budget Tracker");
        budgetTracker.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel welcomePanel = new JPanel();
        welcomePanel.setLayout(new BoxLayout(welcomePanel, BoxLayout.PAGE_AXIS));
        Dimension panelSize = new Dimension(200, 200);
        welcomePanel.setPreferredSize(panelSize);
        budgetTracker.setVisible(true);
        System.out.println("Hello there");
    }

    // Implement your budget tracker functionality (e.g., adding expenses,
    // calculating totals, etc.)
}
