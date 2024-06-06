package Logic;

import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class PassKeeper {
    private HashMap<String, String> passwords;
    public PassKeeper(){
        passwords = new HashMap<>();
    }
    public boolean isEmpty(){
        if (passwords.size() == 0)
            return true;
        return false;
    }
    public String get(String key){
        return passwords.get(key);
    }
    public boolean add(String key, String value){
        try {
            passwords.put(key, value);
        }
        catch (Exception e) {
            return false;
        }
        return true;
    }
    public Set<String> getKeySet(){
        return passwords.keySet();
    }
}
