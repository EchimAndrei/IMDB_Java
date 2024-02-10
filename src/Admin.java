public class Admin extends Staff {

    public Admin(String name, Information information){
        super(name, information, AccountType.ADMIN);
        IMDB.getInstance().admins.add(this);
    }

    public void addUser(Regular regular) {
        IMDB.getInstance().addRegularSystem(regular);
    }
    public void deleteUser(User user) {
        IMDB.getInstance().users.remove(user);
    }
}