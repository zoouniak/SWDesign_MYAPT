import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class AddNotice extends JFrame {
    private JTextArea NoticeField;
    private JButton registerButton;

    public AddNotice() {
        setSize(700, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel mainPanel = new JPanel(new BorderLayout());

        JLabel NoticeLabel = new JLabel("등록할 공지사항을 입력하세요");
        NoticeLabel.setFont(new Font("굴림", Font.PLAIN, 35));
        NoticeLabel.setHorizontalAlignment(JLabel.CENTER);

        NoticeField = new JTextArea();
        NoticeField.setLineWrap(true);
      // NoticeField.setWrapStyleWord(true);

        mainPanel.add(NoticeLabel, BorderLayout.NORTH);
        mainPanel.add(NoticeField, BorderLayout.CENTER);

        registerButton = new JButton("등록하기");

        mainPanel.add(registerButton, BorderLayout.SOUTH);

        add(mainPanel);
        setVisible(true);

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String notice = NoticeField.getText();
                if (!notice.isEmpty()) {
                    try {
                        BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\Users\\오주은\\Desktop\\학교\\소프트웨어설계\\Notice.csv", true));
                        writer.write(notice + "\n");
                        writer.close();
                        JOptionPane.showMessageDialog(null, "공지사항이 성공적으로 등록되었습니다.");
                        NoticeField.setText("");
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(null, "Error saving notice to file");
                        ex.printStackTrace();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "공지사항을 입력해주세요");
                }
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        new AddNotice();
    }
}
