import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;

public class AddFacility extends JFrame {
    private JLabel facilityLabel;

    private JTextField facilityField;
    private JButton registerButton;

    public AddFacility() {
        setTitle("Add Facility");
        setSize(500, 100);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel mainPanel = new JPanel(new BorderLayout());

        facilityLabel = new JLabel("등록할 편의시설명을 입력해주세요");
        facilityField = new JTextField(10);

        JPanel facilityPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        facilityPanel.add(facilityLabel);
        facilityPanel.add(facilityField);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        registerButton = new JButton("등록하기");
        buttonPanel.add(registerButton);

        mainPanel.add(facilityPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        getContentPane().add(mainPanel);

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String facility = facilityField.getText();
                try {
                    FileWriter fw = new FileWriter("facility.csv", true);
                    BufferedWriter bw = new BufferedWriter(fw);
                    PrintWriter pw = new PrintWriter(bw);

                    pw.println(facility);

                    pw.close();
                    bw.close();
                    fw.close();

                    JOptionPane.showMessageDialog(null, "편의시설 등록이 완료되었습니다!");
                    facilityField.setText("");
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "Error Occurred!");
                }
            }
        });
    }
}
