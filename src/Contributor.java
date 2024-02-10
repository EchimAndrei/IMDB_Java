public class Contributor extends Staff implements RequestsManager {
    public Contributor(String name, Information information) {
        super(name, information, AccountType.CONTRIBUTOR);
        IMDB.getInstance().contributors.add(this);
    }
    public void createRequest(Request r) {
        this.requests.add(r);
    }
    public void removeRequest(Request r) {
        this.requests.remove(r);
    }
}