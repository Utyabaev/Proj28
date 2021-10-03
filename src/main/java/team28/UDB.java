public abstract class UDB {
    String login, pass, role;

    public String getLogin() {
        return login;
    }
    public String getPass() {
        return pass;
    }
    public String getRole() {
        return role;
    }
    public abstract void setUser(String login, String pass, String role);
}
