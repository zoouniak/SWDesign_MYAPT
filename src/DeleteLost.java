import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class DeleteLost extends JFrame {
    private JComboBox<String> foundItemsComboBox;
    public DeleteLost() {
        super("MYAPT");
        setSize(500, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Create main panel and set layout
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Create combo box panel and load found items from csv file
        JPanel comboBoxPanel = new JPanel(new FlowLayout());
        JLabel comboBoxLabel = new JLabel("습득물:");
        foundItemsComboBox = new JComboBox<>();
        loadFoundItems();
        comboBoxPanel.add(comboBoxLabel);
        comboBoxPanel.add(foundItemsComboBox);
        mainPanel.add(comboBoxPanel, BorderLayout.NORTH);

        // Create delete button panel
        JPanel deleteButtonPanel = new JPanel(new FlowLayout());
        JButton deleteButton = new JButton("삭제");
        deleteButtonPanel.add(deleteButton);
        mainPanel.add(deleteButtonPanel, BorderLayout.SOUTH);

        // Add main panel to frame
        add(mainPanel);

        // Set border for main panel
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Add action listener to delete button
        deleteButton.addActionListener(e -> {
            // Get the selected item from the combo box
            String selectedItem = (String) foundItemsComboBox.getSelectedItem();
            // Delete the selected item from the csv file
            deleteFoundItem(selectedItem);
            // Reload the combo box with the updated list of found items
            loadFoundItems();
            // Display a success message
            //JOptionPane.showMessageDialog(null, "선택한 습득물이 삭제되었습니다.");
        });
    }

    // Load found items from csv file and populate the combo box
    private void loadFoundItems() {
        foundItemsComboBox.removeAllItems(); // Clear the combo box
        try {
            BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\오주은\\Desktop\\학교\\소프트웨어설계\\FoundList.csv"));
            String line = reader.readLine(); // Skip the header line
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String itemName = parts[0];
                foundItemsComboBox.addItem(itemName);
            }
            reader.close();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "습득물 목록을 불러오는 도중 오류가 발생하였습니다.");
        }
    }

    private void deleteFoundItem(String selectedFoundItem) {
        try {
            File inputFile = new File("C:\\Users\\오주은\\Desktop\\학교\\소프트웨어설계\\FoundList.csv");
            File tempFile = new File("C:\\Users\\오주은\\Desktop\\학교\\소프트웨어설계\\Foundtemp.csv");

            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

            String lineToRemove = selectedFoundItem;
            String currentLine;

            while ((currentLine = reader.readLine()) != null) {
                String[] parts = currentLine.split(",");
                String itemName = parts[0];

                if (!itemName.equals(lineToRemove)) {
                    writer.write(currentLine + System.getProperty("line.separator"));
                }

            }
            inputFile.renameTo(new File("C:\\Users\\오주은\\Desktop\\학교\\소프트웨어설계\\fortemp.csv"));
            tempFile.renameTo(new File("C:\\Users\\오주은\\Desktop\\학교\\소프트웨어설계\\FoundList.csv"));
            writer.close();
            reader.close();



            JOptionPane.showMessageDialog(null, "분실물이 삭제되었습니다.");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "오류가 발생했습니다.");
            ex.printStackTrace();
        }
        dispose();
    }
    public static void main(String[] args){
        DeleteLost deleteLost = new DeleteLost();
        deleteLost.setVisible(true);
    }
}

