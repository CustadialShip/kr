import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String line;
        String cvsSplitBy = ";";
        List<User> userList = new ArrayList<>();
        String csvInFileNameUser = "users.txt";
        try (BufferedReader br = new BufferedReader(new FileReader(csvInFileNameUser))) {
            while ((line = br.readLine()) != null) {
                String[] users = line.split(cvsSplitBy);
                for (int j = 0; j < users.length; j++) {
                    users[j] = users[j].toLowerCase();
                }
                userList.add(new User(users));
            }
        } catch (FileNotFoundException ex) {
            System.out.println(ex);
        } catch (IOException ex) {
            System.out.println(ex);
        }

        String csvInFileNameHostels = "hostels.txt";
        List<Hostel> hostelsList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(csvInFileNameHostels))) {
            while ((line = br.readLine()) != null) {
                String[] hostels = line.split(cvsSplitBy);
                for (int j = 0; j < hostels.length; j++) {
                    hostels[j] = hostels[j].toLowerCase();
                }
                hostelsList.add(new Hostel(hostels));
            }
        } catch (FileNotFoundException ex) {
            System.out.println(ex);
        } catch (IOException ex) {
            System.out.println(ex);
        }

        Terminal terminal = new Terminal(userList, hostelsList);
        try (Scanner sc = new Scanner(System.in)) {
            while (terminal.isRun()) {
                System.out.println("1 - registration");
                System.out.println("2 - log in");
                System.out.println("3 - exit");
                switch (sc.nextInt()) {
                    case 1 -> {
                        System.out.println("Insert name");
                        String nameTemp = sc.next().toLowerCase();
                        System.out.println("Insert login");
                        String loginTemp = sc.next().toLowerCase();
                        System.out.println("Insert mail");
                        String mailTemp = sc.next().toLowerCase();
                        System.out.println("Insert password");
                        String passwordTemp = sc.next().toLowerCase();
                        terminal.registration(nameTemp, loginTemp, mailTemp, passwordTemp);
                        userList = terminal.update();
                        try (FileWriter writer = new FileWriter(csvInFileNameUser, false)) {
                            for (User i : userList) {
                                writer.write(i.toString() + "\n");
                            }
                        } catch (IOException ex) {
                            System.out.println(ex);
                        }
                    }
                    case 2 -> {
                        System.out.println("Insert login");
                        String loginTempLg = sc.next();
                        System.out.println("Insert password");
                        String passwordTempLg = sc.next();
                        terminal.logIn(loginTempLg, passwordTempLg);
                    }
                    case 3 -> terminal.exit();
                    default -> throw new IllegalArgumentException();
                }
                if (terminal.isUser()) {
                    boolean back = false;
                    while (!back) {
                        System.out.println("1 - show info");
                        System.out.println("2 - top hostels");
                        System.out.println("3 - average rating in group");
                        System.out.println("4 - back");
                        System.out.println("5 - exit");
                        switch (sc.nextInt()) {
                            case 1 -> terminal.showInfo();
                            case 2 -> {
                                System.out.println("Number of hotels?");
                                int n = sc.nextInt();
                                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
                                System.out.println("Insert date from (DD.MM.YYYY)");
                                LocalDate sDateInf = LocalDate.parse(sc.next(), formatter);
                                System.out.println("Insert date by (DD.MM.YYYY)");
                                LocalDate sDateSup = LocalDate.parse(sc.next(), formatter);

                                for (Hostel i : terminal.searchTop(n, sDateInf, sDateSup)) {
                                    System.out.println(i);
                                }
                            }
                            case 3 -> {
                                System.out.println("Insert group");
                                String groupTemp = sc.next();
                                System.out.println(terminal.averageRating(groupTemp));
                            }
                            case 4 -> back = true;
                            case 5 -> terminal.exit();
                            default -> throw new IllegalArgumentException();
                        }
                    }
                } else if (terminal.isAdmin()) {
                    boolean back = false;
                    while (!back) {
                        System.out.println("1 - show info");
                        System.out.println("2 - top hostels");
                        System.out.println("3 - average rating in group");
                        System.out.println("4 - search group by id");
                        System.out.println("5 - back");
                        System.out.println("6 - exit");
                        switch (sc.nextInt()) {
                            case 1: {
                                terminal.showInfo();
                                break;
                            }
                            case 2: {
                                System.out.println("number of hotels?");
                                int n = sc.nextInt();
                                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
                                System.out.println("Insert date from (DD.MM.YYYY)");
                                LocalDate sDateInf = LocalDate.parse(sc.next(), formatter);
                                System.out.println("Insert date by (DD.MM.YYYY)");
                                LocalDate sDateSup = LocalDate.parse(sc.next(), formatter);

                                for (Hostel i : terminal.searchTop(n, sDateInf, sDateSup)) {
                                    System.out.println(i);
                                }
                                break;
                            }
                            case 3:{
                                System.out.println("Insert group");
                                String groupTemp = sc.next();
                                System.out.println(terminal.averageRating(groupTemp));
                                break;
                            }
                            case 4:{
                                String id = sc.next();
                                System.out.println(terminal.searchGroup(id));
                            }
                            case 5: {
                                back = true;
                                break;
                            }
                            case 6: {
                                terminal.exit();
                                break;
                            }
                            default: {
                                throw new IllegalArgumentException();
                            }
                        }
                    }
                }
            }
        } catch (IllegalArgumentException ex) {
            System.out.println(ex);
        }
    }
}
