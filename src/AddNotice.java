import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class AddNotice extends JFrame {
    private String notice;

    public AddNotice() {
        super("MYAPT");
        setSize(700, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel mainPanel = new JPanel(new BorderLayout());

        JLabel NoticeLabel = new JLabel("등록할 공지사항을 입력하세요");
        NoticeLabel.setFont(new Font("굴림", Font.PLAIN, 35));
        NoticeLabel.setHorizontalAlignment(JLabel.CENTER);

        JTextArea NoticeField = new JTextArea();
        NoticeField.setLineWrap(true);
      // NoticeField.setWrapStyleWord(true);

        mainPanel.add(NoticeLabel, BorderLayout.NORTH);
        mainPanel.add(NoticeField, BorderLayout.CENTER);

        JButton registerButton = new JButton("등록하기");

        mainPanel.add(registerButton, BorderLayout.SOUTH);

        add(mainPanel);
        setVisible(true);

        registerButton.addActionListener(e -> {
            notice = NoticeField.getText();
            addNotice(notice);
        });

        setVisible(true);
    }

    private void addNotice(String notice) {
        if (!notice.isEmpty()) {
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter("Notice.csv", true));
                writer.write(notice + "\n");
                writer.close();
                JOptionPane.showMessageDialog(null, "공지사항이 성공적으로 등록되었습니다.");
                setVisible(false);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Error saving notice to file");
                ex.printStackTrace();
            }
        } else {
            JOptionPane.showMessageDialog(null, "공지사항을 입력해주세요");
        }
    }
}
