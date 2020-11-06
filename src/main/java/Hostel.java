import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Hostel implements Comparable<Hostel> {
    private final String id;
    private final String group;
    private final String name;
    private final LocalDate dateFoundation;
    private final int numOfRoom;
    private final int stars;
    Hostel(String[] info) throws IllegalArgumentException {
        if (info.length != 6) {
            throw new IllegalArgumentException("Error: wrong data");
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        this.id = info[0];
        this.group = info[1];
        this.name = info[2];
        this.dateFoundation = LocalDate.parse(info[3], formatter);
        this.numOfRoom = Integer.parseInt(info[4]);
        this.stars = Integer.parseInt(info[5]);
    }

    @Override
    public String toString() {
        return "Hostel{" +
                "group='" + group + '\'' +
                ", name='" + name + '\'' +
                ", stars=" + stars +
                '}';
    }

    public int compareTo(Hostel compareHostel){
        int compareStars = ((Hostel)compareHostel).getStars();
        return  this.stars - compareStars;
    }

    public String getId() {
        return id;
    }

    public String getGroup() {
        return group;
    }

    public String getName() {
        return name;
    }

    public LocalDate getDateFoundation() {
        return dateFoundation;
    }

    public int getNumOfRoom() {
        return numOfRoom;
    }

    public int getStars() {
        return stars;
    }
}
