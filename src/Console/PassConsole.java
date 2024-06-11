package Console;

import Logic.PassKeeper;
import Logic.Marking;

import java.util.ArrayList;
import java.util.Scanner;

public class PassConsole {
    private PassKeeper passKeeper;
    private WorkWirhExternalFields workWirhExternalFields;
    public PassConsole(){
        passKeeper = new PassKeeper();
        workWirhExternalFields = new WorkWirhExternalFields(passKeeper);

        loop();
    }
    public void loop(){
        System.out.println("\n\n\t\tMain loop\n" +
                "Choose action:\n" +
                "1 - add marking\n" +
                "2 - get marking\n" +
                "3 - show all markings\n" +
                "4 - select certain marking by id (list number)\n" +
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
            case 4:
                workWirhExternalFields.main();
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
        if (passKeeper.addMarking(marking)) {
            System.out.println("Marking successfully added");
        }
        else
            System.out.println("Marking isn't added");
    }
    public void getMarking(){
        Scanner in = new Scanner(System.in);

        System.out.print("Resource: ");
        String resource = in.nextLine();

        ArrayList<Marking> passwordsMarkingList = passKeeper.getMarking(resource);
        for (Marking marking : passwordsMarkingList){
            System.out.println(marking.getPassword());
        }
    }
    public void showAllMarkings(){
        for (Marking marking : passKeeper.getMarkings()){
            String message = marking.getResource() + " | " +
                    marking.getPassword();

            System.out.println(message);
        }
    }
    class WorkWirhExternalFields{
        private PassKeeper passKeeper;
        public WorkWirhExternalFields(PassKeeper passKeeper){
            this.passKeeper = passKeeper;
        }
        public void main(){
            Scanner in = new Scanner(System.in);

            System.out.print("ID (list number):");
            int id = -1;
            try {
                id = Integer.parseInt(in.nextLine());

                Marking marking = passKeeper.getMarking(id);


                boolean isCycleRunning = true;
                while (isCycleRunning){
                    System.out.println("\n\n\t\tMarknig loop\n" +
                            "Choose marking action:\n" +
                            "1 - Show all info\n" +
                            "2 - Change Resource\n" +
                            "3 - Change Passowrd\n" +
                            "4 - Add external field\n" +
                            "5 - Delete external field\n" +
                            "6 - Change external field\n" +
                            "0 - Exit program");
                    switch (in.nextInt()){
                        case 1:
                            System.out.println(marking.toString());
                            break;
                        case 2:
                            changeResource(marking);
                            break;
                        case 3:
                            changePassword(marking);
                            break;
                        case 4:
                            addExternalField(marking);
                            break;
                        case 5:
                            deleteExternalField(marking);
                            break;
                        case 6:
                            rewriteExternalField(marking);
                            break;
                        case 0:
                            isCycleRunning = false;
                        default:
                            System.out.println("incorrect answer");
                    }
                }
            }
            catch (Exception e){
                System.out.println("Incorrect input. Please input number");
                return;
            }
        }
        public void changeResource(Marking marking){
            Scanner in = new Scanner(System.in);

            System.out.print("new resource name: ");
            String newResource = in.nextLine();

            //System.out.print("Pass: ");
            //String pass = in.nextLine();

            marking.setResource(newResource);
        }
        public void changePassword(Marking marking){
            Scanner in = new Scanner(System.in);

            System.out.print("New pass: ");
            String newPass = in.nextLine();

            marking.setPassword(newPass);
        }
        public void addExternalField(Marking marking){
            Scanner in = new Scanner(System.in);

            System.out.print("Key: ");
            String key = in.nextLine();

            System.out.print("Value: ");
            String value = in.nextLine();

            if (marking.addExternalField(key, value)) {
                System.out.println("External field successfully added");
            }
            else {
                System.out.println("External field isn't added");
            }
        }
        public void deleteExternalField(Marking marking) {
            Scanner in = new Scanner(System.in);

            System.out.print("Key: ");
            String key = in.nextLine();
            if (marking.deleteExternalField(key)){
                System.out.println("External field successfully deleted!!!");
            }
            else {
                System.out.println("SOME Problem");
            }
        }
        public void rewriteExternalField(Marking marking){
            Scanner in = new Scanner(System.in);

            System.out.print("Old key: ");
            String oldKey = in.nextLine();

            System.out.print("New key: ");
            String newKey = in.nextLine();

            System.out.print("Value: ");
            String value = in.nextLine();

            if (marking.changeExternalField(oldKey, newKey, value)) {
                System.out.println("External field successfully changed");
            }
            else {
                System.out.println("External field isn't changed. May wrong key name");
            }
        }
    }
}
