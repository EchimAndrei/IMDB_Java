public class Regular extends User implements RequestsManager {

    public Regular(String name, Information information) {
        super(name, information, AccountType.REGULAR);
        IMDB.getInstance().regulars.add(this);
    }

    @Override
    public void createRequest(Request request) {

        IMDB.getInstance().addRequestSystem(request);
    }
    @Override
    public void removeRequest(Request request) {
        IMDB.getInstance().getRequests().remove(request);
    }
    public void addRating(long rating, String comment, Production prod) {
        Rating userrating = new Rating(this.getUsername(), rating, comment);
        prod.addRatingstoProduction(userrating);
    }
}