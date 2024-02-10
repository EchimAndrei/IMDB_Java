import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class IMDB {
    public List<Regular> regulars;
    public List<Contributor> contributors;
    public List<Admin> admins;
    public List<User> users;
    public List<Actor> actors;
    public List<Request> requests;
    public List<Movie> movies;
    public List<Series> series;

    private static IMDB instance;

    private IMDB() {
        regulars = new ArrayList<>();
        contributors = new ArrayList<>();
        admins = new ArrayList<>();
        actors = new ArrayList<>();
        requests = new ArrayList<>();
        movies = new ArrayList<>();
        series = new ArrayList<>();
        users = new ArrayList<>();
    }

    public List<Actor> getActors() {
        return actors;
    }

    public List<User> getUsers() {
        return users;
    }

    public List<Admin> getAdmins() {
        return admins;
    }

    public List<Contributor> getContributors() {
        return contributors;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public List<Regular> getRegulars() {
        return regulars;
    }

    public List<Request> getRequests() {
        return requests;
    }

    public List<Series> getSeries() {
        return series;
    }

    public void addRegularSystem(Regular regular) {
        regulars.add(regular);
    }

    public void addContributorSystem(Contributor contributor) {
        contributors.add(contributor);
    }

    public void addAdminSystem(Admin admin) {
        admins.add(admin);
    }

    public void addActorSystem(Actor actor) {
        actors.add(actor);
    }

    public void addRequestSystem(Request request) {
        requests.add(request);
    }

    public void addMovieSystem(Movie movie) {
        movies.add(movie);
    }

    public void addSeriesSystem(Series serie) {
        series.add(serie);
    }

    public static IMDB getInstance() {
        if (instance == null) {
            instance = new IMDB();
        }
        return instance;
    }


    public User login(List<User> users) {
        System.out.println("Welcome back! Enter your credentials: ");
        Scanner scanner = new Scanner(System.in);
        User user = null;
        int bool;
        do {
            bool = 0;
            System.out.print("email: ");
            String email = scanner.nextLine();
            System.out.print("password: ");
            String password = scanner.nextLine();

            for (User usercur : users) {
                if (usercur.getInformation().getCredentials().getEmail().equals(email) &&
                        usercur.getInformation().getCredentials().getPassword().equals(password)) {
                    user = usercur;
                    System.out.println("Welcome back user " + user.getUsername() + "!");
                    System.out.println("Username: " + user.getUsername());
                    System.out.println("User experience: " + user.getExperience());
                    System.out.println("Account type: " + user.getAccountType());
                    System.out.println("Choose action: ");
                    bool = 1;
                    break;
                }
            }
            if (bool == 0) {
                System.out.println("Invalid credentials. Please try again.");
            }
        } while (bool == 0);
        return user;
    }

    public void displayProductions(User user) {
        Scanner scanner = new Scanner(System.in);
        int i = 1;
        System.out.println("0) Go back to menu");
        System.out.println("1) Filter productions by genre:");
        System.out.println("2) Filter productions by number of ratings:");
        System.out.println("3) Show all productions:");
        int choicefilter = scanner.nextInt();
        scanner.nextLine();
        switch (choicefilter) {
            case 0:
                returnToMenu(user);
                break;
            case 1:
                System.out.println("Choose a genre: ");
                System.out.println("0) Go back to menu");
                System.out.println("1) Action");
                System.out.println("2) Adventure");
                System.out.println("3) Comedy");
                System.out.println("4) Crime");
                System.out.println("5) Drama");
                System.out.println("6) Fantasy");
                System.out.println("7) Biography");
                System.out.println("8) Horror");
                System.out.println("9) Mystery");
                System.out.println("10) Romance");
                System.out.println("11) Science Fiction");
                System.out.println("12) Thriller");
                System.out.println("13) Cooking");
                System.out.println("14) War");
                int choicegenre = scanner.nextInt();
                scanner.nextLine();
                if (choicegenre == 0) {
                    returnToMenu(user);
                    break;
                }
                Genre chosedgenre = null;
                switch (choicegenre) {
                    case 1:
                        chosedgenre = Genre.ACTION;
                        break;
                    case 2:
                        chosedgenre = Genre.ADVENTURE;
                        break;
                    case 3:
                        chosedgenre = Genre.COMEDY;
                        break;
                    case 4:
                        chosedgenre = Genre.CRIME;
                        break;
                    case 5:
                        chosedgenre = Genre.DRAMA;
                        break;
                    case 6:
                        chosedgenre = Genre.FANTASY;
                        break;
                    case 7:
                        chosedgenre = Genre.BIOGRAPHY;
                        break;
                    case 8:
                        chosedgenre = Genre.HORROR;
                        break;
                    case 9:
                        chosedgenre = Genre.MYSTERY;
                        break;
                    case 10:
                        chosedgenre = Genre.ROMANCE;
                        break;
                    case 11:
                        chosedgenre = Genre.SF;
                        break;
                    case 12:
                        chosedgenre = Genre.THRILLER;
                        break;
                    case 13:
                        chosedgenre = Genre.COOKING;
                        break;
                    case 14:
                        chosedgenre = Genre.WAR;
                        break;

                    default:
                        System.out.println("Invalid choice");
                        break;
                }
                System.out.println("Choose a production: ");
                System.out.println("Movies: ");
                int movieofgenre = 0;
                int seriesofgenre = 0;
                for (Movie movie : movies) {
                    if (movie.getGenres().contains(chosedgenre)) {
                        System.out.println(i + ") " + movie.getTitle());
                        movieofgenre++;
                        i++;
                    }
                }
                System.out.println("Series: ");
                for (Series serie : series) {
                    if (serie.getGenres().contains(chosedgenre)) {
                        System.out.println(i + ") " + serie.getTitle());
                        seriesofgenre++;
                        i++;
                    }
                }

                i = 1;
                int choiceprod = scanner.nextInt();
                scanner.nextLine();
                if (choiceprod <= movieofgenre)
                    for (Movie movie : movies) {
                        if (movie.getGenres().contains(chosedgenre)) {
                            if (i == choiceprod)
                                movie.displayInfo();
                            i++;
                        }
                    }
                else
                    for (Series serie : series) {
                        if (serie.getGenres().contains(chosedgenre)) {
                            if (i == choiceprod)
                                serie.displayInfo();
                            i++;
                        }
                    }

                System.out.println("1) Chose another production");
                System.out.println("2) Go back to menu");
                String choice = scanner.nextLine();
                switch (choice) {
                    case "1":
                        displayProductions(user);
                        break;
                    case "2":
                        returnToMenu(user);
                    default:
                        System.out.println("Invalid choice");
                        displayProductions(user);
                        break;
                }
                break;

            case 2:
                SortedSet<Object> sortedSet = new TreeSet<Object>(new Comparator<Object>() {
                    @Override
                    public int compare(Object o1, Object o2) {
                        if (o1 instanceof Movie && o2 instanceof Movie) {
                            Movie movie1 = (Movie) o1;
                            Movie movie2 = (Movie) o2;
                            return movie1.getRatings().size() - movie2.getRatings().size();
                        } else if (o1 instanceof Series && o2 instanceof Series) {
                            Series serie1 = (Series) o1;
                            Series serie2 = (Series) o2;
                            return serie1.getRatings().size() - serie2.getRatings().size();
                        }
                        return 0;
                    }
                });
                sortedSet.addAll(movies);
                sortedSet.addAll(series);
                int numOfmoviesRatings = 0;
                int numOfseriesRatings = 0;
                System.out.println("Choose a production: ");
                for (Object obj : sortedSet) {
                    if (obj instanceof Movie) {
                        System.out.println(i + ") " + "Movie:" + ((Movie) obj).getTitle() + " " + ((Movie) obj).getRatings().size());
                        numOfmoviesRatings++;
                    } else if (obj instanceof Series) {
                        System.out.println(i + ") " + "Series" + ((Series) obj).getTitle() + " " + ((Series) obj).getRatings().size());
                        numOfseriesRatings++;
                    }
                    i++;
                }
                i = 1;
                int choiceprod2 = scanner.nextInt();
                scanner.nextLine();
                for (Object obs : sortedSet) {
                    if (obs instanceof Movie) {
                        if (i == choiceprod2) {
                            ((Movie) obs).displayInfo();
                            break;
                        }
                        i++;
                    } else if (obs instanceof Series) {
                        if (i == choiceprod2) {
                            ((Series) obs).displayInfo();
                            break;
                        }
                        i++;
                    }
                }

                System.out.println("1) Chose another production");
                System.out.println("2) Go back to menu");
                choice = scanner.nextLine();
                switch (choice) {
                    case "1":
                        displayProductions(user);
                        break;
                    case "2":
                        returnToMenu(user);
                    default:
                        System.out.println("Invalid choice");
                        displayProductions(user);
                        break;
                }
                break;
            case 3:

                System.out.println("Choose a production: ");
                System.out.println("Movies: ");
                for (Movie movie : movies) {
                    System.out.println(i + ") " + movie.getTitle());
                    i++;
                }
                System.out.println("Series: ");
                for (Series serie : series) {
                    System.out.println(i + ") " + serie.getTitle());
                    i++;
                }

                choiceprod = scanner.nextInt();
                scanner.nextLine();
                if (choiceprod <= movies.size())
                    movies.get(choiceprod - 1).displayInfo();
                else
                    series.get(choiceprod - movies.size() - 1).displayInfo();

                System.out.println("1) Chose another production");
                System.out.println("2) Go back to menu");
                choice = scanner.nextLine();
                switch (choice) {
                    case "1":
                        displayProductions(user);
                        break;
                    case "2":
                        returnToMenu(user);
                    default:
                        System.out.println("Invalid choice");
                        displayProductions(user);
                        break;
                }
                break;
        }

    }

    public void displayActors(User user) {
        Scanner scanner = new Scanner(System.in);
        int i = 1;

        System.out.println("Sort alfabetically?");
        System.out.println("1) Yes");
        System.out.println("2) No");
        String choicealf = scanner.nextLine();
        switch (choicealf) {
            case "1":
                Collections.sort(actors, new Comparator<Actor>() {
                    @Override
                    public int compare(Actor o1, Actor o2) {
                        return o1.name.compareTo(o2.name);
                    }
                });
                break;
            case "2":
                break;
            default:
                System.out.println("Invalid choice");
                break;
        }
        System.out.println("Choose an actor: ");
        for (Actor actor : actors) {
            System.out.println(i + ") " + actor.name);
            i++;
        }

        int choiceact = scanner.nextInt();
        scanner.nextLine();

        if (choiceact <= actors.size())
            actors.get(choiceact - 1).displayInfo();
        else
            System.out.println("Invalid choice");
        System.out.println("1) Chose another actor");
        System.out.println("2) Go back to menu");
        String choice = scanner.nextLine();
        switch (choice) {
            case "1":
                displayActors(user);
                break;
            case "2":
                if (user.getAccountType().equals(AccountType.ADMIN))
                    AdminMenu(user);
                else if (user.getAccountType().equals(AccountType.CONTRIBUTOR))
                    ContributorMenu(user);
                else if (user.getAccountType().equals(AccountType.REGULAR))
                    RegularMenu(user);
                break;
            default:
                System.out.println("Invalid choice");
                break;
        }
    }

    public void returnToMenu(User user) {
        if (user.getAccountType().equals(AccountType.ADMIN))
            AdminMenu(user);
        else if (user.getAccountType().equals(AccountType.CONTRIBUTOR))
            ContributorMenu(user);
        else if (user.getAccountType().equals(AccountType.REGULAR))
            RegularMenu(user);
    }

    public void addDeletefavorite(User user) {
        System.out.println("1) Add to favorites");
        System.out.println("2) Delete from favorites");
        Scanner scanner = new Scanner(System.in);
        String choice = scanner.nextLine();
        int i = 1;
        int actorfav = 0;
        int moviefav = 0;
        int seriesfav = 0;

        for (Object favorite : user.getFavorites())
            if (favorite instanceof Actor)
                actorfav++;
        for (Object favorite : user.getFavorites())
            if (favorite instanceof Movie)
                moviefav++;
        for (Object favorite : user.getFavorites())
            if (favorite instanceof Series)
                seriesfav++;

        switch (choice) {
            case "0":
                System.out.println("0) Go back to menu");
                returnToMenu(user);
                break;
            case "1":
                i = 1;
                System.out.println("Choose what to add: ");
                System.out.println("0) Go back to menu");

                System.out.println("Actors: ");
                for (Actor actor : actors) {
                    if (!user.getFavorites().contains(actor)) {
                        System.out.println(i + ") " + actor.name);
                        i++;
                    }
                }

                System.out.println("Movies: ");
                for (Movie movie : movies) {
                    if (!user.getFavorites().contains(movie)) {
                        System.out.println(i + ") " + movie.getTitle());
                        i++;
                    }
                }

                System.out.println("Series: ");
                for (Series serie : series) {
                    if (!user.getFavorites().contains(serie)) {
                        System.out.println(i + ") " + serie.getTitle());
                        i++;
                    }
                }

                int choiceadd = scanner.nextInt();
                scanner.nextLine();

                if (choiceadd == 0) {
                    returnToMenu(user);
                } else if (choiceadd <= actors.size() - actorfav) {
                    i = 1;
                    for (Actor actor : actors) {
                        if (!user.getFavorites().contains(actor)) {
                            System.out.println(actor.name);
                            System.out.println("i: " + i + " choiceadd: " + choiceadd);
                            if (i == choiceadd) {
                                System.out.println("choiceadd " + choiceadd);
                                user.addFavorite(actor);
                                actor.registerObserver(user);
                                returnToMenu(user);
                            }
                            i++;
                        }
                    }
                } else if (choiceadd <= actors.size() - actorfav + movies.size() - moviefav) {
                    i = 1;
                    for (Movie movie : movies) {
                        if (!user.getFavorites().contains(movie)) {
                            System.out.println("i: " + i + " choiceadd: " + choiceadd);
                            if (i == (choiceadd - (actors.size() - actorfav))) {
                                System.out.println("SUNT AICI");
                                user.addFavorite(movie);
                                movie.registerObserver(user);
                                returnToMenu(user);
                            }
                            i++;
                        }
                    }
                } else if (choiceadd <= actors.size() - actorfav + movies.size() - moviefav + series.size() - seriesfav) {
                    i = 1;
                    for (Series series : series) {
                        if (!user.getFavorites().contains(series))
                            if (i == choiceadd - (actors.size() - actorfav) - (movies.size() - moviefav)) {
                                user.addFavorite(series);
                                series.registerObserver(user);
                                returnToMenu(user);
                            }
                        i++;
                    }
                } else {
                    System.out.println("Invalid choice");
                    addDeletefavorite(user);
                }
                break;


            case "2":
                i = 1;
                if (user.getFavorites().isEmpty()) {
                    System.out.println("You have no favorites");
                    System.out.println("1) Go back to menu");
                    String choiceback = scanner.nextLine();
                    switch (choiceback) {
                        case "1":
                            returnToMenu(user);
                            break;
                        default:
                            System.out.println("Invalid choice");
                            break;
                    }
                }
                System.out.println("Choose what to delete: ");
                System.out.println("0) Go back to menu");
                actorfav = 0;
                moviefav = 0;
                seriesfav = 0;
                int hasactfav = 0, hasmoviefav = 0, hasseriesfav = 0;

                for (Object obj : user.getFavorites()) {
                    if (obj instanceof Actor)
                        hasactfav = 1;
                    if (obj instanceof Movie)
                        hasmoviefav = 1;
                    if (obj instanceof Series)
                        hasseriesfav = 1;
                }

                if (hasactfav == 1) {
                    System.out.println("Actors: ");
                    for (Object favorite : user.getFavorites()) {
                        if (favorite instanceof Actor) {
                            System.out.println(i + ") " + ((Actor) favorite).name);
                            actorfav++;
                            i++;
                        }
                    }
                }

                if (hasmoviefav == 1) {
                    System.out.println("Movies: ");
                    for (Object favorite : user.getFavorites()) {
                        if (favorite instanceof Movie) {
                            System.out.println(i + ") " + ((Movie) favorite).getTitle());
                            moviefav++;
                            i++;
                        }
                    }
                }

                if (hasseriesfav == 1) {
                    System.out.println("Series: ");
                    for (Object favorite : user.getFavorites()) {
                        if (favorite instanceof Series) {
                            System.out.println(i + ") " + ((Series) favorite).getTitle());
                            seriesfav++;
                            i++;
                        }
                    }
                }

                int choicerem = scanner.nextInt();
                scanner.nextLine();

                if (choicerem == 0) {
                    returnToMenu(user);
                } else if (choicerem <= actorfav) {
                    i = 1;
                    for (Object favorite : user.getFavorites()) {
                        if (favorite instanceof Actor) {
                            if (i == choicerem) {
                                user.removeFavorite((Actor) favorite);
                                ((Actor) favorite).removeObserver(user);
                                returnToMenu(user);
                            }
                            i++;
                        }
                    }
                } else if (choicerem <= actorfav + moviefav) {
                    i = 1;
                    for (Object favorite : user.getFavorites()) {
                        if (favorite instanceof Movie) {
                            if (i == (choicerem - actorfav)) {
                                user.removeFavorite((Movie) favorite);
                                ((Movie) favorite).removeObserver(user);
                                returnToMenu(user);
                            }
                            i++;
                        }
                    }
                } else if (choicerem <= actorfav + moviefav + seriesfav) {
                    i = 1;
                    for (Object favorite : user.getFavorites()) {
                        if (favorite instanceof Series) {
                            if (i == (choicerem - actorfav - moviefav)) {
                                user.removeFavorite((Series) favorite);
                                ((Series) favorite).removeObserver(user);
                                returnToMenu(user);
                            }
                            i++;
                        }
                    }
                } else {
                    System.out.println("Invalid choice");
                    addDeletefavorite(user);
                }
                break;
            default:
                System.out.println("Invalid choice");
                break;
        }
    }

    public void addDeleteRequest(User user) {
        System.out.println("1) Create a Request");
        System.out.println("2) Delete a Request");
        Scanner scanner = new Scanner(System.in);
        String choice = scanner.nextLine();
        switch (choice) {
            case "1":
                System.out.println("Choose a request type: ");
                System.out.println("0) Go back to menu");
                System.out.println("1) Delete account");
                System.out.println("2) Actor issue");
                System.out.println("3) Movie issue");
                System.out.println("4) Other");
                String choicereqtype = scanner.nextLine();
                if (choicereqtype.equals("0")) {
                    returnToMenu(user);
                    break;
                }
                switch (choicereqtype) {
                    case "1":
                        System.out.println("Enter a description: ");
                        String description = scanner.nextLine();
                        RequestHolder requestHolder = new RequestHolder(user.getUsername(), description,
                                Request.RequestTypes.DELETE_ACCOUNT, null, "ADMIN");
                        requestHolder.registerObserver(user);
                        System.out.println("Request created successfully");
                        returnToMenu(user);
                        break;
                    case "2":
                        System.out.println("Choose an actor: ");
                        int i = 1;
                        for (Actor actor : actors) {
                            System.out.println(i + ") " + actor.name);
                            i++;
                        }
                        int choiceact = scanner.nextInt();
                        Actor chosedactor = actors.get(choiceact - 1);
                        scanner.nextLine();
                        System.out.println("Enter a description: ");
                        String descriptionact = scanner.nextLine();
                        String solver = null;
                        for (Contributor contributor : contributors)
                            if (contributor.prodOrActors.contains(chosedactor))
                                solver = contributor.getUsername();
                        for (Admin admin : admins)
                            if (admin.prodOrActors.contains(chosedactor))
                                solver = admin.getUsername();

                        Request requestact = new Request(user.getUsername(), descriptionact,
                                Request.RequestTypes.ACTOR_ISSUE, chosedactor.name, solver);

                        for (Contributor contributor : contributors)
                            if (contributor.prodOrActors.contains(requestact)) {
                                requestact.registerObserver(contributor);
                                requestact.notifyObservers("A new request has been added to " + requestact.getProdOrActor() + " by " +
                                        user.getUsername() + " -> " + requestact.getDescription());
                            }

                        for (Admin admin : admins)
                            if (admin.prodOrActors.contains(requestact)) {
                                requestact.registerObserver(admin);
                                requestact.notifyObservers("A new request has been added to " + requestact.getProdOrActor() + " by " +
                                        user.getUsername() + " -> " + requestact.getDescription());
                            }
                        requestact.registerObserver(user);
                        System.out.println("Request created successfully");
                        returnToMenu(user);
                        break;
                    case "3":
                        System.out.println("Choose a production: ");
                        System.out.println("Movies: ");
                        i = 1;
                        for (Movie movie : movies) {
                            System.out.println(i + ") " + movie.getTitle());
                            i++;
                        }
                        System.out.println("Series: ");
                        for (Series serie : series) {
                            System.out.println(i + ") " + serie.getTitle());
                            i++;
                        }
                        int choiceprod = scanner.nextInt();
                        scanner.nextLine();

                        Production chosedprod;
                        if (choiceprod <= movies.size()) {
                            chosedprod = movies.get(choiceprod - 1);
                        } else
                            chosedprod = series.get(choiceprod - movies.size() - 1);


                        solver = null;
                        for (Contributor contributor : contributors)
                            if (contributor.prodOrActors.contains(chosedprod))
                                solver = contributor.getUsername();
                        for (Admin admin : admins)
                            if (admin.prodOrActors.contains(chosedprod))
                                solver = admin.getUsername();

                        System.out.println("Enter a description: ");
                        String descriptionprod = scanner.nextLine();

                        Request requestprod = new Request(user.getUsername(), descriptionprod,
                                Request.RequestTypes.MOVIE_ISSUE, chosedprod.getTitle(), solver);
                        System.out.println("Request created successfully");

                        for (Contributor contributor : contributors)
                            if (contributor.prodOrActors.contains(chosedprod)) {
                                requestprod.registerObserver(contributor);
                                requestprod.notifyObservers("A new request has been added to " + chosedprod.getTitle() + " by " +
                                        user.getUsername() + " -> " + descriptionprod);
                            }

                        for (Admin admin : admins)
                            if (admin.prodOrActors.contains(chosedprod)) {
                                requestprod.registerObserver(admin);
                                requestprod.notifyObservers("A new request has been added to " + chosedprod.getTitle() + " by " +
                                        user.getUsername() + " -> " + descriptionprod);
                            }

                        requestprod.registerObserver(user);
                        returnToMenu(user);
                        break;
                    case "4":
                        System.out.println("Enter a description: ");
                        String descriptionother = scanner.nextLine();
                        RequestHolder requestother = new RequestHolder(user.getUsername(), descriptionother,
                                Request.RequestTypes.OTHERS, null, "ADMIN");
                        System.out.println("Request created successfully");
                        requestother.registerObserver(user);
                        returnToMenu(user);
                        break;
                    default:
                        System.out.println("Invalid choice");
                        addDeleteRequest(user);
                        break;
                }
                break;
            case "2":
                int hasRequests = 0;
                for (Request request : requests)
                    if (request.getUsername().equals(user.getUsername()))
                        hasRequests = 1;
                if (hasRequests == 0) {
                    System.out.println("You have no requests");
                    System.out.println("0) Go back to menu");
                    String choiceback = scanner.nextLine();
                    if (choiceback.equals("0")) {
                        returnToMenu(user);
                    } else {
                        System.out.println("Invalid choice");
                    }
                }
                System.out.println("Choose a request to delete: ");
                System.out.println("0) Go back to menu");
                int i = 1;
                for (Request request : requests) {
                    if (request.getUsername().equals(user.getUsername())) {
                        System.out.print(i + ") ");
                        if (request.getRequestTypes().equals(Request.RequestTypes.DELETE_ACCOUNT))
                            System.out.print("Delete account request: ");
                        else if (request.getRequestTypes().equals(Request.RequestTypes.ACTOR_ISSUE))
                            System.out.print("Actor " + request.getProdOrActor() + " issue request: ");
                        else if (request.getRequestTypes().equals(Request.RequestTypes.MOVIE_ISSUE))
                            System.out.print("Movie " + request.getProdOrActor() + " issue request: ");
                        else if (request.getRequestTypes().equals(Request.RequestTypes.OTHERS))
                            System.out.print("Other request: ");
                        System.out.println(request.getFormattedDate());
                        System.out.println(request.getDescription());
                        i++;
                    }
                }
                int choicedel = scanner.nextInt();
                scanner.nextLine();
                if (choicedel == 0) {
                    returnToMenu(user);
                }

                for (Request request : requests) {
                    if (request.getUsername().equals(user.getUsername())) {
                        choicedel--;
                        if (choicedel == 0) {
                            requests.remove(request);
                            System.out.println("Request deleted successfully");
                            returnToMenu(user);
                        }
                    }
                }
                System.out.println("Request deleted successfully");
                returnToMenu(user);
                break;
            default:
                System.out.println("Invalid choice");
                addDeleteRequest(user);
                break;
        }
    }

    public void addDeleteRating(User user) {
        int HasRatings = 0;
        for (Movie movie : movies)
            for (Rating rating : movie.getRatings())
                if (rating.getUsername().equals(user.getUsername()))
                    HasRatings = 1;
        for (Series serie : series)
            for (Rating rating : serie.getRatings())
                if (rating.getUsername().equals(user.getUsername()))
                    HasRatings = 1;
        System.out.println("1) Add a Rating to a production");
        System.out.println("2) Delete a Rating from a production");
        Scanner scanner = new Scanner(System.in);
        String choice = scanner.nextLine();
        switch (choice) {
            case "1":

                System.out.println("Choose a production: ");
                System.out.println("Movies: ");
                int i = 1;
                for (Movie movie : movies) {
                    System.out.println(i + ") " + movie.getTitle());
                    i++;
                }
                System.out.println("Series: ");
                for (Series serie : series) {
                    System.out.println(i + ") " + serie.getTitle());
                    i++;
                }
                int choiceprod = scanner.nextInt();
                scanner.nextLine();
                if (user instanceof Regular) {
                    Regular regular = (Regular) user;
                    Production prod;
                    if (choiceprod <= movies.size())
                        prod = movies.get(choiceprod - 1);
                    else
                        prod = series.get(choiceprod - movies.size() - 1);

                    List<Production> ReviewdProd = user.getReviewdProductions();
                    if (!ReviewdProd.contains(prod) && !user.getAccountType().equals(AccountType.ADMIN)) {
                        user.setExperienceStrategy(new ReviewExperienceStrategy());
                        user.updateExp();
                        user.addReviewdProduction(prod);
                    }

                    if (prod.getRatings().size() != 0) {
                        for (Rating rating1 : prod.getRatings()) {
                            if (rating1.getUsername().equals(user.getUsername())) {
                                System.out.println("You have already rated this production");
                                System.out.println("1) Go back to menu");
                                if (scanner.nextLine().equals("1")) {
                                    returnToMenu(user);
                                } else {
                                    System.out.println("Invalid choice");
                                    returnToMenu(user);
                                }
                            }
                        }
                    }

                    System.out.println("Enter a rating: ");
                    int rating = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Enter a comment: ");
                    String comment = scanner.nextLine();

                    regular.addRating(rating, comment, prod);
                    prod.registerObserver(regular);
                    prod.notifyObservers("* A new rating has been added to " + prod.getTitle() + " by " +
                            regular.getUsername() + " -> " + rating);

                    System.out.println("Rating added successfully");
                    returnToMenu(user);
                }
                break;
            case "2":
                if (HasRatings == 0) {
                    System.out.println("You have no ratings");
                    System.out.println("0) Go back to menu");
                    String choiceback = scanner.nextLine();
                    switch (choiceback) {
                        case "0":
                            returnToMenu(user);
                            break;
                        default:
                            System.out.println("Invalid choice");
                            break;
                    }
                }
                System.out.println("Choose a rating to delete: ");
                System.out.println("0) Go back to menu");
                i = 1;
                for (Movie movie : movies) {
                    for (Rating rating : movie.getRatings()) {
                        if (rating.getUsername().equals(user.getUsername())) {
                            System.out.println(i + ") " + movie.getTitle() + " " + rating.getRating() + " " + rating.getComment());
                            i++;
                        }
                    }
                }
                for (Series serie : series) {
                    for (Rating rating : serie.getRatings()) {
                        if (rating.getUsername().equals(user.getUsername())) {
                            System.out.println(i + ") " + serie.getTitle() + " " + rating.getRating() + " " + rating.getComment());
                            i++;
                        }
                    }
                }
                int choiceback = scanner.nextInt();
                scanner.nextLine();
                i = 1;
                for (Movie movie : movies) {
                    for (Rating rating : movie.getRatings()) {
                        if (rating.getUsername().equals(user.getUsername())) {
                            if (choiceback == i) {
                                movie.getRatings().remove(rating);
                                System.out.println("Rating deleted successfully");
                                movie.removeObserver(user);
                                returnToMenu(user);
                            }
                            i++;
                        }
                    }
                }
                for (Series serie : series) {
                    for (Rating rating : serie.getRatings()) {
                        if (rating.getUsername().equals(user.getUsername())) {
                            if (choiceback == i) {
                                serie.getRatings().remove(rating);
                                System.out.println("Rating deleted successfully");
                                serie.removeObserver(user);
                                returnToMenu(user);
                            }
                            i++;
                        }
                    }
                }

                if (choiceback == 0) {
                    returnToMenu(user);
                    break;
                }
                break;
            default:
                System.out.println("Invalid choice");
                addDeleteRating(user);
                break;
        }
    }

    public void searchActororProd(User user) {
        System.out.println("1) Search for an actor");
        System.out.println("2) Search for a production");
        Scanner scanner = new Scanner(System.in);
        String choice = scanner.nextLine();
        switch (choice) {
            case "1":
                System.out.println("Enter the name of the actor: ");
                String name = scanner.nextLine();
                int i = 1;
                for (Actor actor : actors) {
                    if (actor.name.equals(name)) {
                        actor.displayInfo();
                        System.out.println("1) Go back to menu");
                        System.out.println("2) Search again");
                        String choiceback = scanner.nextLine();
                        switch (choiceback) {
                            case "1":
                                returnToMenu(user);
                                break;
                            case "2":
                                searchActororProd(user);
                                break;
                            default:
                                System.out.println("Invalid choice");
                                break;
                        }
                    }
                }
                System.out.println("Actor not found");
                System.out.println("1) Search again");
                System.out.println("2) Go back to menu");
                String choiceback = scanner.nextLine();
                switch (choiceback) {
                    case "1":
                        searchActororProd(user);
                        break;
                    case "2":
                        returnToMenu(user);
                        break;
                    default:
                        System.out.println("Invalid choice");
                        break;
                }
                break;
            case "2":
                System.out.println("Enter the name of the production: ");
                String nameprod = scanner.nextLine();
                i = 1;
                for (Movie movie : movies) {
                    if (movie.getTitle().equals(nameprod)) {
                        movie.displayInfo();
                        System.out.println("1) Go back to menu");
                        System.out.println("2) Search again");
                        choiceback = scanner.nextLine();
                        switch (choiceback) {
                            case "1":
                                returnToMenu(user);
                                break;
                            case "2":
                                searchActororProd(user);
                                break;
                            default:
                                System.out.println("Invalid choice");
                                break;
                        }
                    }
                }

                for (Series serie : series) {
                    if (serie.getTitle().equals(nameprod)) {
                        serie.displayInfo();
                        System.out.println("1) Go back to menu");
                        System.out.println("2) Search again");
                        choiceback = scanner.nextLine();
                        switch (choiceback) {
                            case "1":
                                returnToMenu(user);
                                break;
                            case "2":
                                searchActororProd(user);
                                break;
                            default:
                                System.out.println("Invalid choice");
                                break;
                        }
                    }
                }
                System.out.println("Production not found");
                System.out.println("1) Search again");
                System.out.println("2) Go back to menu");
                choiceback = scanner.nextLine();
                switch (choiceback) {
                    case "1":
                        searchActororProd(user);
                        break;
                    case "2":
                        returnToMenu(user);
                        break;
                    default:
                        System.out.println("Invalid choice");
                        searchActororProd(user);
                        break;
                }
                break;
        }
    }

    public void addDeleteUserfromSystem(User user) {
        System.out.println("1) Add an User");
        System.out.println("2) Delete an User");
        Scanner scanner = new Scanner(System.in);
        String choice = scanner.nextLine();
        switch (choice) {
            case "1":
                System.out.println("Enter the name of the User: ");
                String name = scanner.nextLine();
                System.out.println("Enter the email of the User: ");
                String email = scanner.nextLine();
                System.out.println("Enter the age of the User: ");
                long age = scanner.nextLong();
                scanner.nextLine();
                System.out.println("Enter the country of the User: ");
                String country = scanner.nextLine();
                System.out.println("Enter the gender of the user: (M, F, N)");
                char gender = scanner.next().charAt(0);
                scanner.nextLine();

                System.out.println("Enter the birth date of the User: (YYYY-MM-DD)");
                String birthDateString = scanner.nextLine();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate birthDate = LocalDate.parse(birthDateString, formatter);
                LocalDateTime birthDateTime = birthDate.atStartOfDay();

                User.Information information = new User.Information.Builder()
                        .setCredentials(new Credentials(email, null))
                        .setName(name)
                        .setAge(age)
                        .setCountry(country)
                        .setGender(gender)
                        .setBirthDate(birthDateTime)
                        .build();
                UserFactory userFactory = new UserFactory();
                User user1 = userFactory.createUser(AccountType.REGULAR, name, information);
                users.add(user1);
                regulars.add((Regular) user1);
                System.out.println("User added successfully");
                returnToMenu(user);
                break;
            case "2":
                System.out.println("Select an user: ");
                int i = 1;
                for (User user2 : users) {
                    System.out.println(i + ") " + user2.getUsername());
                    i++;
                }
                int choiceuser = scanner.nextInt();
                scanner.nextLine();
                User user2 = users.get(choiceuser - 1);

                Iterator<Request> requestIterator = requests.iterator();
                while (requestIterator.hasNext()) {
                    Request request = requestIterator.next();
                    if (request.getUsername().equals(user2.getUsername())) {
                        requestIterator.remove();
                    }
                }
                for (Movie movie : movies) {
                    Iterator<Rating> ratingIterator = movie.getRatings().iterator();
                    while (ratingIterator.hasNext()) {
                        Rating rating = ratingIterator.next();
                        if (rating.getUsername().equals(user2.getUsername())) {
                            ratingIterator.remove();
                        }
                    }
                }
                for (Series serie : series) {
                    Iterator<Rating> ratingIterator = serie.getRatings().iterator();
                    while (ratingIterator.hasNext()) {
                        Rating rating = ratingIterator.next();
                        if (rating.getUsername().equals(user2.getUsername())) {
                            ratingIterator.remove();
                        }
                    }
                }

                if (user2.getAccountType().equals(AccountType.CONTRIBUTOR)) {
                    Contributor contributor = (Contributor) user2;
                    Admin admin = (Admin) user;
                    for (Admin admin1 : admins)
                        admin1.prodOrActors.addAll(contributor.prodOrActors);
                    contributors.remove(contributor);
                    for (Request request : requests)
                        if (request.getSolver().equals(contributor.getUsername()))
                            request.setSolver(admin.getUsername());

                } else if (user2.getAccountType().equals(AccountType.REGULAR)) {
                    Regular regular = (Regular) user2;
                    regulars.remove(regular);
                }
                users.remove(user2);
                System.out.println("User deleted successfully");
                returnToMenu(user);
                break;
            default:
                System.out.println("Invalid choice");
                addDeleteUserfromSystem(user);
                break;
        }
    }

    public void addDeleteActororProdfromSytem(User user) {
        System.out.println("1) Add/Delete an Actor");
        System.out.println("2) Add/Delete a Production");
        Scanner scanner = new Scanner(System.in);
        String choice = scanner.nextLine();
        switch (choice) {
            case "1":
                System.out.println("1) Add an Actor");
                System.out.println("2) Delete an Actor");
                String choiceactor = scanner.nextLine();
                switch (choiceactor) {
                    case "1":
                        System.out.println("Enter the name of the Actor: ");
                        String name = scanner.nextLine();
                        System.out.println("Enter the biography of the Actor: ");
                        String biography = scanner.nextLine();
                        System.out.println("Enter the number of perfomances of the Actor: ");
                        List<Map.Entry<String, String>> performances = new ArrayList<>();
                        int numperf = scanner.nextInt();
                        scanner.nextLine();
                        for (int i = 0; i < numperf; i++) {
                            System.out.println("Enter the name of the production: ");
                            String prodname = scanner.nextLine();
                            System.out.println("Enter the type of production: (Movie or Series)");
                            String type = scanner.nextLine();
                            Map.Entry<String, String> entry = new AbstractMap.SimpleEntry<>(prodname, type);
                            performances.add(entry);
                        }
                        Actor newactor = new Actor(name, biography, performances);
                        actors.add(newactor);
                        if (!user.getAccountType().equals(AccountType.ADMIN)) {
                            user.setExperienceStrategy(new AddedProdOrActor());
                            user.updateExp();
                        }
                        if (user.getAccountType().equals(AccountType.ADMIN))
                            ((Admin) user).prodOrActors.add(newactor);
                        else if (user.getAccountType().equals(AccountType.CONTRIBUTOR))
                            ((Contributor) user).prodOrActors.add(newactor);
                        System.out.println("Actor added successfully");
                        returnToMenu(user);
                        break;
                    case "2":
                        System.out.println("Select an actor: ");
                        int i = 1;
                        if (user.getAccountType().equals(AccountType.CONTRIBUTOR)) {
                            Contributor contributor = (Contributor) user;
                            for (Object actor : contributor.prodOrActors) {
                                if (actor instanceof Actor) {
                                    System.out.println(i + ") " + ((Actor) actor).name);
                                    i++;
                                }
                            }
                            int choiceact = scanner.nextInt();
                            scanner.nextLine();
                            int j = 0;
                            Actor chosenActor = null;
                            for (Object actor : contributor.prodOrActors) {
                                if (actor instanceof Actor) {
                                    if (j == choiceact - 1) {
                                        chosenActor = (Actor) actor;
                                        break;
                                    }
                                    j++;
                                }
                            }
                            chosenActor.displayInfo();
                            System.out.println("1) Yes");
                            System.out.println("2) No");
                            String choiceyesno = scanner.nextLine();
                            System.out.println("choiceyesno: " + chosenActor.name);
                            switch (choiceyesno) {
                                case "1":
                                    contributor.prodOrActors.remove(chosenActor);
                                    actors.remove(chosenActor);
                                    System.out.println("Actor deleted successfully");
                                    returnToMenu(user);
                                    break;
                                case "2":
                                    addDeleteActororProdfromSytem(user);
                                    break;
                                default:
                                    System.out.println("Invalid choice");
                                    addDeleteActororProdfromSytem(user);
                                    break;
                            }

                        } else if (user.getAccountType().equals(AccountType.ADMIN)) {
                            for (Actor actor : actors) {
                                System.out.println(i + ") " + actor.name);
                                i++;
                            }
                            int choiceact = scanner.nextInt();
                            scanner.nextLine();
                            Actor chosenActor = actors.get(choiceact - 1);
                            System.out.println("Are you sure you want to delete " + chosenActor.name + "?");
                            chosenActor.displayInfo();
                            System.out.println("1) Yes");
                            System.out.println("2) No");
                            String choiceyesno = scanner.nextLine();
                            switch (choiceyesno) {
                                case "1":
                                    for (Admin admin : admins)
                                        if (admin.prodOrActors.contains(chosenActor))
                                            admin.prodOrActors.remove(chosenActor);
                                    for (Contributor contributor : contributors)
                                        if (contributor.prodOrActors.contains(chosenActor))
                                            contributor.prodOrActors.remove(chosenActor);
                                    actors.remove(chosenActor);
                                    System.out.println("Actor deleted successfully");
                                    returnToMenu(user);
                                    break;
                                case "2":
                                    addDeleteActororProdfromSytem(user);
                                    break;
                                default:
                                    System.out.println("Invalid choice");
                                    addDeleteActororProdfromSytem(user);
                                    break;
                            }
                        }
                    default:
                        System.out.println("Invalid choice");
                        addDeleteActororProdfromSytem(user);
                        break;
                }
                break;
            case "2":
                System.out.println("1) Add a Production");
                System.out.println("2) Delete a Production");
                String choiceprod = scanner.nextLine();
                switch (choiceprod) {
                    case "1":
                        System.out.println("Enter the type of the Production: (Movie or Series)");
                        String type = scanner.nextLine();
                        if (type.equals("Movie")) {
                            System.out.println("Enter the name of the Movie: ");
                            String title = scanner.nextLine();

                            System.out.println("Enter the description of the Production: ");
                            String description = scanner.nextLine();

                            List<Genre> genres = new ArrayList<>();
                            System.out.println("Enter the number of genres of the Production: ");
                            int numgen = scanner.nextInt();
                            scanner.nextLine();
                            for (int i = 0; i < numgen; i++) {
                                System.out.println("Enter the name of the genre: ");
                                String genrename = scanner.nextLine();
                                for (Genre genre : Genre.values())
                                    if (genre.name().equals(genrename))
                                        genres.add(genre);
                            }

                            List<String> directors = new ArrayList<>();
                            System.out.println("Enter the number of directors of the Production: ");
                            int numdir = scanner.nextInt();
                            scanner.nextLine();
                            for (int i = 0; i < numdir; i++) {
                                System.out.println("Enter the name of the director: ");
                                String directorname = scanner.nextLine();
                                directors.add(directorname);
                            }

                            System.out.println("Enter the number of actors of the Production: ");
                            List<String> actors = new ArrayList<>();
                            int numactors = scanner.nextInt();
                            scanner.nextLine();
                            for (int i = 0; i < numactors; i++) {
                                System.out.println("Enter the name of the actor: ");
                                String actorname = scanner.nextLine();
                                actors.add(actorname);
                            }

                            System.out.println("Enter the duration of the Production: ");
                            long duration = scanner.nextInt();
                            scanner.nextLine();

                            System.out.println("Enter the launch year of the Production: ");
                            long launchYear = scanner.nextInt();
                            scanner.nextLine();

                            Movie movie = new Movie(title, directors, actors, genres, null, description, duration, launchYear);
                            movies.add(movie);
                            if (!user.getAccountType().equals(AccountType.ADMIN)) {
                                user.setExperienceStrategy(new AddedProdOrActor());
                                user.updateExp();
                            }

                            if (user.getAccountType().equals(AccountType.ADMIN))
                                ((Contributor) user).prodOrActors.add(movie);
                            else if (user.getAccountType().equals(AccountType.CONTRIBUTOR))
                                ((Contributor) user).prodOrActors.add(movie);
                        } else if (type.equals("Series")) {
                            System.out.println("Enter the name of the Series: ");
                            String title = scanner.nextLine();

                            System.out.println("Enter the description of the Series: ");
                            String description = scanner.nextLine();

                            List<Genre> genres = new ArrayList<>();
                            System.out.println("Enter the number of genres of the Series: ");
                            int numgen = scanner.nextInt();
                            scanner.nextLine();
                            for (int i = 0; i < numgen; i++) {
                                System.out.println("Enter the name of the genre: ");
                                String genrename = scanner.nextLine();
                                for (Genre genre : Genre.values())
                                    if (genre.name().equals(genrename))
                                        genres.add(genre);
                            }

                            List<String> directors = new ArrayList<>();
                            System.out.println("Enter the number of directors of the Series: ");
                            int numdir = scanner.nextInt();
                            scanner.nextLine();
                            for (int i = 0; i < numdir; i++) {
                                System.out.println("Enter the name of the director: ");
                                String directorname = scanner.nextLine();
                                directors.add(directorname);
                            }

                            System.out.println("Enter the number of actors of the Series: ");
                            List<String> actors = new ArrayList<>();
                            int numactors = scanner.nextInt();
                            scanner.nextLine();
                            for (int i = 0; i < numactors; i++) {
                                System.out.println("Enter the name of the actor: ");
                                String actorname = scanner.nextLine();
                                actors.add(actorname);
                            }

                            System.out.println("Enter the launch year of the Series: ");
                            long launchyear = scanner.nextInt();
                            scanner.nextLine();

                            System.out.println("Enter the number of seasons of the Series: ");
                            long nrSeasons = scanner.nextInt();
                            scanner.nextLine();

                            Map<String, List<Episode>> Seasons = new HashMap<>();
                            for (int i = 0; i < nrSeasons; i++) {
                                System.out.println("Enter the number of episodes of season " + (i + 1) + ": ");
                                int nrEpisodes = scanner.nextInt();
                                scanner.nextLine();
                                List<Episode> episodes = new ArrayList<>();
                                for (int j = 0; j < nrEpisodes; j++) {
                                    System.out.println("Enter the name of episode " + (j + 1) + ": ");
                                    String episodeName = scanner.nextLine();
                                    System.out.println("Enter the duration of episode " + (j + 1) + ": ");
                                    long episodeDuration = scanner.nextInt();
                                    scanner.nextLine();
                                    Episode episode = new Episode(episodeName, episodeDuration);
                                    episodes.add(episode);
                                }
                                Seasons.put("Season " + (i + 1), episodes);
                            }

                            Series series = new Series(title, directors, actors, genres, null, description,
                                    launchyear, nrSeasons, Seasons);
                            if (!user.getAccountType().equals(AccountType.ADMIN)) {
                                user.setExperienceStrategy(new AddedProdOrActor());
                                user.updateExp();
                            }
                        } else {
                            System.out.println("Invalid choice");
                            addDeleteActororProdfromSytem(user);
                            break;
                        }

                        break;
                    case "2":
                        System.out.println("Select a production: ");
                        int i = 1;
                        if (user.getAccountType().equals(AccountType.CONTRIBUTOR)) {
                            Contributor contributor = (Contributor) user;
                            for (Object prod : contributor.prodOrActors) {
                                if (prod instanceof Movie) {
                                    System.out.println(i + ") " + ((Movie) prod).getTitle());
                                    i++;
                                } else if (prod instanceof Series) {
                                    System.out.println(i + ") " + ((Series) prod).getTitle());
                                    i++;
                                }
                            }
                            int choiceprod2 = scanner.nextInt();
                            scanner.nextLine();
                            Production production;
                            int j = 0;
                            for (Object prod : contributor.prodOrActors) {
                                if (prod instanceof Movie) {
                                    if (j == choiceprod2 - 1) {
                                        production = (Movie) prod;
                                        System.out.println("Are you sure you want to delete " + production.getTitle() + "?");
                                        production.displayInfo();
                                        System.out.println("1) Yes");
                                        System.out.println("2) No");
                                        String choiceyesno = scanner.nextLine();
                                        switch (choiceyesno) {
                                            case "1":
                                                contributor.prodOrActors.remove(production);
                                                movies.remove(production);
                                                System.out.println("Production deleted successfully");
                                                returnToMenu(user);
                                                break;
                                            case "2":
                                                addDeleteActororProdfromSytem(user);
                                                break;
                                            default:
                                                System.out.println("Invalid choice");
                                                addDeleteActororProdfromSytem(user);
                                                break;
                                        }
                                    }
                                    j++;
                                } else if (prod instanceof Series) {
                                    if (j == choiceprod2 - 1) {
                                        production = (Series) prod;
                                        System.out.println("Are you sure you want to delete " + production.getTitle() + "?");
                                        production.displayInfo();
                                        System.out.println("1) Yes");
                                        System.out.println("2) No");
                                        String choiceyesno = scanner.nextLine();
                                        switch (choiceyesno) {
                                            case "1":
                                                contributor.prodOrActors.remove(production);
                                                series.remove(production);
                                                System.out.println("Production deleted successfully");
                                                returnToMenu(user);
                                                break;
                                            case "2":
                                                addDeleteActororProdfromSytem(user);
                                                break;
                                            default:
                                                System.out.println("Invalid choice");
                                                addDeleteActororProdfromSytem(user);
                                                break;
                                        }
                                    }
                                    j++;
                                }
                            }
                        } else if (user.getAccountType().equals(AccountType.ADMIN)) {
                            for (Movie movie : movies) {
                                System.out.println(i + ") " + movie.getTitle());
                                i++;
                            }
                            for (Series serie : series) {
                                System.out.println(i + ") " + serie.getTitle());
                                i++;
                            }
                            int choiceprod2 = scanner.nextInt();
                            scanner.nextLine();
                            Production production;
                            if (choiceprod2 <= movies.size())
                                production = movies.get(choiceprod2 - 1);
                            else
                                production = series.get(choiceprod2 - movies.size() - 1);
                            System.out.println("Are you sure you want to delete " + production.getTitle() + "?");
                            production.displayInfo();
                            System.out.println("1) Yes");
                            System.out.println("2) No");
                            String choiceyesno = scanner.nextLine();
                            switch (choiceyesno) {
                                case "1":
                                    for (Contributor contributor : contributors)
                                        if (contributor.prodOrActors.contains(production))
                                            contributor.prodOrActors.remove(production);
                                    for (Admin admin : admins)
                                        if (admin.prodOrActors.contains(production))
                                            admin.prodOrActors.remove(production);
                                    if (production instanceof Movie)
                                        movies.remove(production);
                                    else if (production instanceof Series)
                                        series.remove(production);
                                    System.out.println("Production deleted successfully");
                                    returnToMenu(user);
                                    break;
                                case "2":
                                    addDeleteActororProdfromSytem(user);
                                    break;
                                default:
                                    System.out.println("Invalid choice");
                                    addDeleteActororProdfromSytem(user);
                                    break;
                            }
                        } else {
                            System.out.println("Invalid choice");
                            addDeleteActororProdfromSytem(user);
                            break;
                        }
                        break;
                    default:
                        System.out.println("Invalid choice");
                        addDeleteActororProdfromSytem(user);
                        break;
                }
                break;
            default:
                System.out.println("Invalid choice");
                addDeleteActororProdfromSytem(user);
                break;
        }

    }

    public void updateActor(User user) {
        System.out.println("1) Update an actor");
        System.out.println("2) Go back to menu");
        Scanner scanner = new Scanner(System.in);
        String choice = scanner.nextLine();
        switch (choice) {
            case "1":
                System.out.println("Select an actor: ");
                Actor chosenActor = null;
                if (user.getAccountType().equals(AccountType.CONTRIBUTOR)) {
                    int i = 1;
                    for (Object actor : ((Contributor) user).prodOrActors) {
                        if (actor instanceof Actor) {
                            System.out.println(i + ") " + ((Actor) actor).name);
                            i++;
                        }
                    }
                    int choiceact = scanner.nextInt();
                    scanner.nextLine();
                    int j = 0;
                    for (Object actor1 : ((Contributor) user).prodOrActors) {
                        if (actor1 instanceof Actor) {
                            if (j == choiceact - 1) {
                                chosenActor = (Actor) actor1;
                                break;
                            }
                            j++;
                        }
                    }
                } else if (user.getAccountType().equals(AccountType.ADMIN)) {
                    int i = 1;
                    for (Actor actor : actors) {
                        System.out.println(i + ") " + actor.name);
                        i++;
                    }
                    int choiceact = scanner.nextInt();
                    scanner.nextLine();
                    chosenActor = actors.get(choiceact - 1);
                }
                Actor actor = chosenActor;
                System.out.println("1) Update name");
                System.out.println("2) Update biography");
                System.out.println("3) Update performances");
                System.out.println("4) Go back to menu");
                String choiceupdate = scanner.nextLine();
                switch (choiceupdate) {
                    case "1":
                        System.out.println("Enter the new name: ");
                        System.out.println("Old name: " + actor.name);
                        String newname = scanner.nextLine();
                        actor.name = newname;
                        System.out.println("Name updated successfully");
                        updateActor(user);
                        break;
                    case "2":
                        System.out.println("Enter the new biography: ");
                        System.out.println("Old biography: " + actor.biography);
                        String newbiography = scanner.nextLine();
                        actor.biography = newbiography;
                        System.out.println("Biography updated successfully");
                        updateActor(user);
                        break;
                    case "3":
                        System.out.println("Enter the number of performances: ");
                        int numperf = scanner.nextInt();
                        scanner.nextLine();
                        List<Map.Entry<String, String>> performances = new ArrayList<>();
                        for (int j = 0; j < numperf; j++) {
                            System.out.println("Enter the name of the production: ");
                            String prodname = scanner.nextLine();
                            System.out.println("Enter the type of production: (Movie or Series)");
                            String type = scanner.nextLine();
                            Map.Entry<String, String> entry = new AbstractMap.SimpleEntry<>(prodname, type);
                            performances.add(entry);
                        }
                        actor.performances = performances;
                        System.out.println("Performances updated successfully");
                        updateActor(user);
                        break;
                    case "4":
                        returnToMenu(user);
                        break;
                    default:
                        System.out.println("Invalid choice");
                        updateActor(user);
                        break;
                }
                break;
            case "2":
                returnToMenu(user);
                break;
            default:
                System.out.println("Invalid choice");
                updateActor(user);
                break;
        }
    }

    public void updateProduction(User user) {
        System.out.println("1) Update a production");
        System.out.println("2) Go back to menu");
        Scanner scanner = new Scanner(System.in);
        String choice = scanner.nextLine();
        switch (choice) {
            case "1":
                System.out.println("Select a production: ");
                Production production = null;
                if (user.getAccountType().equals(AccountType.CONTRIBUTOR)) {
                    int i = 1;
                    for (Object prod : ((Contributor) user).prodOrActors) {
                        if (prod instanceof Movie) {
                            System.out.println(i + ") " + ((Movie) prod).getTitle());
                            i++;
                        } else if (prod instanceof Series) {
                            System.out.println(i + ") " + ((Series) prod).getTitle());
                            i++;
                        }
                    }
                    int choiceprod = scanner.nextInt();
                    scanner.nextLine();
                    int j = 0;
                    for (Object prod : ((Contributor) user).prodOrActors) {
                        if (prod instanceof Movie) {
                            if (j == choiceprod - 1) {
                                production = (Movie) prod;
                                break;
                            }
                            j++;
                        } else if (prod instanceof Series) {
                            if (j == choiceprod - 1) {
                                production = (Series) prod;
                                break;
                            }
                            j++;
                        }
                    }
                } else if (user.getAccountType().equals(AccountType.ADMIN)) {
                    int i = 1;
                    for (Movie movie : movies) {
                        System.out.println(i + ") " + movie.getTitle());
                        i++;
                    }
                    for (Series serie : series) {
                        System.out.println(i + ") " + serie.getTitle());
                        i++;
                    }
                    int choiceprod = scanner.nextInt();
                    scanner.nextLine();
                    if (choiceprod <= movies.size())
                        production = movies.get(choiceprod - 1);
                    else
                        production = series.get(choiceprod - movies.size() - 1);
                }
                System.out.println("1) Update title");
                System.out.println("2) Update description");
                System.out.println("3) Update genres");
                System.out.println("4) Update directors");
                System.out.println("5) Update actors");
                System.out.println("6) Update launch year");
                if (production instanceof Movie) {
                    System.out.println("7) Update duration");
                    System.out.println("8) Go back to menu");
                    String choiceupdate = scanner.nextLine();
                    Movie movie = (Movie) production;
                    System.out.println(movie.getTitle());
                    switch (choiceupdate) {
                        case "1":
                            System.out.println("Enter the new title: ");
                            System.out.println("Old title: " + movie.getTitle());
                            String newtitle = scanner.nextLine();
                            movie.setTitle(newtitle);
                            System.out.println("Title updated successfully");
                            updateProduction(user);
                            break;
                        case "2":
                            System.out.println("Enter the new description: ");
                            System.out.println("Old description: " + movie.getDescription());
                            String newdescription = scanner.nextLine();
                            movie.setDescription(newdescription);
                            System.out.println("Description updated successfully");
                            break;
                        case "3":
                            System.out.println("Enter the number of genres: ");
                            int numgen = scanner.nextInt();
                            scanner.nextLine();
                            List<Genre> genres = new ArrayList<>();
                            for (int j = 0; j < numgen; j++) {
                                System.out.println("Enter the name of the genre: ");
                                String genrename = scanner.nextLine();
                                for (Genre genre : Genre.values())
                                    if (genre.name().equals(genrename))
                                        genres.add(genre);
                            }
                            movie.setGenres(genres);
                            System.out.println("Genres updated successfully");
                            updateProduction(user);
                            break;
                        case "4":
                            System.out.println("Enter the number of directors: ");
                            int numdir = scanner.nextInt();
                            scanner.nextLine();
                            List<String> directors = new ArrayList<>();
                            for (int j = 0; j < numdir; j++) {
                                System.out.println("Enter the name of the director: ");
                                String directorname = scanner.nextLine();
                                directors.add(directorname);
                            }
                            production.setDirectors(directors);
                            System.out.println("Directors updated successfully");
                            updateProduction(user);
                            break;
                        case "5":
                            System.out.println("Enter the number of actors: ");
                            int numactors = scanner.nextInt();
                            scanner.nextLine();
                            List<String> actors = new ArrayList<>();
                            for (int j = 0; j < numactors; j++) {
                                System.out.println("Enter the name of the actor: ");
                                String actorname = scanner.nextLine();
                                actors.add(actorname);
                            }
                            production.setActors(actors);
                            System.out.println("Actors updated successfully");
                            updateProduction(user);
                            break;
                        case "6":
                            System.out.println("Enter the new duration: ");
                            System.out.println("Old duration: " + movie.getDuration());
                            long newduration = scanner.nextLong();
                            scanner.nextLine();
                            movie.setDuration(newduration);
                            System.out.println("Duration updated successfully");
                            updateProduction(user);
                            break;
                        case "7":
                            System.out.println("Enter the new launch year: ");
                            System.out.println("Old launch year: " + movie.getLaunchYear());
                            long newlaunchyear = scanner.nextLong();
                            scanner.nextLine();
                            movie.setLaunchYear(newlaunchyear);
                            System.out.println("Launch year updated successfully");
                            updateProduction(user);
                            break;
                        case "8":
                            returnToMenu(user);
                            break;
                        default:
                            System.out.println("Invalid choice");
                            updateProduction(user);
                            break;
                    }
                } else {
                    System.out.println("7) Update number of seasons");
                    System.out.println("8) Update seasons");
                    System.out.println("9) Go back to menu");
                    String choiceupdate = scanner.nextLine();
                    Series serie = (Series) production;
                    System.out.println(serie.getTitle());
                    switch (choiceupdate) {
                        case "1":
                            System.out.println("Enter the new title: ");
                            System.out.println("Old title: " + serie.getTitle());
                            String newtitle = scanner.nextLine();
                            serie.setTitle(newtitle);
                            System.out.println("Title updated successfully");
                            updateProduction(user);
                            break;
                        case "2":
                            System.out.println("Enter the new description: ");
                            System.out.println("Old description: " + serie.getDescription());
                            String newdescription = scanner.nextLine();
                            serie.setDescription(newdescription);
                            System.out.println("Description updated successfully");
                            break;
                        case "3":
                            System.out.println("Enter the number of genres: ");
                            int numgen = scanner.nextInt();
                            scanner.nextLine();
                            List<Genre> genres = new ArrayList<>();
                            for (int j = 0; j < numgen; j++) {
                                System.out.println("Enter the name of the genre: ");
                                String genrename = scanner.nextLine();
                                for (Genre genre : Genre.values())
                                    if (genre.name().equals(genrename))
                                        genres.add(genre);
                            }
                            serie.setGenres(genres);
                            System.out.println("Genres updated successfully");
                            updateProduction(user);
                            break;
                        case "4":
                            System.out.println("Enter the number of directors: ");
                            int numdir = scanner.nextInt();
                            scanner.nextLine();
                            List<String> directors = new ArrayList<>();
                            for (int j = 0; j < numdir; j++) {
                                System.out.println("Enter the name of the director: ");
                                String directorname = scanner.nextLine();
                                directors.add(directorname);
                            }
                            production.setDirectors(directors);
                            System.out.println("Directors updated successfully");
                            updateProduction(user);
                            break;
                        case "5":
                            System.out.println("Enter the number of actors: ");
                            int numactors = scanner.nextInt();
                            scanner.nextLine();
                            List<String> actors = new ArrayList<>();
                            for (int j = 0; j < numactors; j++) {
                                System.out.println("Enter the name of the actor: ");
                                String actorname = scanner.nextLine();
                                actors.add(actorname);
                            }
                            production.setActors(actors);
                            System.out.println("Actors updated successfully");
                            updateProduction(user);
                            break;
                        case "6":
                            System.out.println("Enter the new launch year: ");
                            System.out.println("Old launch year: " + serie.getLaunchYear());
                            long newlaunchyear = scanner.nextLong();
                            scanner.nextLine();
                            serie.setLaunchYear(newlaunchyear);
                            System.out.println("Launch year updated successfully");
                            updateProduction(user);
                            break;
                        case "7":
                            System.out.println("Enter the new number of seasons: ");
                            System.out.println("Old number of seasons: " + serie.getNrSeasons());
                            long newnrseasons = scanner.nextLong();
                            scanner.nextLine();
                            serie.setNrSeasons(newnrseasons);
                            System.out.println("Number of seasons updated successfully");
                            updateProduction(user);
                            break;
                        case "8":
                            System.out.println("Choose a season: ");
                            int j = 1;
                            for (Map.Entry<String, List<Episode>> entry : serie.getSeasons().entrySet()) {
                                System.out.println(j + ") " + entry.getKey());
                                j++;
                            }
                            int choiceseason = scanner.nextInt();
                            scanner.nextLine();
                            j = 1;
                            String season = null;
                            for (Map.Entry<String, List<Episode>> entry : serie.getSeasons().entrySet()) {
                                if (j == choiceseason) {
                                    season = entry.getKey();
                                    break;
                                }
                                j++;
                            }
                            System.out.println("Choose an episode: ");
                            j = 1;
                            for (Episode episode : serie.getSeasons().get(season)) {
                                System.out.println(j + ") " + episode.episodeName);
                                j++;
                            }
                            int choiceepisode = scanner.nextInt();
                            scanner.nextLine();
                            j = 1;
                            Episode episode = null;
                            for (Episode episode1 : serie.getSeasons().get(season)) {
                                if (j == choiceepisode) {
                                    episode = episode1;
                                    break;
                                }
                                j++;
                            }
                            System.out.println("1) Update name");
                            System.out.println("2) Update duration");
                            System.out.println("3) Go back to menu");
                            String choiceupdateepisode = scanner.nextLine();
                            switch (choiceupdateepisode) {
                                case "1":
                                    System.out.println("Enter the new name: ");
                                    System.out.println("Old name: " + episode.episodeName);
                                    String newname = scanner.nextLine();
                                    episode.episodeName = newname;
                                    System.out.println("Name updated successfully");
                                    updateProduction(user);
                                    break;
                                case "2":
                                    System.out.println("Enter the new duration: ");
                                    System.out.println("Old duration: " + episode.episodeDuration);
                                    long newduration = scanner.nextLong();
                                    scanner.nextLine();
                                    episode.episodeDuration = newduration;
                                    System.out.println("Duration updated successfully");
                                    updateProduction(user);
                                    break;
                                case "3":
                                    returnToMenu(user);
                                    break;
                                default:
                                    System.out.println("Invalid choice");
                                    updateProduction(user);
                                    break;
                            }
                            break;
                        case "9":
                            returnToMenu(user);
                            break;
                        default:
                            System.out.println("Invalid choice");
                            updateProduction(user);
                            break;
                    }
                }

                break;
            case "2":
                returnToMenu(user);
                break;
            default:
                System.out.println("Invalid choice");
                updateProduction(user);
                break;
        }
    }

    public void solveRequest(User user) {
        System.out.println("Select a request: ");
        int i = 1;
        int hasRequests = 0;
        for (Request request : requests) {
            if (request.getSolver().equals("ADMIN") && user.getAccountType().equals(AccountType.ADMIN)) {
                hasRequests = 1;
                System.out.println(i + ") " + request.getDescription());
                i++;
            }
            if (request.getSolver().equals(user.getUsername())) {
                hasRequests = 1;
                System.out.println(i + ") " + request.getDescription());
                i++;
            }
        }
        if (hasRequests == 0) {
            System.out.println("You have no requests");
            System.out.println("0) Go back to menu");
            Scanner scanner = new Scanner(System.in);
            String choiceback = scanner.nextLine();
            switch (choiceback) {
                case "0":
                    returnToMenu(user);
                    break;
                default:
                    System.out.println("Invalid choice");
                    solveRequest(user);
                    break;
            }
        }
        Scanner scanner = new Scanner(System.in);
        int choicereq = scanner.nextInt();
        scanner.nextLine();
        i = 1;
        Request requestCURR = null;
        for (Request request : requests) {
            System.out.println(request.getSolver());
            System.out.println(request.getDescription());
            if (request.getSolver().equals(user.getUsername()) ||
                    request.getSolver().equals("ADMIN") && user.getAccountType().equals(AccountType.ADMIN)) {
                if (choicereq == i) {
                    requestCURR = request;
                    break;
                }
                i++;
            }
        }
        System.out.println("1) Marked as solved");
        System.out.println("2) Reject request");
        System.out.println("3) Go back to menu");
        String choice = scanner.nextLine();
        switch (choice) {
            case "1":
                System.out.println("Enter the solution: ");
                String solution = scanner.nextLine();

                if (requestCURR.getRequestTypes().equals(Request.RequestTypes.DELETE_ACCOUNT)) {
                    for (User user1 : users) {
                        if (user1.getUsername().equals(requestCURR.getUsername())) {
                            users.remove(user1);
                            break;
                        }
                    }
                }

                requestCURR.notifyObservers("* Your request has been solved." + "\n" + "Solution: " + solution);
                if (requestCURR.getRequestTypes().equals(Request.RequestTypes.MOVIE_ISSUE) ||
                        requestCURR.getRequestTypes().equals(Request.RequestTypes.ACTOR_ISSUE)) {
                    for (User user1 : users)
                        if (user1.getUsername().equals(requestCURR.getUsername()) &&
                                !user1.getAccountType().equals(AccountType.ADMIN)) {
                            user1.setExperienceStrategy(new RequestSolvedStrategy());
                            user1.updateExp();
                        }
                }

                System.out.println("Request solved successfully");
                requests.remove(requestCURR);
                returnToMenu(user);
                break;
            case "2":
                System.out.println("Enter the reason: ");
                String reason = scanner.nextLine();

                requestCURR.notifyObservers("* Your request has been rejected." + "\n" + "Reason: " + reason);
                requestCURR.getUsername();
                requests.remove(requestCURR);
                returnToMenu(user);
                break;
            case "3":
                returnToMenu(user);
                break;
            default:
                System.out.println("Invalid choice");
                solveRequest(user);
                break;
        }
    }

    public void showNotif(User user) {
        if (user.getNotifications().isEmpty()) {
            System.out.println("You have no notifications");
            System.out.println("1) Go back to menu");
            Scanner scanner = new Scanner(System.in);
            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    returnToMenu(user);
                    break;
                default:
                    System.out.println("Invalid choice");
                    showNotif(user);
                    break;
            }
        }
        System.out.println("Notifications: ");
        user.printNotifications();
        System.out.println("1) Go back to menu");
        System.out.println("2) Clear notifications");
        Scanner scanner = new Scanner(System.in);
        String choice = scanner.nextLine();
        switch (choice) {
            case "1":
                returnToMenu(user);
                break;
            case "2":
                user.setNotifications(new ArrayList<>());
                System.out.println("Notifications cleared successfully");
                returnToMenu(user);
                break;
            default:
                System.out.println("Invalid choice");
                showNotif(user);
                break;
        }
    }

    public void AdminMenu(User user) {
        System.out.println("1) View productions details");
        System.out.println("2) View actors details");
        System.out.println("3) View notifications");
        System.out.println("4) Search for actor/movie/series");
        System.out.println("5) Add/Delete actor/movie/series to/from favorites");
        System.out.println("6) Add/Delete user");
        System.out.println("7) Add/Delete actor/movie/series from system");
        System.out.println("8) Update Movie Details");
        System.out.println("9) Update Actor Details");
        System.out.println("10) Solve a request");
        System.out.println("11) Logout");

        Scanner scanner = new Scanner(System.in);
        String choice = scanner.nextLine();
        switch (choice) {
            case "1":
                displayProductions(user);
                break;
            case "2":
                displayActors(user);
                break;
            case "3":
                showNotif(user);
                break;
            case "4":
                searchActororProd(user);
                break;
            case "5":
                addDeletefavorite(user);
                break;
            case "6":
                addDeleteUserfromSystem(user);
                break;
            case "7":
                addDeleteActororProdfromSytem(user);
                break;
            case "8":
                updateProduction(user);
                break;
            case "9":
                updateActor(user);
                break;
            case "10":
                solveRequest(user);
                break;
            case "11":
                System.out.println("1) Log in with another account.");
                System.out.println("2) Close application.");
                String choiceout = scanner.nextLine();
                switch (choiceout) {
                    case "1":
                        user = login(users);
                        if (user.getAccountType().equals(AccountType.ADMIN))
                            AdminMenu(user);
                        else if (user.getAccountType().equals(AccountType.CONTRIBUTOR))
                            ContributorMenu(user);
                        else if (user.getAccountType().equals(AccountType.REGULAR))
                            RegularMenu(user);
                        break;
                    case "2":
                        user.logout();
                        break;
                    default:
                        System.out.println("Invalid choice");
                        break;
                }
            default:
                System.out.println("Invalid choice");
                break;
        }
    }

    public void ContributorMenu(User user) {
        System.out.println("1) View productions details");
        System.out.println("2) View actors details");
        System.out.println("3) View notifications");
        System.out.println("4) Search for actor/movie/series");
        System.out.println("5) Add/Delete actor/movie/series to/from favorites");
        System.out.println("6) Add/Delete a request");
        System.out.println("7) Add/Delete actor/movie/series from system");
        System.out.println("8) Update Movie Details");
        System.out.println("9) Update Actor Details");
        System.out.println("10) Solve a request");
        System.out.println("11) Logout");

        Scanner scanner = new Scanner(System.in);
        String choice = scanner.nextLine();
        switch (choice) {
            case "1":
                displayProductions(user);
                break;
            case "2":
                displayActors(user);
                break;
            case "3":
                showNotif(user);
                break;
            case "4":
                searchActororProd(user);
                break;
            case "5":
                addDeletefavorite(user);
                break;
            case "6":
                addDeleteRequest(user);
                break;
            case "7":
                addDeleteActororProdfromSytem(user);
                break;
            case "8":
                updateProduction(user);
                break;
            case "9":
                updateActor(user);
                break;
            case "10":
                solveRequest(user);
                break;
            case "11":
                System.out.println("1) Log in with another account.");
                System.out.println("2) Close application.");
                String choiceout = scanner.nextLine();
                switch (choiceout) {
                    case "1":
                        user = login(users);
                        if (user.getAccountType().equals(AccountType.ADMIN))
                            AdminMenu(user);
                        else if (user.getAccountType().equals(AccountType.CONTRIBUTOR))
                            ContributorMenu(user);
                        else if (user.getAccountType().equals(AccountType.REGULAR))
                            RegularMenu(user);
                        break;
                    case "2":
                        user.logout();
                        break;
                    default:
                        System.out.println("Invalid choice");
                        break;
                }
            default:
                System.out.println("Invalid choice");
                break;
        }
    }

    public void RegularMenu(User user) {
        System.out.println("1) View productions details");
        System.out.println("2) View actors details");
        System.out.println("3) View notifications");
        System.out.println("4) Search for actor/movie/series");
        System.out.println("5) Add/Delete actor/movie/series to/from favorites");
        System.out.println("6) Add/Delete a request");
        System.out.println("7) Add/Delete a rating to/from a production");
        System.out.println("8) Show account info");
        System.out.println("9) Logout");

        Scanner scanner = new Scanner(System.in);
        String choice = scanner.nextLine();
        switch (choice) {
            case "1":
                displayProductions(user);
                break;

            case "2":
                displayActors(user);
                break;
            case "3":
                showNotif(user);
                break;
            case "4":
                searchActororProd(user);
                break;
            case "5":
                addDeletefavorite(user);
                break;
            case "6":
                addDeleteRequest(user);
                break;
            case "7":
                addDeleteRating(user);
                break;

            case "8":
                user.displayInfo();
                returnToMenu(user);
                break;
            case "9":
                System.out.println("1) Log in with another account.");
                System.out.println("2) Close application.");
                String choiceout = scanner.nextLine();
                switch (choiceout) {
                    case "1":
                        user = login(users);
                        if (user.getAccountType().equals(AccountType.ADMIN))
                            AdminMenu(user);
                        else if (user.getAccountType().equals(AccountType.CONTRIBUTOR))
                            ContributorMenu(user);
                        else if (user.getAccountType().equals(AccountType.REGULAR))
                            RegularMenu(user);
                        break;
                    case "2":
                        user.logout();
                        break;
                    default:
                        System.out.println("Invalid choice");
                        break;
                }
            default:
                System.out.println("Invalid choice");
                break;
        }
    }

    private void loadProductionSystem() throws IOException, ParseException {
        JSONParser jsonparser = new JSONParser();
        Object production = jsonparser.parse(new FileReader("resources/production.json"));
        JSONArray prodArray = (JSONArray) production;
        for (Object prodObj : prodArray) {
            JSONObject prod = (JSONObject) prodObj;

            String title = (String) prod.get("title");
            String type = (String) prod.get("type");
            String description = (String) prod.get("plot");

            long launchYear = 0;
            if (prod.get("releaseYear") != null) {
                launchYear = (long) prod.get("releaseYear");
            }

            JSONArray directors = (JSONArray) prod.get("directors");
            JSONArray actors = (JSONArray) prod.get("actors");
            JSONArray genres = (JSONArray) prod.get("genres");
            JSONArray ratings = (JSONArray) prod.get("ratings");

            List<String> directorsList = new ArrayList<>();
            for (Object directorObj : directors) {
                directorsList.add((String) directorObj);
            }

            List<String> actorsList = new ArrayList<>();
            for (Object actorObj : actors) {
                actorsList.add((String) actorObj);
            }

            List<Genre> genresList = new ArrayList<>();
            for (Object genreObj : genres) {
                genresList.add(Genre.valueOf(((String) genreObj).toUpperCase()));
            }

            List<Rating> ratingsList = getRatingsList(ratings);

            if (type.equals("Movie")) {

                String durationString = (String) prod.get("duration");
                String[] parts = durationString.split(" ");
                long duration = Long.parseLong(parts[0]);

                Movie movie = new Movie(title, directorsList, actorsList, genresList, ratingsList,
                        description, duration, launchYear);
            } else if (type.equals("Series")) {

                JSONObject seasonsObj = (JSONObject) prod.get("seasons");
                Map<String, List<Episode>> seasons = new HashMap<>();

                for (Object key : seasonsObj.keySet()) {
                    String seasonName = (String) key;
                    JSONArray episodesArray = (JSONArray) seasonsObj.get(seasonName);

                    List<Episode> episodes = new ArrayList<>();
                    for (Object episodeObj : episodesArray) {

                        JSONObject episodeJson = (JSONObject) episodeObj;

                        String episodeName = (String) episodeJson.get("episodeName");

                        String durationString = (String) episodeJson.get("duration");
                        String[] parts = durationString.split(" ");
                        long duration = Long.parseLong(parts[0]);

                        Episode episode = new Episode(episodeName, duration);
                        episodes.add(episode);
                    }
                    seasons.put(seasonName, episodes);
                }

                Series newseries = new Series(title, directorsList, actorsList, genresList, ratingsList,
                        description, launchYear, seasons.size(), seasons);
            }
        }
    }

    private List<Rating> getRatingsList(JSONArray ratings) {
        List<Rating> ratingsList = new ArrayList<>();
        for (Object ratingObj : ratings) {
            JSONObject rating = (JSONObject) ratingObj;
            String username = (String) rating.get("username");
            long grade = 0;
            if (rating.get("rating") != null) {
                grade = (long) rating.get("rating");
            }
            String comment = (String) rating.get("comment");
            Rating rating1 = new Rating(username, grade, comment);
            ratingsList.add(rating1);
        }
        return ratingsList;
    }

    private void loadAccountsSystem() throws IOException, ParseException {
        JSONParser jsonparser = new JSONParser();
        Object acc = jsonparser.parse(new FileReader("resources/accounts.json"));
        JSONArray accArray = (JSONArray) acc;
        for (Object userObj : accArray) {
            JSONObject user = (JSONObject) userObj;
            String username = (String) user.get("username");
            JSONObject information = (JSONObject) user.get("information");
            String name = (String) information.get("name");
            String country = (String) information.get("country");
            long age = (long) information.get("age");
            String gender = (String) information.get("gender");

            JSONObject credentials = (JSONObject) information.get("credentials");
            String email = (String) credentials.get("email");
            String password = (String) credentials.get("password");

            DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;
            String birthDateString = (String) information.get("birthDate");
            LocalDate birthDate = LocalDate.parse(birthDateString, formatter);
            LocalDateTime birthDateTime = birthDate.atStartOfDay();

            User.Information info = new User.Information.Builder()
                    .setCredentials(new Credentials(email, password))
                    .setName(name)
                    .setAge(age)
                    .setCountry(country)
                    .setGender(gender.charAt(0))
                    .setBirthDate(birthDateTime)
                    .build();

            List<String> notifications = new ArrayList<>();
            JSONArray notificationsArray = (JSONArray) user.get("notifications");
            if (notificationsArray != null) {
                for (Object notifObj : notificationsArray) {
                    String notif = (String) notifObj;
                    notifications.add(notif);
                }
            }

            String experienceString = (String) user.get("experience");
            long experience = 0;
            if (experienceString != null)
                experience = Long.parseLong(experienceString);

            if (user.get("userType").equals("Regular")) {
                UserFactory userFactory = new UserFactory();
                userFactory.createUser(AccountType.REGULAR, username, info);
                regulars.get(regulars.size() - 1).setUsername(username);
                regulars.get(regulars.size() - 1).setExperience(experience);
                if (notificationsArray != null) {
                    regulars.get(regulars.size() - 1).setNotifications(notifications);
                }

                users.add(regulars.get(regulars.size() - 1));
                User userreg = regulars.get(regulars.size() - 1);
                for (Movie movie : movies)
                    for (Rating rating : movie.getRatings())
                        if (rating.getUsername().equals(userreg.getUsername()))
                            userreg.addReviewdProduction(movie);
                for (Series serie : series)
                    for (Rating rating : serie.getRatings())
                        if (rating.getUsername().equals(userreg.getUsername()))
                            userreg.addReviewdProduction(serie);


            } else if (user.get("userType").equals("Contributor")) {

                UserFactory userFactory = new UserFactory();
                userFactory.createUser(AccountType.CONTRIBUTOR, username, info);
                contributors.get(contributors.size() - 1).setUsername(username);
                contributors.get(contributors.size() - 1).setExperience(experience);
                if (notificationsArray != null)
                    contributors.get(contributors.size() - 1).setNotifications(notifications);

                users.add(contributors.get(contributors.size() - 1));


                JSONArray productionsContribution = (JSONArray) user.get("productionsContribution");
                if (productionsContribution != null) {
                    for (Object contrObj : productionsContribution) {
                        String contrTitle = (String) contrObj;
                        for (Movie movie : movies)
                            if (movie.getTitle().equals(contrTitle)) {
                                contributors.get(contributors.size() - 1).prodOrActors.add(movie);
                                movie.registerObserver(contributors.get(contributors.size() - 1));
                            }
                        for (Series serie : series)
                            if (serie.getTitle().equals(contrTitle)) {
                                contributors.get(contributors.size() - 1).prodOrActors.add(serie);
                                serie.registerObserver(contributors.get(contributors.size() - 1));
                            }

                    }
                }

                JSONArray actorsContribution = (JSONArray) user.get("actorsContribution");
                if (actorsContribution != null) {
                    for (Object contrAct : actorsContribution) {
                        String favorite = (String) contrAct;
                        for (Actor actor : actors) {
                            if (actor.name.equals(favorite)) {
                                contributors.get(contributors.size() - 1).prodOrActors.add(actor);
                            }
                        }
                    }
                }


            } else if (user.get("userType").equals("Admin")) {

                UserFactory userFactory = new UserFactory();
                userFactory.createUser(AccountType.ADMIN, username, info);
                admins.get(admins.size() - 1).setUsername(username);
                admins.get(admins.size() - 1).setExperience(experience);
                if (notificationsArray != null)
                    admins.get(admins.size() - 1).setNotifications(notifications);

                users.add(admins.get(admins.size() - 1));

                JSONArray productionsContribution = (JSONArray) user.get("productionsContribution");
                if (productionsContribution != null) {
                    for (Object contrObj : productionsContribution) {
                        String contrTitle = (String) contrObj;
                        for (Movie movie : movies)
                            if (movie.getTitle().equals(contrTitle)) {
                                admins.get(admins.size() - 1).prodOrActors.add(movie);
                                movie.registerObserver(admins.get(admins.size() - 1));
                            }
                        for (Series serie : series)
                            if (serie.getTitle().equals(contrTitle)) {
                                admins.get(admins.size() - 1).prodOrActors.add(serie);
                                serie.registerObserver(admins.get(admins.size() - 1));
                            }
                    }
                }

                JSONArray actorsContribution = (JSONArray) user.get("actorsContribution");
                if (actorsContribution != null) {
                    for (Object contrAct : actorsContribution) {
                        String favorite = (String) contrAct;
                        for (Actor actor : actors) {
                            if (actor.name.equals(favorite)) {
                                admins.get(admins.size() - 1).prodOrActors.add(actor);
                            }
                        }
                    }
                }
            }

            JSONArray favoriteProductions = (JSONArray) user.get("favoriteProductions");
            if (favoriteProductions != null) {
                for (Object favoriteObj : favoriteProductions) {
                    String favoriteTitle = (String) favoriteObj;
                    for (Movie movie : movies) {
                        if (movie.getTitle().equals(favoriteTitle)) {
                            if (user.get("userType").equals("Regular"))
                                regulars.get(regulars.size() - 1).addFavorite(movie);
                            else if (user.get("userType").equals("Contributor"))
                                contributors.get(contributors.size() - 1).addFavorite(movie);
                            else if (user.get("userType").equals("Admin"))
                                admins.get(admins.size() - 1).addFavorite(movie);
                        }
                    }
                    for (Series serie : series) {
                        if (serie.getTitle().equals(favoriteTitle)) {
                            if (user.get("userType").equals("Regular"))
                                regulars.get(regulars.size() - 1).addFavorite(serie);
                            else if (user.get("userType").equals("Contributor"))
                                contributors.get(contributors.size() - 1).addFavorite(serie);
                            else if (user.get("userType").equals("Admin"))
                                admins.get(admins.size() - 1).addFavorite(serie);
                        }
                    }
                }
            }

            JSONArray favoriteActors = (JSONArray) user.get("favoriteActors");
            if (favoriteActors != null) {
                for (Object favoriteAct : favoriteActors) {
                    String favorite = (String) favoriteAct;
                    for (Actor actor : actors) {
                        if (actor.name.equals(favorite)) {
                            if (user.get("userType").equals("Regular"))
                                regulars.get(regulars.size() - 1).addFavorite(actor);
                            else if (user.get("userType").equals("Contributor"))
                                contributors.get(contributors.size() - 1).addFavorite(actor);
                            else if (user.get("userType").equals("Admin"))
                                admins.get(admins.size() - 1).addFavorite(actor);
                        }
                    }
                }
            }

        }
    }

    private void loadActorsSystem() throws IOException, ParseException {
        JSONParser jsonparser = new JSONParser();
        Object actors = jsonparser.parse(new FileReader("resources/actors.json"));
        JSONArray actorsArray = (JSONArray) actors;
        for (Object actorObj : actorsArray) {
            JSONObject actor = (JSONObject) actorObj;

            String name = (String) actor.get("name");
            String bio = (String) actor.get("biography");

            JSONArray performances = (JSONArray) actor.get("performances");
            List<Map.Entry<String, String>> performanceslist = getPerformanceslist(performances);

            Actor newactor = new Actor(name, bio, performanceslist);
        }
    }

    private static List<Map.Entry<String, String>> getPerformanceslist(JSONArray performances) {
        List<Map.Entry<String, String>> performanceslist = new ArrayList<>();
        for (Object performanceObj : performances) {
            JSONObject performance = (JSONObject) performanceObj;
            String title = (String) performance.get("title");
            String performanceType = (String) performance.get("type");
            Map.Entry<String, String> movie = new AbstractMap.SimpleEntry<>(title, performanceType);
            performanceslist.add(movie);
        }
        return performanceslist;
    }

    private void loadRequestsSystem() throws IOException, ParseException {
        JSONParser jsonparser = new JSONParser();
        Object requests = jsonparser.parse(new FileReader("resources/requests.json"));
        JSONArray requestsArray = (JSONArray) requests;
        for (Object requestObj : requestsArray) {
            JSONObject request = (JSONObject) requestObj;

            String type = (String) request.get("type");
            String description = (String) request.get("description");
            String username = (String) request.get("username");
            String solver = (String) request.get("to");

            String createdDateString = (String) request.get("createdDate");
            LocalDateTime createdDate = LocalDateTime.parse(createdDateString);


            Request.RequestTypes requestType = null;
            if (type.equals("DELETE_ACCOUNT")) {
                requestType = Request.RequestTypes.DELETE_ACCOUNT;
                RequestHolder newrequest = new RequestHolder(username, description, requestType, null, "ADMIN");
                for (User user1 : users)
                    if (user1.getUsername().equals(username))
                        IMDB.getInstance().requests.get(IMDB.getInstance().requests.size() - 1).registerObserver(user1);
                newrequest.setcreatedDate(createdDate);
            } else if (type.equals("ACTOR_ISSUE")) {
                String actorName = (String) request.get("actorName");
                requestType = Request.RequestTypes.ACTOR_ISSUE;
                Request newrequest = new Request(username, description, requestType, actorName, solver);
                for (User user1 : users)
                    if (user1.getUsername().equals(username))
                        IMDB.getInstance().requests.get(IMDB.getInstance().requests.size() - 1).registerObserver(user1);
                newrequest.setcreatedDate(createdDate);
            } else if (type.equals("MOVIE_ISSUE")) {
                requestType = Request.RequestTypes.MOVIE_ISSUE;
                String movieTitle = (String) request.get("movieTitle");
                Request newrequest = new Request(username, description, requestType, movieTitle, solver);
                for (User user1 : users)
                    if (user1.getUsername().equals(username))
                        IMDB.getInstance().requests.get(IMDB.getInstance().requests.size() - 1).registerObserver(user1);
                newrequest.setcreatedDate(createdDate);
            } else if (type.equals("OTHERS")) {
                requestType = Request.RequestTypes.OTHERS;
                RequestHolder newrequest = new RequestHolder(username, description, requestType, null, "ADMIN");
                for (User user1 : users)
                    if (user1.getUsername().equals(username))
                        IMDB.getInstance().requests.get(IMDB.getInstance().requests.size() - 1).registerObserver(user1);
                newrequest.setcreatedDate(createdDate);
            }

        }
    }

    public static void main(String[] args) throws IOException, ParseException {
        try {
            IMDB.getInstance().run();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public void run() throws IOException, ParseException {

        loadProductionSystem();
        loadActorsSystem();
        loadAccountsSystem();
        loadRequestsSystem();

        for (int i = 0; i < movies.size(); i++) {
            String title = movies.get(i).getTitle();
            String imageUrl = "resources/productions/" + title.replace(" ", "_") + ".jpg";
            movies.get(i).setImageURL(imageUrl);
            if (movies.get(i).title.equals("Mad Max: Fury Road"))
                movies.get(i).setImageURL("resources/productions/Mad_Max_Fury_Road.jpg");
            if (movies.get(i).title.equals("The Lord of the Rings: The Return of the King"))
                movies.get(i).setImageURL("resources/productions/The_Lord_of_the_Rings_The_Return_of_the_King.jpg");
        }

        for (int i = 0; i < series.size(); i++) {
            String title = series.get(i).getTitle();
            String imageUrl = "resources/productions/" + title.replace(" ", "_") + ".jpg";
            series.get(i).setImageURL(imageUrl);
        }

        for (int i = 0; i < actors.size(); i++) {
            String name = actors.get(i).name;
            String imageUrl = "resources/productions/" + name.replace(" ", "_") + ".jpg";
            actors.get(i).setImageURL(imageUrl);
        }

        System.out.println("Choose how use the application: ");
        System.out.println("1) Terminal");
        System.out.println("2) Application");
        Scanner scanner = new Scanner(System.in);
        String choice = scanner.nextLine();
        switch (choice) {
            case "1":
                User user = login(users);
                if (user.getAccountType().equals(AccountType.ADMIN))
                    AdminMenu(user);
                else if (user.getAccountType().equals(AccountType.CONTRIBUTOR))
                    ContributorMenu(user);
                else if (user.getAccountType().equals(AccountType.REGULAR))
                    RegularMenu(user);
                break;
            case "2":
                LoginWindow window = new LoginWindow();
                break;
            default:
                System.out.println("Invalid choice");
                run();
                break;
        }
    }
}