import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChooseService extends JFrame {

    public ChooseService() {
        super("MYAPT");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        // Add label at the top
        JLabel titleLabel = new JLabel("아파트 관리 시스템");
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2;
        c.insets = new Insets(20, 0, 20, 0);
        panel.add(titleLabel, c);

        // Add reservation button
        JButton reservationButton = new JButton("편의시설 예약하기");
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 1;
        c.insets = new Insets(10, 50, 10, 20);
        panel.add(reservationButton, c);

        // Add cancellation button
        JButton cancellationButton = new JButton("편의시설 예약 취소하기");
        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 1;
        c.insets = new Insets(10, 50, 10, 20);
        panel.add(cancellationButton, c);

        // Add status inquiry button
        JButton reservationStatusButton = new JButton("편의시설 예약 현황");
        c.gridx = 0;
        c.gridy = 3;
        c.gridwidth = 1;
        c.insets = new Insets(10, 50, 10, 20);
        panel.add(reservationStatusButton, c);

        // Add registration button
        JButton registerLostAndFoundButton = new JButton("습득물 등록");
        c.gridx = 1;
        c.gridy = 1;
        c.gridwidth = 1;
        c.insets = new Insets(10, 20, 10, 50);
        panel.add(registerLostAndFoundButton, c);

        // Add deletion button
        JButton deletionButton = new JButton("분실물 삭제");
        c.gridx = 1;
        c.gridy = 2;
        c.gridwidth = 1;
        c.insets = new Insets(10, 20, 10, 50);
        panel.add(deletionButton, c);

        JButton CheckNotice = new JButton("공지사항 확인");
        c.gridx = 1;
        c.gridy = 3;
        c.gridwidth = 1;
        c.insets = new Insets(10, 20, 10, 50);
        panel.add(CheckNotice, c);

        getContentPane().add(panel);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);

        reservationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ReserveFacility reserveFacility = new ReserveFacility();
                reserveFacility.setVisible(true);
            }
        });
        cancellationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CancelReservation cancelReservation = new CancelReservation();
                cancelReservation.setVisible(true);
            }
        });
        reservationStatusButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ReservationStatus reservationStatus = new ReservationStatus();
                reservationStatus.setVisible(true);
            }
        });
        registerLostAndFoundButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RegisterFound registrationGUI = new RegisterFound();
                registrationGUI.setVisible(true);
            }
        });
        deletionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DeleteLost deleteLost = new DeleteLost();
                deleteLost.setVisible(true);
            }
        });
        CheckNotice.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CheckNotice checkNotice = new CheckNotice();
                checkNotice.setVisible(true);
            }
        });

    }
}