public class User extends UDB  {

    /*public User(String login, String pass, String role) {
    }*/
    @Override
    public void setUser(String login, String pass, String role) {
        this.login = login;
        this.pass = pass;
        this.role = role;
    }
}
