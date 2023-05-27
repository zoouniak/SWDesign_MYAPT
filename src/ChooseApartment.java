import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ChooseApartment extends JFrame {
    List<String> apartmentNames;
    JComboBox<String> apartmentChoice;

    public ChooseApartment() {
        // Set up the frame
        super("MYAPT");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 150);

        // Create a label for the message and add it to the top of the frame
        JLabel messageLabel = new JLabel("거주하고 있는 아파트를 선택하세요");
        messageLabel.setFont(new Font(null, Font.BOLD, 20));
        messageLabel.setHorizontalAlignment(JLabel.CENTER);
        add(messageLabel, BorderLayout.NORTH);

        // Create a panel for the choice component and add it to the frame
        Panel choicePanel = new Panel();
        add(choicePanel, BorderLayout.CENTER);

        GetApt();
// Create an array to hold the apartment options
        String[] apartmentOptions = new String[apartmentNames.size() + 1];

// Add the "선택" option to the first index of the array
        apartmentOptions[0] = "선택";


// Loop through the apartment names and add them to the array
        for (int i = 0; i < apartmentNames.size(); i++) {
            apartmentOptions[i + 1] = apartmentNames.get(i);
        }

// Create the JComboBox with the apartment options
        apartmentChoice = new JComboBox<>(apartmentOptions);
        apartmentChoice.setSelectedIndex(0);

        // Add the Choice component to the panel
        choicePanel.add(apartmentChoice);

        // Create a label for the "select apartment" message and add it to the panel
        Panel messagePanel = new Panel();
        add(messagePanel, BorderLayout.SOUTH);

        Font font = new Font(null, Font.PLAIN, 16);
        JButton selectButton = new JButton("선택");
        selectButton.setFont(font);
        messagePanel.add(selectButton);

        selectButton.addActionListener((ActionListener) e -> {
            String selectedItem = (String) apartmentChoice.getSelectedItem();
            select(selectedItem);
        });

        setVisible(true);
    }

    private void GetApt() {
        // Create a list to hold the apartment names
        apartmentNames = new ArrayList<>();

        // Create a new File object with the file path
        File file = new File("C:\\Users\\오주은\\Desktop\\학교\\소프트웨어설계\\AptList.csv");

        // Create a new BufferedReader to read the file
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;

            // Read each line of the file
            while ((line = reader.readLine()) != null) {
                // Split the line into an array of apartment names
                String[] apartmentRow = line.split(",");

                // Add each apartment name to the list
                for (String apartmentName : apartmentRow) {
                    apartmentNames.add(apartmentName.trim());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void select(String selectedItem) {
        // Get the selected item from the Choice component
        // Check if an apartment was selected
        if (selectedItem.equals("선택")) {
            JOptionPane.showMessageDialog(null, "아파트를 선택하세요");
        } else {
            dispose();
            ChooseService facilityGUI = new ChooseService();
            facilityGUI.setVisible(true);
        }
    }
}
