import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Admin extends JFrame {

    public Admin() {
        // Create and configure window
        setTitle("MYAPT");
        setSize(700, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Create components
        JLabel titleLabel = new JLabel("관리자 권한 기능입니다. 사용할 기능을 선택하세요");
        titleLabel.setFont(new Font("굴림체", Font.BOLD, 20));

        JButton registerApartmentButton = new JButton("아파트 등록");
        JButton registerFacilityButton = new JButton("편의시설 등록");
        JButton registerNoticeButton = new JButton("공지사항 등록");
        JButton deleteNoticeButton = new JButton("공지사항 삭제");

        // Add components to content pane
        Container contentPane = getContentPane();
        contentPane.setLayout(new GridLayout(5, 1));

        JPanel panel1 = new JPanel();
        panel1.add(titleLabel);
        contentPane.add(panel1);

        JPanel panel2 = new JPanel();
        panel2.add(registerApartmentButton);
        contentPane.add(panel2);

        JPanel panel3 = new JPanel();
        panel3.add(registerFacilityButton);
        contentPane.add(panel3);

        JPanel panel4 = new JPanel();
        panel4.add(registerNoticeButton);
        contentPane.add(panel4);

        JPanel panel5 = new JPanel();
        panel5.add(deleteNoticeButton);
        contentPane.add(panel5);

        // Add button listeners
        registerApartmentButton.addActionListener(e -> {
            openAddApt();
        });

        registerFacilityButton.addActionListener(e -> {
            openAddFacility();
        });

        registerNoticeButton.addActionListener(e -> {
            openAddNotice();
        });

        deleteNoticeButton.addActionListener(e -> {
            openDeleteNotice();
        });
    }

    private void openAddApt() {
        AddApartment addApartment = new AddApartment();
        addApartment.setVisible(true);
    }

    private void openAddFacility() {
        AddFacility addFacility = new AddFacility();
        addFacility.setVisible(true);
    }

    private void openAddNotice() {
        AddNotice addNotice = new AddNotice();
        addNotice.setVisible(true);
    }

    private void openDeleteNotice() {
        DeleteNotice deleteNotice = new DeleteNotice();
        deleteNotice.setVisible(true);
    }
}
