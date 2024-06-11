package Logic;

import DB.DBWorker;

import java.util.*;

public class PassKeeper {
    private ArrayList<Marking> markings;
    private DBWorker db;
    public ArrayList<Marking> getMarkings() {
        return markings;
    }
    public PassKeeper(){
        db = new DBWorker();

        markings = db.getDataBase();
    }
    public  int getSize(){
        return markings.size();
    }
    public boolean isEmpty(){
        return markings.isEmpty();
    }
    // Get marking by RESOURCE
    public ArrayList getMarking(String resource){
        ArrayList<Marking> rezultList = new ArrayList<>();

        for (Marking marking : markings){
            if (marking.getResource().equals(resource)){
                rezultList.add(marking);
            }
        }
        return rezultList;
    }
    // Get marking by ID
    public Marking getMarking(int id){
        return markings.get(id);
    }
    public boolean addMarking(Marking marking){
        try {
            db.addMarking(marking);

            markings.add(marking);
        }
        catch (Exception e) {
            return false;
        }
        return true;
    }
}
