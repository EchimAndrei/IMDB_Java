import java.time.LocalDateTime;
import java.util.*;

abstract class User<T extends Comparable> implements Observer {

    private Information information;
    private AccountType accountType;
    private String username;
    private long experience;
    private SortedSet<T> favorites;

    private ExperienceStrategy experienceStrategy;

    public List<Production> reviewdProductions = new ArrayList<>();

    private List<Rating> ratings;

    private List<String> notifications = new ArrayList<>();

    public User(String name, Information information, AccountType accountType) {

        this.information = information;
        this.username = generateUsername(name);
        this.accountType = accountType;
        this.experience = 0;
        this.notifications = new ArrayList<>();
        this.favorites = new TreeSet<>();
        this.ratings = new ArrayList<>();
    }


    public void setExperienceStrategy(ExperienceStrategy experienceStrategy) {
        this.experienceStrategy = experienceStrategy;
    }

    public void updateExp() {
        if (experienceStrategy != null) {
            experience += experienceStrategy.calculateExperience();
        }
    }

    public List<Production> getReviewdProductions() {
        return reviewdProductions;
    }

    public void addReviewdProduction(Production production) {
        reviewdProductions.add(production);
    }

    private String generateUsername(String name) {
        Random rand = new Random();
        String[] nameParts = name.split(" ");
        String first_name = nameParts[0].toLowerCase();
        String last_name = "";

        if (nameParts.length > 1) {
            last_name = nameParts[1].toLowerCase();
        }

        String username = first_name;
        if (!last_name.isEmpty()) {
            username += "_" + last_name;
        }
        username += "_" + rand.nextInt(10000);

        return username;
    }
    public void addFavorite(T favorite) {
        favorites.add(favorite);
    }
    public void removeFavorite(T favorite) {
        favorites.remove(favorite);
    }
    public void setExperience(long experience) {
        this.experience = experience;
    }
    public String getUsername() {
        return this.username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public Information getInformation() {
        return information;
    }
    public AccountType getAccountType() {
        return accountType;
    }
    public long getExperience() {
        return experience;
    }

    public void displayInfo(){
        System.out.println("Username: " + this.username);
        System.out.println("Nume: " + this.information.name);
        System.out.println("Tara: " + this.information.country);
        System.out.println("Varsta: " + this.information.age);
        System.out.println("Experienta: " + this.experience);
    }

    public List<String> getNotifications() {
        return notifications;
    }

    public void setNotifications(List<String> notifications) {
        this.notifications = notifications;
    }

    public List<Rating> getRatings() {
        return ratings;
    }
    public SortedSet<T> getFavorites() {
        return favorites;
    }

    @Override
    public void update(String notification) {
        notifications.add(notification);
    }

    public void printNotifications() {
        for (String notification : notifications) {
            System.out.println(notification);
        }
    }

    public void addRating(long rating, String comment, Production prod) {
        Rating userrating = new Rating(this.getUsername(), rating, comment);
        prod.addRatingstoProduction(userrating);
    }

    static class Information {

        String name, country;
        long age;
        char gender;
        private LocalDateTime birthDate;

        Credentials credentials;

        private Information(Builder builder) {
            this.credentials = builder.credentials;
            this.name = builder.name;
            this.country = builder.country;
            this.age = builder.age;
            this.gender = builder.gender;
            this.birthDate = builder.birthDate;
        }

        static class Builder {
            private Credentials credentials;
            private String country;
            private String name;
            private long age;
            private char gender;
            private LocalDateTime birthDate;

            private String generatePassword() {
                Random rand = new Random();
                String upperCase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
                String lowerCase = "abcdefghijklmnopqrstuvwxyz";
                String specialCharacters = "!@#$%^&*()_+";
                String numbers = "0123456789";
                String password = rand.nextInt(10000) + "";
                password += upperCase.charAt(rand.nextInt(upperCase.length()));
                password += upperCase.charAt(rand.nextInt(upperCase.length()));
                password += lowerCase.charAt(rand.nextInt(lowerCase.length()));
                password += lowerCase.charAt(rand.nextInt(lowerCase.length()));
                password += specialCharacters.charAt(rand.nextInt(specialCharacters.length()));
                password += specialCharacters.charAt(rand.nextInt(specialCharacters.length()));
                password += numbers.charAt(rand.nextInt(numbers.length()));
                return password;
            }

            public Builder setCredentials(Credentials credentials) {
                if(credentials.getPassword() == null)
                    credentials.setPassword(generatePassword());
                this.credentials = credentials;
                return this;
            }
            public Builder setName(String name) {
                this.name = name;
                return this;
            }

            public Builder setCountry(String country) {
                this.country = country;
                return this;
            }

            public Builder setAge(long age) {
                this.age = age;
                return this;
            }

            public Builder setGender(char gender) {
                this.gender = gender;
                return this;
            }

            public Builder setBirthDate(LocalDateTime birthDate) {
                this.birthDate = birthDate;
                return this;
            }

            public Information build() {
                return new Information(this);
            }

        }


        public String getName() {
            return name;
        }
        public String getCountry() {
            return country;
        }
        public long getAge() {
            return age;
        }
        public char getGender() {
            return gender;
        }
        public LocalDateTime getBirthDate() {
            return birthDate;}
        public Credentials getCredentials() {
            return credentials;
        }
    }

    void logout(){
        System.out.println("Logout");
        System.exit(0);
    }
}