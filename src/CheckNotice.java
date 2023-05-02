import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;

public class CheckNotice extends JFrame {

    private JTextArea noticeArea;

    public CheckNotice() {
        super("MYAPT");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // create UI components
        JLabel titleLabel = new JLabel("Notices:");
        noticeArea = new JTextArea(10, 30);
        noticeArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(noticeArea);
        JButton loadButton = new JButton("Load Notices");
        loadButton.addActionListener(e -> loadNotices());

        // add components to content pane
        Container c = getContentPane();
        c.setLayout(new BorderLayout());
        c.add(titleLabel, BorderLayout.NORTH);
        c.add(scrollPane, BorderLayout.CENTER);
        c.add(loadButton, BorderLayout.SOUTH);

        // set window properties
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void loadNotices() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\오주은\\Desktop\\학교\\소프트웨어설계\\Notice.csv"));
            ArrayList<String> notices = new ArrayList<>();

            String line = reader.readLine();
            while (line != null) {
                notices.add(line);
                line = reader.readLine();
            }

            reader.close();

            // display notices in the text area
            noticeArea.setText("");
            for (String notice : notices) {
                noticeArea.append(notice + "\n");
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error loading notices: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

}
