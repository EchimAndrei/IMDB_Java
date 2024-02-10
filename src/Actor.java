import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Actor implements Comparable, Subject {
    String name;
    String biography;
    List<Map.Entry<String, String>> performances;
    String imageURL = null;
    private List<Observer> observers = new ArrayList<>();

    public Actor(String name, String biography, List<Map.Entry<String, String>> performances){
        IMDB.getInstance().addActorSystem(this);
        this.name  = name;
        this.biography = biography;
        this.performances = performances;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
    public void getimageURL(){
        System.out.println(this.imageURL);
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


    public void displayInfo(){
        System.out.println("Actor name: " + name);
        System.out.println("Biography: " + biography);
        System.out.println("Performances: " + performances);
    }
    @Override
    public int compareTo(Object o) {
        if (o instanceof Actor) {
            Actor other = (Actor) o;
            return this.name.compareTo(other.name);
        } else if (o instanceof Movie) {
            Movie other = (Movie) o;
            return this.name.compareTo(other.getTitle());
        } else if (o instanceof Series) {
            Series other = (Series) o;
            return this.name.compareTo(other.getTitle());
        } else {
            throw new IllegalArgumentException("Compararea poate fi făcută doar între obiecte de tip Actor, Movie sau Series.");
        }
    }
}