public class UserFactory {
    User createUser(AccountType accountType, String name, User.Information info) {
        switch (accountType) {
            case REGULAR:
                return new Regular(name, info);
            case CONTRIBUTOR:
                return new Contributor(name, info);
            case ADMIN:
                return new Admin(name, info);
            default:
                throw new IllegalArgumentException("Invalid account type: " + accountType);
        }
    }
}