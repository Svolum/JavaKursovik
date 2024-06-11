package Logic;

import java.util.*;

public class Marking {
    private String resource;
    private String password;
    private ArrayList<KeyValue> externalFields;
    public String getResource() {
        return resource;
    }
    public void setResource(String resource) {
        this.resource = resource;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public Marking(String resource, String password){
        this.resource = resource;
        this.password = password;

        this.externalFields = new ArrayList<>();
    }
    @Override
    public String toString() {
        String message = "\tName: " + resource + "\n";
        message += "pass:\t" + password + "\n";

        for (KeyValue kv : externalFields){
            message += kv.getKey() + ":\t" + kv.getValue() + "\n";
        }

        return message;
    }
    class KeyValue{
        private String key;
        private String value;
        public String getKey() {
            return key;
        }
        public void setKey(String key) {
            this.key = key;
        }
        public String getValue() {
            return value;
        }
        public void setValue(String value) {
            this.value = value;
        }
        public KeyValue(String key, String value){
            this.key = key;
            this.value = value;
        }
    }
    public boolean addExternalField(String key, String value){
        try {
            externalFields.add(new KeyValue(key, value));
        }
        catch (Exception e){
            System.out.println(this.getClass() + " addExternalField");
            return false;
        }
        return true;
    }
    public String getExternalFieldValue(String key){
        String result = null;
        for (KeyValue kv : externalFields){
            if (kv.getKey().equals(key)) {
                result = kv.getValue();
                break;
            }
        }
        return result;
    }
    public ArrayList<String> getExternalFieldKeys(){
        ArrayList<String> keyList = new ArrayList<>();
        for (KeyValue kv : externalFields){
            keyList.add(kv.getKey());
        }
        return keyList;
    }
    public boolean changeExternalField(String oldKey, String newKey, String value){
        try {
            for (KeyValue kv : externalFields){
                if (kv.getKey().equals(oldKey)){
                    kv.setKey(newKey);
                    kv.setValue(value);
                }
            }
            return false;
        }
        catch (Exception e){
            System.out.println(this.getClass() + " changeExternalField\n" + e.getMessage());
            return false;
        }
    }
    public boolean deleteExternalField(String key){
        // because of the try there in no !!!!return!!!
        try {
            for (KeyValue kv : externalFields){
                if (kv.getKey().equals(key)){
                    externalFields.remove(kv);
                    return true;
                }
            }
            return false;
        }
        catch (Exception e){
            System.out.println(this.getClass() + " deleteExternalField\\n" + e.getMessage());
            return false;
        }
    }
}
