import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class CancelReservation extends JFrame  {

    private JComboBox<String> reservationComboBox;

    public CancelReservation() {
        // Set up the frame
        super("MYAPT");
        setSize(700, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Set up the panel
        JPanel panel = new JPanel(new GridLayout(3, 1));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Add a label to the panel
        JLabel label = new JLabel("취소하실 예약을 선택해주세요:");
        panel.add(label);

        // Add a combo box to the panel
        reservationComboBox = new JComboBox<>();
        panel.add(reservationComboBox);

        // Read the reservations from the file and add them to the combo box
        readReservationsFromFile();

        // Add a button to the panel
        JButton cancelButton = new JButton("예약 취소하기");
        //cancelButton.addActionListener(this);
        cancelButton.addActionListener(e -> {
            String selectedReservation = (String) reservationComboBox.getSelectedItem();
            deleteFromFile(selectedReservation);
        });
        panel.add(cancelButton);

        // Add the panel to the frame
        add(panel);

        // Show the frame
        setVisible(true);
    }

    private void readReservationsFromFile() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\오주은\\Desktop\\학교\\소프트웨어설계\\reservation.csv"));
            String line = reader.readLine();
            while (line != null) {
                String[] fields = line.split(",");
                if (fields.length == 5) {
                    reservationComboBox.addItem(line);
                }
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    private void deleteFromFile(String selectedReservation){


            // Remove the selected reservation from the combo box
            reservationComboBox.removeItem(selectedReservation);

            // Remove the selected reservation from the file
            try {
                BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\오주은\\Desktop\\학교\\소프트웨어설계\\reservation.csv"));
                BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\Users\\오주은\\Desktop\\학교\\소프트웨어설계\\temp.csv"));
                String line = reader.readLine();
                while (line != null) {
                    if (!line.equals(selectedReservation)) {
                        writer.write(line);
                        writer.newLine();
                    }
                    line = reader.readLine();
                }
                reader.close();
                writer.close();
                File oldFile = new File("C:\\Users\\오주은\\Desktop\\학교\\소프트웨어설계\\reservation.csv");
                oldFile.delete();
                File newFile = new File("C:\\Users\\오주은\\Desktop\\학교\\소프트웨어설계\\temp.csv");
                newFile.renameTo(oldFile);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }

            // Show a message dialog
            JOptionPane.showMessageDialog(this, "Reservation cancelled successfully.");
        }
    }
