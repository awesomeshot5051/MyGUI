import javax.swing.*;
import java.awt.*;

public class whatIf {
    private final JTextField[] dynamicFields;

    public whatIf(ImageIcon icon, Double balance, int amount) {
        JFrame whatIF = new JFrame("What If?");
        whatIF.setLayout(new GridLayout(amount + 3, 2)); // Adjust layout manager to accommodate dynamic fields

        JLabel textLabel = new JLabel("Cost of Product");
        JTextField costOfProduct = new JTextField();
        JLabel balanceLabel = new JLabel("Current Balance");
        JTextField balanceText = new JTextField(String.valueOf(balance));
        JButton leaveButton = new JButton("Exit");
        leaveButton.addActionListener(_ -> whatIF.dispose());
        JButton testIfEnough = new JButton("Test If Enough");

        dynamicFields = new JTextField[amount];
        for (int i = 0; i < amount; i++) {
            JLabel dynamicLabel = new JLabel("Cost of Product " + (i + 1));
            dynamicFields[i] = new JTextField();
            whatIF.add(dynamicLabel);
            whatIF.add(dynamicFields[i]);
        }

        testIfEnough.addActionListener(_ -> {
            try {
                int totalProductCost = Integer.parseInt(costOfProduct.getText());
                for (JTextField field : dynamicFields) {
                    totalProductCost += Integer.parseInt(field.getText());
                }
                double currentBalance = Double.parseDouble(balanceText.getText());
                boolean enough = checkIfEnough(totalProductCost, currentBalance);
                String message = enough ? "You have enough!" : "You don't have enough.";
                JOptionPane.showMessageDialog(null, message);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Please enter valid numbers.");
            }
        });

        whatIF.add(textLabel);
        whatIF.add(costOfProduct);
        whatIF.add(balanceLabel);
        whatIF.add(balanceText);
        whatIF.add(testIfEnough);
        whatIF.add(leaveButton);

        whatIF.setIconImage(icon.getImage());
        whatIF.setSize(400, 200 + (amount * 30)); // Adjust size based on the number of dynamic fields
        whatIF.setVisible(true);
        whatIF.setLocationRelativeTo(null);
    }

    private boolean checkIfEnough(int amount, double currentBalance) {
        return currentBalance - amount > 0;
    }
}
