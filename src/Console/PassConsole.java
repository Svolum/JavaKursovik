package Console;

import Logic.PassKeeper;

import java.util.Scanner;

public class PassConsole {
    private PassKeeper passKeeper;
    public PassConsole(){
        passKeeper = new PassKeeper();
        loop();
    }
    private void loop(){
        System.out.println("Choose action:\n" +
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
    private void add(){
        Scanner in = new Scanner(System.in);

        System.out.print("Key: ");
        String key = in.nextLine();

        System.out.print("Value: ");
        String value = in.nextLine();

        if (passKeeper.add(key, value))
            System.out.println("Record successfully added!");
        else
            System.out.println("Record isn't added!");
    }
    private void get(){
        if (passKeeper.isEmpty()) {
            System.out.println("no passwords");
            return;
        }
        Scanner in = new Scanner(System.in);

        System.out.print("Key: ");
        String key = in.nextLine();

        String value = passKeeper.get(key);
        if (value != null)
            System.out.println("Value = " +value);
        else
            System.out.println("This key isn't exist");
    }
    private void showAll(){
        if (passKeeper.isEmpty()) {
            System.out.println("no passwords");
            return;
        }
        for (String key : passKeeper.getKeySet()){
            System.out.println("Key = " + key + "\n\tValue = " + passKeeper.get(key));
        }
    }
}
