import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

abstract class Staff<T extends Comparable<T>> extends User implements StaffInterface {

    List<Request> requests;
    SortedSet<T> prodOrActors;

    public Staff(String name, Information information, AccountType accountType){
        super(name, information, accountType);
        this.requests = new ArrayList<>();
        this.prodOrActors = new TreeSet<>();
    }

    IMDB imdb = IMDB.getInstance();

    public SortedSet<T> getProOrActors(){
        return this.prodOrActors;
    }
    @Override
    public void addProductionSystem(Production p) {
    }
    @Override
    public void removeProductionSystem(String name) {

    }
    @Override
    public void addActorSystem(Actor a) {
        if(!imdb.getActors().contains(a)){
            prodOrActors.add((T) a);
            imdb.addActorSystem(a);
        } else {
            System.out.println("Actorul exista deja in baza de date.");
        }
    }
    @Override
    public void removeActorSystem(String name) {
        int bool = 0;
        for(Actor actor : imdb.getActors()){
            if(actor.name.equals(name)){
                imdb.getActors().remove(actor);
                bool = 1;
                break;
            }
        }
        if (bool == 0) {
            System.out.println("Actorul nu exista in baza de date.");
        }
    }
    @Override
    public void updateProduction(Production p) {
    }
    @Override
    public void updateActor(Actor a) {

    }
    public void resolveRequests() {
        for (Request request : requests) {
            System.out.println("Cerere rezolvată: " + request.getDescription());
        }
        requests.clear();
    }
}