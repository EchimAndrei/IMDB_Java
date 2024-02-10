import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class RequestHolder implements Subject {
    private static List<Request> adminrequests = new ArrayList<>();
    private java.time.LocalDateTime LocalDateTime;

    private List<Observer> observers = new ArrayList<>();

    public RequestHolder(String username, String description, Request.RequestTypes requestType, Object o, String solver) {
        for(Admin admin: IMDB.getInstance().admins){
            observers.add(admin);
        }
        Request request = new Request(username, description, requestType, null, "ADMIN");
        adminrequests.add(request);
        notifyObservers("* A new request has been added." + "\n" + "Request type: " + requestType);
        LocalDateTime = LocalDateTime.now();
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

    public static void addRequest(Request request) {
        adminrequests.add(request);
    }

    public static void removeRequest(String request) {
        adminrequests.remove(request);
    }

    public static List<Request> getRequests() {
        return adminrequests;
    }

    public void setcreatedDate(LocalDateTime createdDate) {
        this.LocalDateTime = createdDate;
    }
}