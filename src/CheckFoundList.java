import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CheckFoundList extends JFrame {
  
    public CheckFoundList() {
        setTitle("Lost and Found List");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(500, 400);
        setLocationRelativeTo(null);

        JTable table = new JTable();
        JScrollPane scrollPane = new JScrollPane(table);
        getContentPane().add(scrollPane, BorderLayout.CENTER);

        try {
            DefaultTableModel model = readFoundFromFile();
            table.setModel(model);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error reading CSV file: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private DefaultTableModel readFoundFromFile() throws IOException {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("이름");
        model.addColumn("습득 날짜");
        model.addColumn("습득 장소");
        model.addColumn("보관 장소");

        BufferedReader reader = new BufferedReader(new FileReader("FoundList.csv"));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] data = line.split(",");
            model.addRow(data);
        }
        reader.close();

        return model;
    }
}
