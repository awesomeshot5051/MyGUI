import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CustomColorChooser extends JDialog {
    private Color backgroundColor;
    private Color buttonColor;
    private Color textColor;
    private boolean colorsSelected = false;

    public CustomColorChooser(JFrame parent) {
        super(parent, "Custom Color Chooser", true);
        setSize(400, 300);
        setLocationRelativeTo(parent);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(4, 1));

        JButton backgroundColorButton = new JButton("Choose Background Color");
        JButton buttonColorButton = new JButton("Choose Button Color");
        JButton textColorButton = new JButton("Choose Text Color");
        JButton applyButton = new JButton("Apply Colors");

        backgroundColorButton.addActionListener(new ColorChooserListener());
        buttonColorButton.addActionListener(new ColorChooserListener());
        textColorButton.addActionListener(new ColorChooserListener());
        applyButton.addActionListener(e -> {
            colorsSelected = true;
            setVisible(false);
        });

        mainPanel.add(backgroundColorButton);
        mainPanel.add(buttonColorButton);
        mainPanel.add(textColorButton);
        mainPanel.add(applyButton);

        add(mainPanel);
    }

    private class ColorChooserListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Color chosenColor = JColorChooser.showDialog(null, "Choose a Color", Color.WHITE);
            if (chosenColor != null) {
                if (e.getSource() instanceof JButton sourceButton) {
                    if (sourceButton.getText().contains("Background")) {
                        backgroundColor = chosenColor;
                    } else if (sourceButton.getText().contains("Button")) {
                        buttonColor = chosenColor;
                    } else if (sourceButton.getText().contains("Text")) {
                        textColor = chosenColor;
                    }
                }
            }
        }
    }

    public Color getBackgroundColor() {
        return backgroundColor;
    }

    public Color getButtonColor() {
        return buttonColor;
    }

    public Color getTextColor() {
        return textColor;
    }

    public boolean areColorsSelected() {
        return colorsSelected;
    }
}
