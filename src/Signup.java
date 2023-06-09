import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.*;

public class Signup extends JFrame {

    private String id;
    private String password;
    private String confirm;
    private String contact;
    public JTextField idField;
    public Signup() {

        setTitle("MYAPT JOIN");

        JPanel titlePanel = new JPanel();
        JLabel title = new JLabel("회원가입");
        title.setFont(new Font(null, Font.BOLD, 20));
        titlePanel.add(title);

        JPanel idPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JLabel idLabel = new JLabel("아이디: ");
        idField = new JTextField(10);
        JButton checkIdButton = new JButton("중복 확인");
        idPanel.add(idLabel);
        idPanel.add(idField);
        idPanel.add(checkIdButton);

        JPanel passwordPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JLabel passwordLabel = new JLabel("비밀번호: ");
        JPasswordField passwordField = new JPasswordField(20);
        passwordPanel.add(passwordLabel);
        passwordPanel.add(passwordField);

        JPanel confirmPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JLabel confirmLabel = new JLabel("비밀번호 확인: ");
        JPasswordField confirmField = new JPasswordField(20);
        confirmPanel.add(confirmLabel);
        confirmPanel.add(confirmField);

        JPanel contactPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JLabel contactLabel = new JLabel("연락처: ");
        JTextField contactArea = new JTextField(20);
        contactPanel.add(contactLabel);
        contactPanel.add(contactArea);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton registerButton = new JButton("회원가입");
        buttonPanel.add(registerButton);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.add(Box.createVerticalStrut(20));
        mainPanel.add(idPanel);
        mainPanel.add(Box.createVerticalStrut(20));
        mainPanel.add(passwordPanel);
        mainPanel.add(Box.createVerticalStrut(20));
        mainPanel.add(confirmPanel);
        mainPanel.add(Box.createVerticalStrut(20));
        mainPanel.add(contactPanel);
        mainPanel.add(Box.createVerticalStrut(20));
        mainPanel.add(buttonPanel);

        setLayout(new BorderLayout());
        add(titlePanel, BorderLayout.NORTH);
        add(mainPanel, BorderLayout.CENTER);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);

        registerButton.addActionListener(e -> {
            id = idField.getText();
            password=new String(passwordField.getPassword());
            confirm=new String(confirmField.getPassword());
            contact=contactArea.getText();
            SignInfoCheck(id,password,confirm,contact);
        });
        checkIdButton.addActionListener(e -> {
            IdDuplicateCheck(idField.getText());
        });
    }

    private boolean checkIdExists(String idToCheck) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\오주은\\Desktop\\학교\\소프트웨어설계\\login.csv"));
            String line;

            while ((line = reader.readLine()) != null) {
                //line = line.substring(1, line.length() - 1);
                String[] columns = line.split(",");
                String id = columns[0];

                if (id.equals(idToCheck)) {
                    return true;
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }

    private void IdDuplicateCheck(String idToCheck) {
        if (idToCheck.isEmpty()) {
            JOptionPane.showMessageDialog(null, "아이디를 입력해주세요.");
            return;
        }
        boolean idExists = checkIdExists(idToCheck); // Replace with your implementation of checking for duplicate IDs

        if (idExists) {
            JOptionPane.showMessageDialog(null, "중복된 아이디입니다.");
            idField.setText("");//idField 초기화
            idField.requestFocusInWindow();//idField로 커서이동
        } else if (idToCheck.length() < 5) {
            JOptionPane.showMessageDialog(null, "아이디는 5글자 이상이여야 합니다. 다시 입력해주세요.");
            idField.setText("");//idField 초기화
            idField.requestFocusInWindow();//idField로 커서이동
        } else {
            JOptionPane.showMessageDialog(null, "해당 ID 사용이 가능합니다: " + idToCheck);
        }
    }

    private void SignInfoCheck(String id,String password,String confirm,String contact ) {

        // Validate input and process registration
        if (id.isEmpty() || password.isEmpty() || confirm.isEmpty() || contact.isEmpty()) {
            //모든 필드가 입력되지 않았을 때
            JOptionPane.showMessageDialog(null, "모든 항목을 입력해주세요");
        } else if ((password.length() < 8)) {
            //비밀번호가 8자리 이하일 때
            JOptionPane.showMessageDialog(null, "비밀번호는 8자리 이상이여야 합니다");
        } else if (!password.equals(confirm)) {
            //비밀번호와 비밀번호 확인이 동일하지 않을 때
            JOptionPane.showMessageDialog(null, "비밀번호가 동일하지 않습니다. 다시 확인해주세요.");
        } else {
            try {
                //csv파일에 아이디 비밀번호 저장
                PrintWriter writer = new PrintWriter(new FileWriter("C:\\Users\\오주은\\Desktop\\학교\\소프트웨어설계\\login.csv", true));
                writer.println(id + "," + password);
                writer.close();
                JOptionPane.showMessageDialog(null, "회원가입이 완료되었습니다.");
                setVisible(false);
                Login login = new Login();
                login.setVisible(true);//로그인 창 다시 실행
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}