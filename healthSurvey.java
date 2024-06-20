//import java.awt.*;
//import java.awt.event.KeyEvent;
//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;
//import java.util.Enumeration;
//
//import javax.swing.*;
//import javax.swing.text.NumberFormatter;
//
//public class healthSurvey {
//    private JLabel headingLabel;
//    private JLabel firstNameLabel;
//    private JTextField firstName;
//    private JLabel lastNameLabel;
//    private JTextField lastName;
//    private JLabel phoneNumberLabel;
//    private JTextField phoneNumber;
//    private JLabel emailLabel;
//    private JTextField email;
//    private JRadioButton maleRadioButton;
//    private JRadioButton femaleJRadioButton;
//    private JRadioButton pntsJRadioButton;
//    private ButtonGroup radioButtonGroup;
//    private JLabel dietQuestionsLabel;
//    private JLabel cupsOfWaterLabel;
//    private SpinnerNumberModel spinnerModel;
//    private JSpinner waterIntakeSpinner;
//    private JLabel mealIntakeJLabel;
//    private JSlider mealJSlider;
//    private JCheckBox wheatCheckBox;
//    private JCheckBox sugarCheckBox;
//    private JCheckBox dairyCheckBox;
//    private JComboBox<String> walkComboBox;
//    private JFormattedTextField weightFormattedTextField;
//    private fileHandler filehandler = new fileHandler();
//    private static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");
//    private boolean isAdmin;
//
//    public healthSurvey() {
//        JFrame customJFrame = new JFrame("Dietary Survey");
//        customJFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        JPanel customPanel = new JPanel(new GridBagLayout());
//        GridBagConstraints gbc = new GridBagConstraints();
//        gbc.insets = new Insets(5, 5, 5, 5);
//
//        headingLabel = new JLabel("Personal Information");
//        firstNameLabel = new JLabel("First Name");
//        firstName = new JTextField(20);
//        lastNameLabel = new JLabel("Last Name");
//        lastName = new JTextField(20);
//        phoneNumberLabel = new JLabel("Phone Number");
//        phoneNumber = new JTextField(20);
//        emailLabel = new JLabel("Email");
//        email = new JTextField(20);
//        maleRadioButton = new JRadioButton("Male");
//        femaleJRadioButton = new JRadioButton("Female");
//        pntsJRadioButton = new JRadioButton("Prefer not to say");
//        maleRadioButton.setMnemonic(KeyEvent.VK_0);
//        femaleJRadioButton.setMnemonic(KeyEvent.VK_1);
//        pntsJRadioButton.setMnemonic(KeyEvent.VK_2);
//        radioButtonGroup = new ButtonGroup();
//        radioButtonGroup.add(maleRadioButton);
//        radioButtonGroup.add(femaleJRadioButton);
//        radioButtonGroup.add(pntsJRadioButton);
//        dietQuestionsLabel = new JLabel("Dietary Questions");
//        cupsOfWaterLabel = new JLabel("How many cups of water on average do you think you drink a day?");
//        spinnerModel = new SpinnerNumberModel(15, 0, 50, 1);
//        waterIntakeSpinner = new JSpinner(spinnerModel);
//        mealIntakeJLabel = new JLabel("How many meals on average do you eat a day?");
//        mealJSlider = new JSlider(0, 0, 10, 3);
//        mealJSlider.setMajorTickSpacing(1);
//        mealJSlider.setPaintTicks(true);
//        mealJSlider.setPaintLabels(true);
//        wheatCheckBox = new JCheckBox("Wheat");
//        wheatCheckBox.setMnemonic(KeyEvent.VK_3);
//        sugarCheckBox = new JCheckBox("Sugar");
//        sugarCheckBox.setMnemonic(KeyEvent.VK_4);
//        dairyCheckBox = new JCheckBox("Dairy");
//        dairyCheckBox.setMnemonic(KeyEvent.VK_5);
//        // checkBoxGroup = new ButtonGroup();
//        // checkBoxGroup.add(wheatCheckBox);
//        // checkBoxGroup.add(sugarCheckBox);
//        // checkBoxGroup.add(dairyCheckBox);
//
//        JLabel checkBoxLabel = new JLabel("Do any of these meals regularly contain:");
//        JLabel walkLabel = new JLabel("On average, how many miles do you walk a day?");
//        String[] walkingOptions = { "Less than 1 Mile", "More than 1 mile but less than 2 miles",
//                "More than 2 miles but less than 3 miles", "More than 3 miles" };
//        walkComboBox = new JComboBox<>(walkingOptions);
//        JLabel weightLabel = new JLabel("How much do you weigh?");
//        NumberFormatter formatter = new NumberFormatter();
//        try {
//            // Create a NumberFormatter to accept only numbers
//            formatter.setValueClass(Integer.class);
//            formatter.setMinimum(0); // Optional: Set a minimum value
//            formatter.setMaximum(9999); // Optional: Set a maximum value
//            formatter.setAllowsInvalid(false); // Only accept valid numbers
//
//            // Create the JFormattedTextField using the NumberFormatter
//        } catch (Exception e) {
//            weightFormattedTextField = new JFormattedTextField();
//        }
//        weightFormattedTextField = new JFormattedTextField(formatter);
//
//        weightFormattedTextField.setColumns(14);
//        JButton clearButton = new JButton("Clear");
//        clearButton.addActionListener(e -> {
//            clearForm();
//        });
//
//        JButton submitButton = new JButton("Submit");
//        submitButton.addActionListener(e -> {
//            submitForm();
//        });
//        gbc.anchor = GridBagConstraints.WEST;
//        gbc.gridx = 0;
//        gbc.gridy = 0;
//        gbc.gridwidth = 1;
//        // gbc.gridheight = 10;
//        customPanel.add(headingLabel, gbc);
//        gbc.gridy = 1;
//        gbc.gridx = 0;
//        gbc.gridwidth = 5;
//        customPanel.add(firstNameLabel, gbc);
//
//        gbc.gridx = 1;
//        customPanel.add(firstName, gbc);
//
//        gbc.gridy = 2;
//        gbc.gridx = 0;
//        customPanel.add(lastNameLabel, gbc);
//
//        gbc.gridx = 1;
//        customPanel.add(lastName, gbc);
//
//        gbc.gridy = 3;
//        gbc.gridx = 0;
//        customPanel.add(phoneNumberLabel, gbc);
//
//        gbc.gridx = 1;
//        customPanel.add(phoneNumber, gbc);
//
//        gbc.gridy = 4;
//        gbc.gridx = 0;
//        customPanel.add(emailLabel, gbc);
//
//        gbc.gridx = 1;
//        customPanel.add(email, gbc);
//        gbc.gridy = 5;
//        gbc.gridx = 0;
//        customPanel.add(new JLabel("Sex"), gbc);
//        gbc.gridx = 1;
//        customPanel.add(maleRadioButton, gbc);
//        gbc.gridy = 6;
//        customPanel.add(femaleJRadioButton, gbc);
//        gbc.gridy = 7;
//        customPanel.add(pntsJRadioButton, gbc);
//        gbc.gridy = 8;
//        gbc.gridx = 0;
//        customPanel.add(dietQuestionsLabel, gbc);
//        gbc.gridy = 9;
//        gbc.gridx = 0;
//        customPanel.add(cupsOfWaterLabel, gbc);
//        // gbc.anchor = GridBagConstraints.CENTER;
//        // gbc.gridwidth = GridBagConstraints.REMAINDER;
//        gbc.gridy = 10;
//        gbc.gridx = 1;
//        customPanel.add(waterIntakeSpinner, gbc);
//        gbc.gridy = 11;
//        gbc.gridx = 0;
//        customPanel.add(mealIntakeJLabel, gbc);
//        gbc.gridy = 12;
//        customPanel.add(mealJSlider, gbc);
//        gbc.gridy = 13;
//        gbc.gridx = 0;
//        customPanel.add(checkBoxLabel, gbc);
//        gbc.anchor = GridBagConstraints.CENTER;
//        gbc.gridwidth = 1;
//        // Checkbox 1: Wheat
//        gbc.gridx = 0;
//        gbc.gridy = 15;
//        customPanel.add(wheatCheckBox, gbc);
//
//        // Checkbox 2: Sugar
//        gbc.gridx = 1;
//        customPanel.add(sugarCheckBox, gbc);
//
//        // Checkbox 3: Dairy
//        gbc.gridx = 2;
//        customPanel.add(dairyCheckBox, gbc);
//        gbc.gridwidth = 5;
//        gbc.gridy = 16;
//        gbc.gridx = 0;
//        customPanel.add(walkLabel, gbc);
//        gbc.gridy = 17;
//        customPanel.add(walkComboBox, gbc);
//        gbc.gridy = 18;
//        customPanel.add(weightLabel, gbc);
//        gbc.gridy = 19;
//        customPanel.add(weightFormattedTextField, gbc);
//        gbc.anchor = GridBagConstraints.WEST;
//        gbc.gridy = 20;
//        gbc.gridx = 0;
//        customPanel.add(clearButton, gbc);
//        gbc.gridx = 4;
//        customPanel.add(submitButton, gbc);
//        customJFrame.setContentPane(customPanel);
//        customJFrame.pack();
//        customJFrame.setLocationRelativeTo(null);
//        customJFrame.setVisible(true);
//    }
//
//    public healthSurvey(LoginGUI loginGUI) {
//        this.isAdmin = LoginGUI.isAdmin();
//        JFrame customJFrame = new JFrame("Dietary Survey");
//        customJFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        JPanel customPanel = new JPanel(new GridBagLayout());
//        GridBagConstraints gbc = new GridBagConstraints();
//        gbc.insets = new Insets(5, 5, 5, 5);
//
//        headingLabel = new JLabel("Personal Information");
//        firstNameLabel = new JLabel("First Name");
//        firstName = new JTextField(20);
//        lastNameLabel = new JLabel("Last Name");
//        lastName = new JTextField(20);
//        phoneNumberLabel = new JLabel("Phone Number");
//        phoneNumber = new JTextField(20);
//        emailLabel = new JLabel("Email");
//        email = new JTextField(20);
//        maleRadioButton = new JRadioButton("Male");
//        femaleJRadioButton = new JRadioButton("Female");
//        pntsJRadioButton = new JRadioButton("Prefer not to say");
//        maleRadioButton.setMnemonic(KeyEvent.VK_0);
//        femaleJRadioButton.setMnemonic(KeyEvent.VK_1);
//        pntsJRadioButton.setMnemonic(KeyEvent.VK_2);
//        radioButtonGroup = new ButtonGroup();
//        radioButtonGroup.add(maleRadioButton);
//        radioButtonGroup.add(femaleJRadioButton);
//        radioButtonGroup.add(pntsJRadioButton);
//        dietQuestionsLabel = new JLabel("Dietary Questions");
//        cupsOfWaterLabel = new JLabel("How many cups of water on average do you think you drink a day?");
//        spinnerModel = new SpinnerNumberModel(15, 0, 50, 1);
//        waterIntakeSpinner = new JSpinner(spinnerModel);
//        mealIntakeJLabel = new JLabel("How many meals on average do you eat a day?");
//        mealJSlider = new JSlider(0, 0, 10, 3);
//        mealJSlider.setMajorTickSpacing(1);
//        mealJSlider.setPaintTicks(true);
//        mealJSlider.setPaintLabels(true);
//        wheatCheckBox = new JCheckBox("Wheat");
//        wheatCheckBox.setMnemonic(KeyEvent.VK_3);
//        sugarCheckBox = new JCheckBox("Sugar");
//        sugarCheckBox.setMnemonic(KeyEvent.VK_4);
//        dairyCheckBox = new JCheckBox("Dairy");
//        dairyCheckBox.setMnemonic(KeyEvent.VK_5);
//        // checkBoxGroup = new ButtonGroup();
//        // checkBoxGroup.add(wheatCheckBox);
//        // checkBoxGroup.add(sugarCheckBox);
//        // checkBoxGroup.add(dairyCheckBox);
//
//        JLabel checkBoxLabel = new JLabel("Do any of these meals regularly contain:");
//        JLabel walkLabel = new JLabel("On average, how many miles do you walk a day?");
//        String[] walkingOptions = { "Less than 1 Mile", "More than 1 mile but less than 2 miles",
//                "More than 2 miles but less than 3 miles", "More than 3 miles" };
//        walkComboBox = new JComboBox<>(walkingOptions);
//        JLabel weightLabel = new JLabel("How much do you weigh?");
//        NumberFormatter formatter = new NumberFormatter();
//        try {
//            // Create a NumberFormatter to accept only numbers
//            formatter.setValueClass(Integer.class);
//            formatter.setMinimum(0); // Optional: Set a minimum value
//            formatter.setMaximum(9999); // Optional: Set a maximum value
//            formatter.setAllowsInvalid(false); // Only accept valid numbers
//
//            // Create the JFormattedTextField using the NumberFormatter
//        } catch (Exception e) {
//            weightFormattedTextField = new JFormattedTextField();
//        }
//        weightFormattedTextField = new JFormattedTextField(formatter);
//
//        weightFormattedTextField.setColumns(14);
//        JButton clearButton = new JButton("Clear");
//        clearButton.addActionListener(e -> {
//            clearForm();
//        });
//
//        JButton submitButton = new JButton("Submit");
//        submitButton.addActionListener(e -> {
//            submitForm();
//        });
//        JButton exitButton = new JButton("Exit");
//        exitButton.addActionListener(e -> {
//            customJFrame.dispose();
//            loginGUI.openWelcomeWindow(isAdmin);
//        });
//        gbc.anchor = GridBagConstraints.WEST;
//        gbc.gridx = 0;
//        gbc.gridy = 0;
//        gbc.gridwidth = 1;
//        // gbc.gridheight = 10;
//        customPanel.add(headingLabel, gbc);
//        gbc.gridy = 1;
//        gbc.gridx = 0;
//        gbc.gridwidth = 5;
//        customPanel.add(firstNameLabel, gbc);
//
//        gbc.gridx = 1;
//        customPanel.add(firstName, gbc);
//
//        gbc.gridy = 2;
//        gbc.gridx = 0;
//        customPanel.add(lastNameLabel, gbc);
//
//        gbc.gridx = 1;
//        customPanel.add(lastName, gbc);
//
//        gbc.gridy = 3;
//        gbc.gridx = 0;
//        customPanel.add(phoneNumberLabel, gbc);
//
//        gbc.gridx = 1;
//        customPanel.add(phoneNumber, gbc);
//
//        gbc.gridy = 4;
//        gbc.gridx = 0;
//        customPanel.add(emailLabel, gbc);
//
//        gbc.gridx = 1;
//        customPanel.add(email, gbc);
//        gbc.gridy = 5;
//        gbc.gridx = 0;
//        customPanel.add(new JLabel("Sex"), gbc);
//        gbc.gridx = 1;
//        customPanel.add(maleRadioButton, gbc);
//        gbc.gridy = 6;
//        customPanel.add(femaleJRadioButton, gbc);
//        gbc.gridy = 7;
//        customPanel.add(pntsJRadioButton, gbc);
//        gbc.gridy = 8;
//        gbc.gridx = 0;
//        customPanel.add(dietQuestionsLabel, gbc);
//        gbc.gridy = 9;
//        gbc.gridx = 0;
//        customPanel.add(cupsOfWaterLabel, gbc);
//        // gbc.anchor = GridBagConstraints.CENTER;
//        // gbc.gridwidth = GridBagConstraints.REMAINDER;
//        gbc.gridy = 10;
//        gbc.gridx = 1;
//        customPanel.add(waterIntakeSpinner, gbc);
//        gbc.gridy = 11;
//        gbc.gridx = 0;
//        customPanel.add(mealIntakeJLabel, gbc);
//        gbc.gridy = 12;
//        customPanel.add(mealJSlider, gbc);
//        gbc.gridy = 13;
//        gbc.gridx = 0;
//        customPanel.add(checkBoxLabel, gbc);
//        gbc.anchor = GridBagConstraints.CENTER;
//        gbc.gridwidth = 1;
//        // Checkbox 1: Wheat
//        gbc.gridx = 0;
//        gbc.gridy = 15;
//        customPanel.add(wheatCheckBox, gbc);
//
//        // Checkbox 2: Sugar
//        gbc.gridx = 1;
//        customPanel.add(sugarCheckBox, gbc);
//
//        // Checkbox 3: Dairy
//        gbc.gridx = 2;
//        customPanel.add(dairyCheckBox, gbc);
//        gbc.gridwidth = 5;
//        gbc.gridy = 16;
//        gbc.gridx = 0;
//        customPanel.add(walkLabel, gbc);
//        gbc.gridy = 17;
//        customPanel.add(walkComboBox, gbc);
//        gbc.gridy = 18;
//        customPanel.add(weightLabel, gbc);
//        gbc.gridy = 19;
//        customPanel.add(weightFormattedTextField, gbc);
//        gbc.anchor = GridBagConstraints.WEST;
//        gbc.gridy = 20;
//        gbc.gridx = 0;
//        customPanel.add(clearButton, gbc);
//        gbc.gridx = 4;
//        customPanel.add(submitButton, gbc);
//        gbc.gridy = 21;
//        gbc.gridx = 2;
//        customPanel.add(exitButton, gbc);
//        customJFrame.setContentPane(customPanel);
//        customJFrame.pack();
//        customJFrame.setLocationRelativeTo(null);
//        customJFrame.setVisible(true);
//    }
//
//    private void clearForm() {
//        // Clear input fields
//        firstName.setText("");
//        lastName.setText("");
//        phoneNumber.setText("");
//        email.setText("");
//        radioButtonGroup.clearSelection();
//        waterIntakeSpinner.setValue(15);
//        mealJSlider.setValue(3);
//        wheatCheckBox.setSelected(false);
//        sugarCheckBox.setSelected(false);
//        dairyCheckBox.setSelected(false);
//        walkComboBox.setSelectedIndex(0);
//        weightFormattedTextField.setValue(null);
//    }
//
//    private void submitForm() {
//        // Write the form data to the CSV file
//        String timeFormatted = getFormattedTime();
//        filehandler.writeResults(timeFormatted + ",");
//        // //System.out.println(timeFormatted);
//        filehandler.writeResults(firstName.getText() + ",");
//        // //System.out.println(firstName.getText());
//        filehandler.writeResults(lastName.getText() + ",");
//        // //System.out.println(lastName.getText());
//        filehandler.writeResults(phoneNumber.getText() + ",");
//        // //System.out.println(phoneNumber.getText());
//        filehandler.writeResults(email.getText() + ",");
//        // //System.out.println(email.getText());
//
//        // Example: Write the selected radio button value
//        Component selectedComponent = null;
//
//        Enumeration<AbstractButton> buttons = radioButtonGroup.getElements();
//        while (buttons.hasMoreElements()) {
//            AbstractButton button = buttons.nextElement();
//            if (button.isSelected()) {
//                selectedComponent = button;
//                break;
//            }
//        }
//
//        if (selectedComponent != null) {
//            filehandler.writeResults(((AbstractButton) selectedComponent).getActionCommand().toString() + ",");
//            System.out.println(((AbstractButton) selectedComponent).getActionCommand().toString());
//        } else {
//            filehandler.writeResults("null,");
//            System.out.println("null");
//        }
//
//        // System.out.println(maleRadioButton.isSelected());
//        // System.out.println(femaleJRadioButton.isSelected());
//        // System.out.println(pntsJRadioButton.isSelected());
//        filehandler.writeResults(waterIntakeSpinner.getValue().toString() + ",");
//        // System.out.println(waterIntakeSpinner.getValue().toString());
//        filehandler.writeResults(mealJSlider.getValue() + ",");
//        // System.out.println(mealJSlider.getValue());
//        // Example: Write the selected checkboxes
//        filehandler.writeResults(wheatCheckBox.isSelected() ? "True," : "False,");
//        // System.out.println(wheatCheckBox.isSelected() ? "True," : "False,");
//        filehandler.writeResults(sugarCheckBox.isSelected() ? "True," : "False,");
//        // System.out.println(sugarCheckBox.isSelected() ? "Sugar," : "False,");
//        filehandler.writeResults(dairyCheckBox.isSelected() ? "True," : "False,");
//        // System.out.println(dairyCheckBox.isSelected() ? "True," : "False,");
//
//        // Example: Write the selected walk option
//        filehandler.writeResults((String) walkComboBox.getSelectedItem() + ",");
//        // System.out.println((String) walkComboBox.getSelectedItem() + ",");
//
//        // Example: Write the weight value
//        String weightValue = weightFormattedTextField.getValue() != null
//                ? weightFormattedTextField.getValue().toString()
//                : "null";
//        // System.out.println(weightValue);
//        filehandler.writeResults(weightValue);
//        filehandler.writeResults("\n");
//        // Clear the form after writing to the file
//        clearForm();
//
//        // //System.out.println("Form data saved successfully.");
//    }
//
//    private static String getFormattedTime() {
//        LocalDateTime currentTime = LocalDateTime.now();
//        return currentTime.format(dateTimeFormatter);
//    }
//
//    public static void main(String[] args) {
//        new healthSurvey();
//    }
//}
