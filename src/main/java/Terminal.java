import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Terminal {
    List<User> userListTerminal;
    List<Hostel> hostelsListTerminal;
    private boolean isUser;
    private boolean isAdmin;
    boolean isRun;

    Terminal(List<User> userList, List<Hostel> hostelsList){
        isUser = false;
        isAdmin = false;
        this.userListTerminal = userList;
        this.hostelsListTerminal = hostelsList;
        isRun = true;
    }

    public boolean isUser() {
        return isUser;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public boolean isRun() {
        return isRun;
    }

    List<User> update(){
        return userListTerminal;
    }

    void registration(String name, String login, String mail,
                      String password){
        boolean isExist = false;
        for(User i : userListTerminal){
            if(login.equals(i.getLogin())){
                System.out.println("Such user already exists");
                isExist = true;
            }
        }
        if(!isExist) {
            String[] user = new String[5];
            user[0] = name;
            user[1] = login;
            user[2] = mail;
            user[3] = password;
            user[4] = "user";
            userListTerminal.add(new User(user));
        }
    }

    void logIn(String login, String password){
        boolean isCorrectLogin = false;
        boolean isCorrectPassword = false;
        for(User i : userListTerminal){
            if(i.getLogin().equals(login)){
                isCorrectLogin = true;
            }
            if(i.getPassword().equals(password)){
                isCorrectPassword = true;
            }
            if(isCorrectLogin && isCorrectPassword && i.getRole().equals("admin")){
                isAdmin = true;
                isUser = false;
            }
            if(isCorrectLogin && isCorrectPassword && i.getRole().equals("user")){
                isUser = true;
                isAdmin = false;
            } else {
                isCorrectLogin = false;
                isCorrectPassword = false;
            }
        }
    }

    void showInfo(){
        for (Hostel i : hostelsListTerminal){
            System.out.println(i);
        }
    }

    String searchGroup(String id){
        if(isAdmin){
            for(Hostel i : hostelsListTerminal){
                if(i.getId().equals(id)){
                    return i.getGroup();
                }
            }
        }
        return "null";
    }

    double averageRating(String groupTemp){
        List<Hostel> soloGroup= new ArrayList<>();
        for(Hostel i : hostelsListTerminal){
            if(i.getGroup().equals(groupTemp)){
                soloGroup.add(i);
            }
        }
        double sum = 0;
        for(Hostel i : soloGroup){
            sum += i.getStars();
        }
        return sum / soloGroup.size();
    }

    List<Hostel> searchTop(int n, LocalDate infDate, LocalDate supDate){
            List<Hostel> answer = new ArrayList<>();
            for (Hostel i: hostelsListTerminal){
                if(infDate.isBefore(i.getDateFoundation()) &&
                        supDate.isAfter(i.getDateFoundation())){
                    answer.add(i);
                }
            }
        return answer;
    }

    void exit(){
        isAdmin = false;
        isUser = false;
        isRun = false;
    }
}
