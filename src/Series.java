import java.util.List;
import java.util.Map;

public class Series extends Production {

    long launchYear = 0, nrSeasons = 0;
    private Map<String, List<Episode>> Seasons = null;

    public Series(String title, List<String> directors,
                  List<String> actors, List<Genre> genres,
                  List<Rating> ratings, String description,
                  long launchYear, long nrSeasons, Map<String, List<Episode>> Seasons) {
        super(title, directors, actors, genres, ratings, description);
        IMDB.getInstance().addSeriesSystem(this);
        this.launchYear = launchYear;
        this.nrSeasons = nrSeasons;
        this.Seasons = Seasons;
    }
    public long getLaunchYear() {
        return launchYear;
    }
    public long getNrSeasons() {
        return nrSeasons;
    }

    public Map<String, List<Episode>> getSeasons() {
        return Seasons;
    }

    public void setLaunchYear(long newlaunchyear) {
        this.launchYear = newlaunchyear;
    }

    public void setNrSeasons(long nrSeasons) {
        this.nrSeasons = nrSeasons;
    }

    @Override
    public void displayInfo() {
        System.out.println("Title: " + title);
        System.out.println("Directors: " + directors);
        System.out.println("Actors: " + actors);
        System.out.println("Genres: " + genres);
        System.out.println("Description: " + description );
        System.out.println("Launch Year: " + launchYear);
        System.out.println("Number of Seasons: " + nrSeasons);
        System.out.println("Average Rating: " + this.getAverageRating());
    }

}