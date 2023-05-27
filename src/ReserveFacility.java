import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class ReserveFacility extends JFrame {

    private JComboBox<String> FacilityCombo;
    private JComboBox<String> timeCombo;
    private JTextField dateField;
    private JTextField nameField;
    private JTextField contactField;

    public ReserveFacility() {
        // Set up the frame
        super("MYAPT");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Create the panel and set the layout
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 2, 10, 10));

        // Create the components
        JLabel FacilityLabel = new JLabel("편의시설명:");
        JLabel dateLabel = new JLabel("이용하실 날짜:");
        JLabel timeLabel = new JLabel("이용 시간:");
        JLabel nameLabel = new JLabel("성함:");
        JLabel contactLabel = new JLabel("연락처:");
        FacilityCombo = new JComboBox<>(new String[]{"헬스장", "축구장", "농구장", "독서실"});
        dateField = new JTextField();
        timeCombo = new JComboBox<>(new String[]{"9:00 - 10:00", "10:00 - 11:00", "11:00 - 12:00", "12:00 - 13:00", "13:00 - 14:00", "14:00 - 15:00", "15:00 - 16:00", "16:00 - 17:00", "17:00 - 18:00"});
        nameField = new JTextField();
        contactField = new JTextField();
        JButton reserveButton = new JButton("예약하기");

        // Add the components to the panel
        panel.add(FacilityLabel);
        panel.add(FacilityCombo);
        panel.add(dateLabel);
        panel.add(dateField);
        panel.add(timeLabel);
        panel.add(timeCombo);
        panel.add(nameLabel);
        panel.add(nameField);
        panel.add(contactLabel);
        panel.add(contactField);
        panel.add(new JLabel());
        panel.add(reserveButton);

        // Add an action listener to the reserve button
        reserveButton.addActionListener(e -> {
            checkReservceInfo();
        });
        // Add the panel to the frame
        add(panel);

    }

    private void saveReservation(String facility, String date, String time, String name, String contact) throws IOException {
        FileWriter writer = new FileWriter("C:\\Users\\오주은\\Desktop\\학교\\소프트웨어설계\\reservation.csv", true);
        PrintWriter printer = new PrintWriter(writer);
        printer.print(facility);
        printer.print(",");
        printer.print(date);
        printer.print(",");
        printer.print(time);
        printer.print(",");
        printer.print(name);
        printer.print(",");
        printer.print(contact);
        printer.println();
        printer.close();
        writer.close();
    }

    private void checkReservceInfo() {
        String Facility = (String) FacilityCombo.getSelectedItem();
        String date = dateField.getText();
        String time = (String) timeCombo.getSelectedItem();
        String name = nameField.getText();
        String contact = contactField.getText();

        if (Facility.isEmpty() || date.isEmpty() || time.isEmpty() || name.isEmpty() || contact.isEmpty()) {
            JOptionPane.showMessageDialog(null, "모든 항목을 입력해주세요");
        } else if (!date.matches("\\d{4}/\\d{2}/\\d{2}")) {
            JOptionPane.showMessageDialog(null, "날짜 입력 양식(YYYY/MM/DD)에 맞게 입력해주세요.");
            return;
        } else if (!contact.matches("\\d{3}-\\d{4}-\\d{4}")) {
            JOptionPane.showMessageDialog(null, "연락처 입력 양식(000-0000-0000)에 맞게 입력해주세요.");
        } else {
            // Show a confirmation message
            try {
                saveReservation(Facility, date, time, name, contact);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            JOptionPane.showMessageDialog(ReserveFacility.this, "예약이 완료되었습니다!", "예약 완료", JOptionPane.INFORMATION_MESSAGE);
            setVisible(false);
        }
    }
}
