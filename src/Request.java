import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Request implements Subject {
    public enum RequestTypes {
        DELETE_ACCOUNT, ACTOR_ISSUE, MOVIE_ISSUE, OTHERS;
    }
    private RequestTypes requestTypes;
    private java.time.LocalDateTime LocalDateTime;
    private String prodOrActor;
    private String description;
    private String username, solver;
    private List<Observer> observers = new ArrayList<>();

    public Request (String  username, String description, RequestTypes requestTypes, String prodOrActor,  String solver) {
        this.prodOrActor = prodOrActor;
        this.description = description;
        this.username = username;
        this.LocalDateTime = LocalDateTime.now();
        this.requestTypes = requestTypes;
        IMDB.getInstance().addRequestSystem(this);

        if(requestTypes.equals(RequestTypes.DELETE_ACCOUNT) || requestTypes.equals(RequestTypes.OTHERS)){
            this.solver = "ADMIN";
        }
        else if(requestTypes.equals(RequestTypes.ACTOR_ISSUE) || requestTypes.equals(RequestTypes.MOVIE_ISSUE)) {
            for(Contributor contributor : IMDB.getInstance().contributors){
                for(Object production : contributor.prodOrActors){
                    if(production instanceof Movie){
                        if(((Movie) production).getTitle().equals(prodOrActor)){
                            this.solver = contributor.getUsername();
                        }
                    } else if(production instanceof Series){
                        if(((Series) production).getTitle().equals(prodOrActor)){
                            this.solver = contributor.getUsername();
                        }
                    } else if(production instanceof Actor){
                        if(((Actor) production).name.equals(prodOrActor)){
                            this.solver = contributor.getUsername();
                        }
                    }
                }
            }
            for(Admin admin : IMDB.getInstance().admins){
                for(Object production : admin.prodOrActors){
                    if(production instanceof Movie){
                        if(((Movie) production).getTitle().equals(prodOrActor)){
                            this.solver = admin.getUsername();
                        }
                    } else if(production instanceof Series){
                        if(((Series) production).getTitle().equals(prodOrActor)){
                            this.solver = admin.getUsername();
                        }
                    } else if(production instanceof Actor){
                        if(((Actor) production).name.equals(prodOrActor)){
                            this.solver = admin.getUsername();
                        }
                    }
                }
            }
        }
    }

    public void setSolver(String solver) {
        this.solver = solver;
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

    public void setcreatedDate(LocalDateTime createdDate) {
        this.LocalDateTime = createdDate;
    }
    public String getFormattedDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return this.LocalDateTime.format(formatter);
    }
    public String getProdOrActor() {
        return  prodOrActor;
    }
    public String getUsername() {
        return username;
    }
    public String getSolver() {
        return solver;
    }
    public String getDescription() {
        return description;
    }

    public RequestTypes getRequestTypes() {
        return requestTypes;
    }
}