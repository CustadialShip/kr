import java.util.Objects;

public class User {
    private final String name;
    private final String login;
    private final String mail;
    private final String password;

    //private final String role;
    public enum Role {
        ADMIN("ADMIN"),
        USER("USER"),
        ERROR("ERROR");
        String value;

        Role(String value) {
            this.value = value.toUpperCase();
        }

        static Role fromString(String info) {
            info = info.toUpperCase();
            if (info.equals("ADMIN")) {
                return ADMIN;
            } else if (info.equals("USER")) {
                return USER;
            } else {
                return ERROR;
            }
        }
    }

    private final Role role;

    User(String[] info) throws IllegalArgumentException {
        if (info.length != 5) {
            throw new IllegalArgumentException("Error: wrong data");
        }
        this.name = info[0];
        this.login = info[1];
        this.mail = info[2];
        this.password = info[3];
        this.role = Role.fromString(info[4]);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return name.equals(user.name) &&
                login.equals(user.login) &&
                mail.equals(user.mail) &&
                password.equals(user.password) &&
                role.equals(user.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, login, mail, password, role);
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

    public Role getRole() {
        return role;
    }
}
