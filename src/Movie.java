import java.util.List;

public class Movie extends Production{
    long Duration = 0;
    long launchYear = 0;
    public Movie(String title, List<String> directors,
                 List<String> actors, List<Genre> genres,
                 List<Rating> ratings, String description,
                 long Duration, long launchYear){
        super(title, directors, actors, genres, ratings, description);
        IMDB.getInstance().addMovieSystem(this);
        this.Duration = Duration;
        this.launchYear = launchYear;
    }
    public long getDuration() {
        return Duration;
    }
    public long getLaunchYear() {
        return launchYear;
    }

    @Override
    public void displayInfo() {
        System.out.println("Title: " + this.title);
        System.out.println("Directors: " + this.directors);
        System.out.println("Actors: " + this.actors);
        System.out.println("Genres: " + this.genres);
        System.out.println("Description: " + this.description);
        System.out.println("Duration: " + this.Duration + " minutes");
        System.out.println("Launch Year: " + this.launchYear);
        System.out.println("Average Rating: " + this.getAverageRating());
    }

    public void setDuration(long duration) {
        Duration = duration;
    }
    public void setLaunchYear(long newlaunchyear) {
        this.launchYear = newlaunchyear;
    }
}