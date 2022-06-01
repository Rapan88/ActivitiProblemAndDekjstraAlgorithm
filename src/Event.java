import java.time.Duration;
import java.time.LocalTime;

public class Event {
    private LocalTime start;
    private LocalTime finish;
    private String title;
    private double price;

    public Duration getDuration() {
        return Duration.between(start, finish);
    }

    private Event(LocalTime start, LocalTime finish, String title, double price) {
        this.start = start;
        this.finish = finish;
        this.title = title;
        this.price = price;
    }

    public static Event of(LocalTime start, LocalTime finish, String title, double price) {
        return new Event(start, finish, title, price);
    }

    public LocalTime getStart() {
        return start;
    }

    public void setStart(LocalTime start) {
        this.start = start;
    }

    public LocalTime getFinish() {
        return finish;
    }

    public void setFinish(LocalTime finish) {
        this.finish = finish;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return " Event -- band <**  " + title + "  **>" + " price " + price + "," + " start - " + start + "," + " finish - " + finish + "," + " duration: ";
    }
}
