package LLD2_DesignPatterns.PrototypeAndRegistry.Q1_PrototypeUser;


public interface UserPrototypeRegistry {

    void addPrototype(User user);

    User getPrototype(UserType type);

    User clone(UserType type);
}
