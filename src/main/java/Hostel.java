import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Hostel hostel = (Hostel) o;
        return numOfRoom == hostel.numOfRoom &&
                stars == hostel.stars &&
                id.equals(hostel.id) &&
                group.equals(hostel.group) &&
                name.equals(hostel.name) &&
                dateFoundation.equals(hostel.dateFoundation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, group, name, dateFoundation, numOfRoom, stars);
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
