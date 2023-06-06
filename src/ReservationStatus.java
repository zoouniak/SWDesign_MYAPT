import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ReservationStatus extends JFrame {
    public ReservationStatus() {
        super("MYAPT");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Add a label to the top of the frame
        JLabel titleLabel = new JLabel("편의시설 예약 현황");
        titleLabel.setFont(new Font("굴림체", Font.BOLD, 30));
        add(titleLabel, BorderLayout.NORTH);


        // Create the table
        DefaultTableModel tableModel = new DefaultTableModel(new String[]{"편의시설명", "예약날짜", "예약시간", "성함", "연락처"}, 0);
        JTable table = new JTable(tableModel);

        // Add the table to the frame
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // Read the reservations from the file and add them to the table
        ArrayList<String[]> reservations = readReservationsFromFile();
        for (String[] reservation : reservations) {
            tableModel.addRow(reservation);
        }

        JButton goToReservationButton = new JButton("편의시설 예약하기");
        goToReservationButton.addActionListener(e -> {
            openReserveFacility();
        });
        add(goToReservationButton, BorderLayout.SOUTH);
        // Show the frame
        setVisible(true);
    }

    private void openReserveFacility() {
        ReserveFacility reserveFacility = new ReserveFacility();
        reserveFacility.setVisible(true);
    }

    private ArrayList<String[]> readReservationsFromFile() {
        ArrayList<String[]> reservations = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\오주은\\Desktop\\학교\\소프트웨어설계\\reservation.csv"));
            String line = reader.readLine();
            while (line != null) {
                String[] fields = line.split(",");
                if (fields.length == 5) {
                    reservations.add(fields);
                }
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        return reservations;
    }
}
