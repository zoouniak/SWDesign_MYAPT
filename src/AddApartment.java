import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileWriter;
import java.io.IOException;

public class AddApartment extends JFrame {
    private JLabel apartmentLabel;
    private JTextField apartmentField;
    private JButton registerButton;

    public AddApartment() {
        setTitle("MYAPT");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(500, 100);

        JPanel mainPanel = new JPanel(new BorderLayout());


        apartmentLabel = new JLabel("새로 등록할 아파트 이름을 입력해주세요");
        apartmentField = new JTextField(20);

        JPanel apartmentPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        apartmentPanel.add(apartmentLabel);
        apartmentPanel.add(apartmentField);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        registerButton = new JButton("아파트 등록하기");
        buttonPanel.add(registerButton);

        mainPanel.add(apartmentPanel,BorderLayout.CENTER);
        mainPanel.add(buttonPanel,BorderLayout.SOUTH);
        getContentPane().add(mainPanel);
        //setVisible(true);

        registerButton.addActionListener(new ActionListener() {
            String apartmentName = apartmentField.getText();

            @Override
            public void actionPerformed(ActionEvent e) {
                if (!apartmentName.equals("")) {
                    try {
                        FileWriter writer = new FileWriter("C:\\Users\\오주은\\Desktop\\학교\\소프트웨어설계\\AptList.csv", true);
                        writer.write(apartmentName + "\n");
                        writer.close();
                        JOptionPane.showMessageDialog(null, "아파트가 등록되었습니다.");
                        apartmentField.setText("");
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "아파트 이름을 입력하세요.");
                }
            }
        });
    }

}
