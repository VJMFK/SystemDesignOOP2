package Models;

import java.time.LocalDateTime;

public class Showtime {
    private Movie movie;
    private ScreeningRoom room;
    private LocalDateTime showtime;

    public Showtime(Movie movie, ScreeningRoom room, LocalDateTime showtime) {
        this.movie = movie;
        this.room = room;
        this.showtime = showtime;
    }

    public Movie getMovie() {
        return movie;
    }

    public ScreeningRoom getRoom() {
        return room;
    }

    public LocalDateTime getShowtime() {
        return showtime;
    }
}
