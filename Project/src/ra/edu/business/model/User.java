package ra.edu.business.model;

public class User {
    private int id;
    private String account;
    private String password;
    private boolean status;

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User() {
    }

    public User(String account, int id, String password, boolean status) {
        this.account = account;
        this.id = id;
        this.password = password;
        this.status = status;
    }
}
