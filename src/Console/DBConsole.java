package Console;

import DB.DBWorker;
import Logic.Marking;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class DBConsole {
    private DBWorker db;
    public DBConsole(){
        db = new DBWorker();

        loop();

        db.closeDB();
    }
    public void loop(){
        System.out.println("\n\nChoose action:\n" +
                "1 - add marking\n" +
                "2 - get marking\n" +
                "3 - show all markings\n" +
                "0 - exit program");
        Scanner in = new Scanner(System.in);
        switch (in.nextInt()){
            case 1:
                addMarking();
                break;
            case 2:
                getMarking();
                break;
            case 3:
                showAllMarkings();
                break;
            case 0:
                return;
            default:
                System.out.println("incorrect answer");
        }


        loop();
    }
    public void addMarking(){
        Scanner in = new Scanner(System.in);

        System.out.print("Resource: ");
        String resource = in.nextLine();

        System.out.print("Pass: ");
        String pass = in.nextLine();

        Marking marking = new Marking(resource, pass);
        if (db.addMarking(marking)) {
            System.out.println("Record successfully added");
        }
        else
            System.out.println("Record isn't added");
    }
    public void getMarking(){
        Scanner in = new Scanner(System.in);

        System.out.print("Resource: ");
        String resource = in.nextLine();

        ArrayList<Marking> passwordsMarkingList = db.getMarking(resource);
        for (Marking marking : passwordsMarkingList){
            System.out.println(marking.getPassword());
        }
    }
    public void showAllMarkings(){
        ArrayList<Marking> markings = db.getDataBase();


        for (Marking marking : markings){
            String message = marking.getResource() + " | ";
            message += marking.getPassword();

            System.out.println(message);
        }
    }
}
