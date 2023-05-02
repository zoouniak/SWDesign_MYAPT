import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Login extends JFrame {

    public Login() {
        JPanel imagePanel = new JPanel(new BorderLayout());
        ImageIcon imageIcon = new ImageIcon("src/logo.png");
        JLabel imageLabel = new JLabel(imageIcon);

        imagePanel.add(imageLabel, BorderLayout.NORTH);
        add(imagePanel, BorderLayout.NORTH);

        setTitle("MYAPT LOGIN");

        JPanel title = new JPanel();

        JLabel login = new JLabel("로그인을 해주세요");
        login.setForeground(new Color(5, 0, 153));

        title.add(login);
        JPanel jp1 = new JPanel();
        jp1.setLayout(new GridLayout(3, 2));

        JPanel idPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JLabel jlb1 = new JLabel("아이디 : ", JLabel.CENTER);
        idPanel.add(jlb1);

        JPanel idPanel2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JTextField jtf1 = new JTextField(10);

        idPanel2.add(jtf1);

        jp1.add(idPanel);
        jp1.add(idPanel2);

        JPanel pwdPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JLabel jlb2 = new JLabel("비밀번호 : ", JLabel.CENTER);

        JPanel pwdPanel2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPasswordField jtf2 = new JPasswordField(10);

        pwdPanel.add(jlb2);
        pwdPanel2.add(jtf2);

        jp1.add(pwdPanel);
        jp1.add(pwdPanel2);

        Font font = new Font(null, Font.PLAIN, 16);

        jlb1.setFont(font);
        jlb2.setFont(font);
        jtf1.setFont(font);
        jtf2.setFont(font);

        JPanel loginPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton jLogin = new JButton("로그인");

        JPanel joinPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton join = new JButton("회원가입");
        loginPanel.add(jLogin);
        joinPanel.add(join);

        jp1.add(loginPanel);
        jp1.add(joinPanel);

        JPanel jp2 = new JPanel();
        jp2.setLayout(new FlowLayout());
        jp2.add(jp1);

        setLayout(new BorderLayout());

        add(title, BorderLayout.NORTH);
        add(jp2, BorderLayout.CENTER);

        add(imagePanel, BorderLayout.NORTH);
        setBounds(200, 200, 500, 450);

        setSize(1000, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setVisible(true);
        jLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String enteredUsername = jtf1.getText();
                String enteredPassword = new String(jtf2.getPassword());

                try {
                    boolean loginSuccessful = User.checkLoginInfo(enteredUsername, enteredPassword, "C:\\Users\\오주은\\Desktop\\학교\\소프트웨어설계\\login.csv");

                    if (loginSuccessful) {
                        if (enteredUsername.equals("0")) {
                            Admin admin = new Admin();
                            admin.setVisible(true);
                            dispose();
                        }
                        // Open the ApartmentGUI window
                        else {
                            ChooseApartment apartmentGUI = new ChooseApartment();
                            apartmentGUI.setVisible(true);
                            dispose(); // Close the Login window
                        }
                    } else {
                        JOptionPane.showMessageDialog(Login.this, "해당 로그인 정보가 존재하지 않습니다", "User Failed", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(Login.this, "파일을 읽어들이지 못하였습니다", "User Failed", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        join.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                Signup signup = new Signup();
                signup.setVisible(true);
            }
        });
    }
    public static void main(String[] args){
        Login login = new Login();
        login.setVisible(true);
    }
}
