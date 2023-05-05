import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChooseApartment extends JFrame {
    private static final long serialVersionUID = 1L;

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

        // Create a Choice component and add the options
        String[] apartmentOptions = {"선택", "힐스테이트", "롯데캐슬", "화성파크드림"};
        JComboBox<String> apartmentChoice = new JComboBox<>(apartmentOptions);
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

        selectButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Get the selected item from the Choice component
                String selectedItem = (String) apartmentChoice.getSelectedItem();

                // Check if an apartment was selected
                if (selectedItem.equals("선택")) {
                    JOptionPane.showMessageDialog(null, "아파트를 선택하세요");
                } else {
                    //JOptionPane.showMessageDialog(null, "You selected: " + selectedItem);
                    dispose();
                    ChooseService facilityGUI = new ChooseService();
                    facilityGUI.setVisible(true);
                }
            }
        });

        setVisible(true);
    }
}
