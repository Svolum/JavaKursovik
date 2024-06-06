package Console;

import DB.DBWorker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
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
                "1 - add record\n" +
                "2 - get record\n" +
                "3 - show all records\n" +
                "0 - exit program");
        Scanner in = new Scanner(System.in);
        switch (in.nextInt()){
            case 1:
                add();
                break;
            case 2:
                get();
                break;
            case 3:
                showAll();
                break;
            case 0:
                return;
            default:
                System.out.println("incorrect answer");
        }


        loop();
    }
    public void add(){
        Scanner in = new Scanner(System.in);

        System.out.print("Resource: ");
        String resource = in.nextLine();

        System.out.print("Pass: ");
        String pass = in.nextLine();

        if (db.addRecord(resource, pass)) {
            System.out.println("Record successfully added");
        }
        else
            System.out.println("Record isn't added");
    }
    public void get(){
        Scanner in = new Scanner(System.in);

        System.out.print("Resource: ");
        String resource = in.nextLine();

        ArrayList<String> passwords = db.getRecord(resource);
        for (String pass : passwords){
            System.out.println(pass);
        }
    }
    public void showAll(){
        HashMap<String, String> password = db.showDataBase();

        for (String resource : password.keySet()){
            String message = resource + " | ";
            message += password.get(resource);

            System.out.println(message);
        }
    }
}
