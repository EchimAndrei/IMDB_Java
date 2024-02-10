import java.time.LocalDateTime;

public class Credentials{
    private String email;
    private String password;
    private java.time.LocalDateTime LocalDateTime;
    Credentials (String email, String password) {
        this.email = email;
        this.password = password;
        this.LocalDateTime = LocalDateTime.now();
    }
    Credentials (String email) {
        this.email = email;
        this.password = null;
        this.LocalDateTime = LocalDateTime.now();
    }

    public String getEmail() {
        return email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setEmail(String email) {
        this.email = email;
    }
}