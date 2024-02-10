public class Rating implements ExperienceStrategy{
    private String username;
    private long rating;
    private String comment;

    public Rating(String username, long rating, String comment){
        this.username = username;
        if (rating < 1 || rating > 10) {
            throw new IllegalArgumentException("Nota trebuie sa fie intre 1 și 10.");
        }
        this.rating = rating;
        this.comment = comment;
    }

    public String getUsername() {
        return username;
    }
    public long getRating() {
        return rating;
    }
    public String getComment() {
        return comment;
    }

    @Override
    public int calculateExperience() {

//        user.increaseExperience(new ReviewExperienceStrategy());
        return 10;
    }
}