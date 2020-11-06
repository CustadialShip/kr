public class User {
    private final String name;
    private final String login;
    private final String mail;
    private final String password;
    private final String role;

    User(String[] info) throws IllegalArgumentException {
        if (info.length != 5) {
            throw new IllegalArgumentException("Error: wrong data");
        }
        this.name = info[0];
        this.login = info[1];
        this.mail = info[2];
        this.password = info[3];
        this.role = info[4];
    }

    @Override
    public String toString() {
        return name + ";" +
                login + ";" +
                mail + ";" +
                password + ";" + role;
    }

    public String getName() {
        return name;
    }

    public String getLogin() {
        return login;
    }

    public String getMail() {
        return mail;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }
}
