import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ReserveFacility extends JFrame {
    private String facility;
    private String date;
    private String time;
    private String name;
    private String contact;
    List<String> facilities;
    private String SelectedFacility;

    public ReserveFacility() {
        // Set up the frame
        super("MYAPT");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create the panel and set the layout
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 2, 10, 10));

        Getfacility();
        String[] FacilityOptions = new String[facilities.size() ];
        for (int i = 0; i < facilities.size(); i++) {
            FacilityOptions[i ] = facilities.get(i);
        }
        JComboBox<String> facilityChoice = new JComboBox<>(FacilityOptions);
        // Create the components
        JLabel FacilityLabel = new JLabel("편의시설명:");
        JLabel dateLabel = new JLabel("이용하실 날짜:");
        JLabel timeLabel = new JLabel("이용 시간:");
        JLabel nameLabel = new JLabel("성함:");
        JLabel contactLabel = new JLabel("연락처:");
       // JComboBox<String> FacilityCombo = new JComboBox<>(new String[]{"헬스장", "축구장", "농구장", "독서실"});
        JTextField dateField = new JTextField();
        JComboBox<String> timeCombo = new JComboBox<>(new String[]{"9:00 - 10:00", "10:00 - 11:00", "11:00 - 12:00", "12:00 - 13:00", "13:00 - 14:00", "14:00 - 15:00", "15:00 - 16:00", "16:00 - 17:00", "17:00 - 18:00"});
        JTextField nameField = new JTextField();
        JTextField contactField = new JTextField();
        JButton reserveButton = new JButton("예약하기");

        // Add the components to the panel
        panel.add(FacilityLabel);
        panel.add(facilityChoice);
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
            facility = (String) facilityChoice.getSelectedItem();
            date = dateField.getText();
            time = (String) timeCombo.getSelectedItem();
            name = nameField.getText();
            contact = contactField.getText();
            checkReservceInfo(facility,date,time,name,contact);
        });
        // Add the panel to the frame
        add(panel);

    }
    public void Getfacility() {
        // Create a list to hold the apartment names
        facilities = new ArrayList<>();

        // Create a new File object with the file path
        File file = new File("facilityList.csv");

        // Create a new BufferedReader to read the file
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;

            // Read each line of the file
            while ((line = reader.readLine()) != null) {
                // Split the line into an array of apartment names
                String[] apartmentRow = line.split(",");

                // Add each apartment name to the list
                for (String facilityname : apartmentRow) {
                    facilities.add(facilityname.trim());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveReservation(String facility, String date, String time, String name, String contact) throws IOException {
        FileWriter writer = new FileWriter("reservation.csv", true);
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

    private void checkReservceInfo(String facility, String date, String time, String name, String contact) {

        if (facility.isEmpty() || date.isEmpty() || time.isEmpty() || name.isEmpty() || contact.isEmpty()) {
            JOptionPane.showMessageDialog(null, "모든 항목을 입력해주세요");
        } else if (!date.matches("\\d{4}/\\d{2}/\\d{2}")) {
            JOptionPane.showMessageDialog(null, "날짜 입력 양식(YYYY/MM/DD)에 맞게 입력해주세요.");
            return;
        } else if (!contact.matches("\\d{3}-\\d{4}-\\d{4}")) {
            JOptionPane.showMessageDialog(null, "연락처 입력 양식(000-0000-0000)에 맞게 입력해주세요.");
        } else {
            // Show a confirmation message
            try {
                saveReservation(facility, date, time, name, contact);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            JOptionPane.showMessageDialog(ReserveFacility.this, "예약이 완료되었습니다!", "예약 완료", JOptionPane.INFORMATION_MESSAGE);
            setVisible(false);
        }
    }
}
