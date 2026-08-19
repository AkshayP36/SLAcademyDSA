package LLD2_DesignPatterns.PrototypeAndRegistry.Q1_PrototypeUser;


import java.util.HashMap;

public class UserPrototypeRegistryImp implements UserPrototypeRegistry {
    private HashMap<UserType, User> hashMap = new HashMap<>();

    @Override
    public void addPrototype(User user) {

        // if(user.getType().equals(UserType.ADMIN)){
        //     hashMap.put(UserType.ADMIN, user);
        // }else if(user.getType().equals(UserType.READER)){
        //     hashMap.put(UserType.READER, user);
        // }else if(user.getType().equals(UserType.WRITER)){
        //     hashMap.put(UserType.WRITER, user);
        // }

        hashMap.put(user.getType(), user);
    }

    @Override
    public User getPrototype(UserType type) {
        return hashMap.get(type);
    }

    @Override
    public User clone(UserType type) {
        User user = hashMap.get(type);
        return user.cloneObject();
    }

}

