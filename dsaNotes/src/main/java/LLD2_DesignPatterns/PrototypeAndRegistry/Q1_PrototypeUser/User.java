package LLD2_DesignPatterns.PrototypeAndRegistry.Q1_PrototypeUser;


public class User implements ClonableObject{
    private long userId;

    private String username;
    private String email;
    private String displayName;
    private int age;
    private UserType type;

    public User(){}

    public User(long userId, String username, String email, String displayName, int age, UserType type) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.displayName = displayName;
        this.age = age;
        this.type = type;
    }

    public long getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getDisplayName() {
        return displayName;
    }

    public int getAge() {
        return age;
    }

    public UserType getType() {
        return type;
    }


    public void setUserId(long userId) {
        this.userId = userId;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setType(UserType type) {
        this.type = type;
    }

    @Override
    public User cloneObject() {
        return new User(this.userId, this.username, this.email, this.displayName, this.age, this.type);
    }

}
