public class Episode {
    String episodeName;
    long episodeDuration;

    public Episode(String episodeName, long episodeDuration){
        this.episodeName = episodeName;
        this.episodeDuration = episodeDuration;
    }
    String getEpisodeName() {
        return episodeName;
    }
}