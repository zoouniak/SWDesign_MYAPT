import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class DeleteNotice extends JFrame {
    private JCheckBox[] checkBoxes;
    private ArrayList<String> notices;

    public DeleteNotice() {
        super("MYAPT");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Load notices from file
        notices = readFromFile();

        // Create check boxes
        checkBoxes = new JCheckBox[notices.size()];
        JPanel checkBoxPanel = new JPanel(new GridLayout(notices.size(), 1));
        for (int i = 0; i < notices.size(); i++) {
            checkBoxes[i] = new JCheckBox(notices.get(i));
            checkBoxPanel.add(checkBoxes[i]);
        }

        // Create delete button
        JButton deleteButton = new JButton("Delete");
        deleteButton.addActionListener(e -> {
            deleteFromFile();
        });

        // Add components to the frame
        getContentPane().add(checkBoxPanel, BorderLayout.CENTER);
        getContentPane().add(deleteButton, BorderLayout.SOUTH);

        setVisible(true);
    }

    private ArrayList<String> readFromFile() {
        ArrayList<String> notices = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File("Notice.csv"))) {
            while (scanner.hasNextLine()) {
                notices.add(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Notice.csv not found");
        }
        return notices;
    }

    private void deleteFromFile() {
        try (PrintWriter writer = new PrintWriter(new File("Notice.csv"))) {
            for (int i = 0; i < checkBoxes.length; i++) {
                if (!checkBoxes[i].isSelected()) {
                    writer.println(notices.get(i));
                }
            }
            JOptionPane.showMessageDialog(null, "Notices deleted successfully");
            dispose();
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Notice.csv not found");
        }
    }
}
