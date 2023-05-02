import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class User {
    private String username;
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public static boolean checkLoginInfo(String username, String password, String fileName) throws IOException {
        List<User> users = readLoginsFromFile(fileName);
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    public static List<User> readLoginsFromFile(String fileName) throws IOException {
        List<User> users = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        String line;
        while ((line = br.readLine()) != null) {
            //line = line.substring(1, line.length() - 1);
            String[] parts = line.split(",");
            if (parts.length == 2) {
                users.add(new User(parts[0], parts[1]));
            }
        }
        br.close();
        return users;
    }
}