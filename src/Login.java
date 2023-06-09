import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Login extends JFrame{
    private String id;
    private String password;
    public Login() {
        JPanel imagePanel = new JPanel(new BorderLayout());
        ImageIcon imageIcon = new ImageIcon("src/logo.png");
        JLabel imageLabel = new JLabel(imageIcon);

        imagePanel.add(imageLabel, BorderLayout.NORTH);
        add(imagePanel, BorderLayout.NORTH);

        setTitle("MYAPT LOGIN");

        JPanel title = new JPanel();

        JLabel login = new JLabel("로그인을 해주세요");
        login.setForeground(new Color(0, 20, 153));

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
        join.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openSignup();
            }
        });
        jLogin.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                id = jtf1.getText();
                password = new String(jtf2.getPassword());
                handleLogin(id,password);
            }
        });

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
    }
    private void handleLogin(String enteredUsername,String enteredPassword) {

        try {
            if(enteredUsername.isEmpty() || enteredPassword.isEmpty()){
                JOptionPane.showMessageDialog(Login.this,"아이디와 비밀번호를 입력하세요","Nother Entered",JOptionPane.ERROR_MESSAGE);
                return;
            }
            boolean loginSuccessful = User.checkLoginInfo(enteredUsername, enteredPassword);


            if (loginSuccessful) {
                if (enteredUsername.equals("0")) {
                    openAdmin();
                } else {
                    openUser();
                }
            } else {
                JOptionPane.showMessageDialog(Login.this, "해당 로그인 정보가 존재하지 않습니다", "User Failed", JOptionPane.ERROR_MESSAGE);
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(Login.this, "파일을 읽어들이지 못하였습니다", "User Failed", JOptionPane.ERROR_MESSAGE);
        }
    }
    private void openSignup() {
        dispose();
        Signup signup = new Signup();
        signup.setVisible(true);
    }
    private void openAdmin(){
        dispose();
        Admin admin = new Admin();
        admin.setVisible(true);
    }
    private void openUser(){
        dispose();
        ChooseApartment chooseApartment = new ChooseApartment();
        chooseApartment.setVisible(true);
    }

    public static void main(String[] args){
        Login login = new Login();
        login.setVisible(true);
    }

}


