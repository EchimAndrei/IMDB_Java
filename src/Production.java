import java.util.ArrayList;
import java.util.List;

abstract class Production implements Comparable, Subject {
    String title = null;
    List<String> directors = null;
    List<String> actors = null;
    List<Genre> genres = null;
    List<Rating> ratings = null;
    String description = null;

    String imageURL = null;

    private List<Observer> observers = new ArrayList<>();
    public Production(String title, List<String> directors,
                      List<String> actors, List<Genre> genres,
                      List<Rating> ratings, String description) {

        this.title = title;
        this.directors = directors;
        this.actors = actors;
        this.genres = genres;
        this.ratings = ratings;
        this.description = description;
        this.averageRating = getAverageRating();
    }

    String getTitle(){
        return this.title;
    }
    void addRatingstoProduction(Rating rating){
        this.ratings.add(rating);
    }
    List<String> getDirectors(){
        return this.directors;
    }
    List<String> getActors(){
        return this.actors;
    }
    List<Genre> getGenres(){
        return this.genres;
    }
    public void setTitle(String newtitle) {
        this.title = newtitle;
    }
    List<Rating> getRatings(){
        return this.ratings;
    }
    String getDescription(){
        return this.description;
    }

    @Override
    public void registerObserver(Observer o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers(String notification) {
        for (Observer observer : observers) {
            observer.update(notification);
        }
    }

    double averageRating = 0;
    double getAverageRating() {
        int sum = 0;
        if(ratings == null || ratings.isEmpty())
            return 0;
        for(Rating rating : ratings) {
            sum += rating.getRating();
        }

        this.averageRating = (double) sum / ratings.size();
        return this.averageRating;
    }

    public abstract void displayInfo();
    public int compareTo(Object o) {
        if (o instanceof Actor) {
            Actor other = (Actor) o;
            return this.getTitle().compareTo(other.name);
        } else if (o instanceof Movie) {
            Movie other = (Movie) o;
            return this.getTitle().compareTo(other.getTitle());
        } else if (o instanceof Series) {
            Series other = (Series) o;
            return this.getTitle().compareTo(other.getTitle());
        } else {
            throw new IllegalArgumentException("Compararea poate fi făcută doar între obiecte de tip Actor, Movie sau Series.");
        }
    }

    public void setDescription(String newdescription) {
        this.description = newdescription;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
    public void getimageURL(){
        System.out.println(this.imageURL);
    }

    public void setDirectors(List<String> directors) {
        this.directors = directors;
    }

    public void setActors(List<String> actors) {
        this.actors = actors;
    }

}