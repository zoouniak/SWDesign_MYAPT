import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileWriter;
import java.io.IOException;

public class AddApartment extends JFrame {
    private String aptName;
    public AddApartment() {
        setTitle("MYAPT");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(500, 100);

        JPanel mainPanel = new JPanel(new BorderLayout());


        JLabel apartmentLabel = new JLabel("새로 등록할 아파트 이름을 입력해주세요");
        JTextField apartmentField = new JTextField(20);

        JPanel apartmentPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        apartmentPanel.add(apartmentLabel);
        apartmentPanel.add(apartmentField);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton registerButton = new JButton("아파트 등록하기");
        buttonPanel.add(registerButton);

        mainPanel.add(apartmentPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        getContentPane().add(mainPanel);

        registerButton.addActionListener(e -> {
            aptName = apartmentField.getText();
            AddApt(aptName);
        });
    }

    private void AddApt(String apartmentName) {
        if (!apartmentName.equals("")) {
            try {
                FileWriter writer = new FileWriter("AptList.csv", true);
                writer.write(apartmentName + "\n");
                writer.close();
                JOptionPane.showMessageDialog(null, "아파트가 등록되었습니다.");
                setVisible(false);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } else {
            JOptionPane.showMessageDialog(null, "아파트 이름을 입력하세요.");
        }
    }
}
