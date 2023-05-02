import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class RegisterFound extends JFrame {
    private JTextField nameField;
    private JTextField dateField;
    private JTextField locationField;
    private JTextField storageField;
    private JButton photoButton;
    private JButton registerButton;

    public RegisterFound() {
        super("MYAPT");
        setTitle("분실물 등록");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Create main panel and set layout
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(8, 1));

        JPanel titlePanel = new JPanel(new FlowLayout());
        JLabel titleLabel = new JLabel("분실물 등록");
        titleLabel.setFont(new Font(null, Font.BOLD, 40));
        titlePanel.add(titleLabel);
        mainPanel.add(titlePanel, BorderLayout.NORTH);
        // Create top panel for name label and text field
        JPanel namePanel = new JPanel(new FlowLayout());
        JLabel nameLabel = new JLabel("분실물 이름:");
        nameField = new JTextField();
        nameField.setPreferredSize(new Dimension(400, 30));
        namePanel.add(nameLabel);
        namePanel.add(nameField);
        mainPanel.add(namePanel, BorderLayout.NORTH);

        // Create middle panel for date label and text field
        JPanel datePanel = new JPanel(new FlowLayout());
        JLabel dateLabel = new JLabel("습득 날짜:");
        dateField = new JTextField();
        dateField.setPreferredSize(new Dimension(400, 30));
        datePanel.add(dateLabel);
        datePanel.add(dateField);
        mainPanel.add(datePanel, BorderLayout.CENTER);

        // Create location panel for location label and text field
        JPanel locationPanel = new JPanel(new FlowLayout());
        JLabel locationLabel = new JLabel("습득 장소:");
        locationField = new JTextField();
        locationField.setPreferredSize(new Dimension(400, 30));
        locationPanel.add(locationLabel);
        locationPanel.add(locationField);
        mainPanel.add(locationPanel, BorderLayout.CENTER);

        // Create storage panel for storage label and text field
        JPanel storagePanel = new JPanel(new FlowLayout());
        JLabel storageLabel = new JLabel("보관 장소:");
        storageField = new JTextField();
        storageField.setPreferredSize(new Dimension(400, 30));
        storagePanel.add(storageLabel);
        storagePanel.add(storageField);
        mainPanel.add(storagePanel, BorderLayout.CENTER);

        // Create photo panel for photo label and button
        JPanel photoPanel = new JPanel(new FlowLayout());
        JLabel photoLabel = new JLabel("사진:");
        photoButton = new JButton("Select Photo");
        photoPanel.add(photoLabel);
        photoPanel.add(photoButton);
        mainPanel.add(photoPanel, BorderLayout.CENTER);

        // Create bottom panel for register button
        JPanel registerPanel = new JPanel(new FlowLayout());
        registerButton = new JButton("Register");
        registerPanel.add(registerButton);
        mainPanel.add(registerPanel, BorderLayout.SOUTH);

        // Add main panel to frame
        add(mainPanel);

        // Set border for main panel
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        photoButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int result = fileChooser.showOpenDialog(RegisterFound.this);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    // TODO: Add code to handle the selected file
                    ImageIcon imageIcon;
                    imageIcon = new ImageIcon(selectedFile.getPath());
                    photoLabel.setPreferredSize(new Dimension(200, 200));
                    photoLabel.setIcon(imageIcon);
                }
                photoPanel.add(photoLabel, BorderLayout.EAST);
            }
        });

        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Get the values from the text fields
                String name = nameField.getText();
                String date = dateField.getText();
                String location = locationField.getText();
                String storage = storageField.getText();

                // Check that all fields have been filled out
                if (name.isEmpty() || date.isEmpty() || location.isEmpty() || storage.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "모든 항목을 입력해주세요.");
                    return;
                } else if (date.matches("\\d{4}/\\d{2}/\\d{2}")) {
                    JOptionPane.showMessageDialog(null, "날짜 입력 양식(YYYY/MM/DD)에 맞게 입력해주세요.");
                }
                else {
                    try {
                        FileWriter fw = new FileWriter("C:\\Users\\오주은\\Desktop\\학교\\소프트웨어설계\\FoundList.csv", true);
                        BufferedWriter bw = new BufferedWriter(fw);

                        String data = name + "," + date + "," + location + "," + storage;
                        bw.write(data);
                        bw.newLine();
                        bw.close();

                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }

                    // Display a success message
                    JOptionPane.showMessageDialog(null, "습득물 등록이 완료되었습니다.");

                    // Clear the text fields
                    nameField.setText("");
                    dateField.setText("");
                    locationField.setText("");
                    storageField.setText("");
                }
            }
        });
    }

}
