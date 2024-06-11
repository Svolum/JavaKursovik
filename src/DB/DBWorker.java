package DB;

import Logic.Marking;

import java.sql.*;
import java.util.ArrayList;

public class DBWorker {
    private final String URL = "jdbc:sqlite:DB\\passwordsDB.db";
    private final String DBTITLE = "password";
    private Connection connection;

    public DBWorker(){
        initDB();
    }
    private void initDB(){
        try {
            connection = DriverManager.getConnection(URL);

        } catch (SQLException e) {
            System.out.println("initDB-незагрузилась бд");
        }
    }
    public void closeDB(){
        try {
            connection.close();
        } catch (SQLException e) {
            System.out.println("closeDB-не закрылось 0_0");
        }
    }
    public ArrayList getDataBase() {
        ArrayList<Marking> markings = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();

            ResultSet rs = statement.executeQuery("select * from password");
            while (rs.next())
            {
                //String message =    rs.getInt(1) + " | " +
//                                    rs.getString(2) + " | " +
//                                    rs.getString(3);

//                System.out.println(message);
                markings.add(new Marking(rs.getString(2), rs.getString(3)));
                //password.put(rs.getString(2), rs.getString(3));
            }
            rs.close();

            statement.close();
        } catch (SQLException e) {
            System.out.println("showDataBase- при попытке получить данные из бд");
            System.out.println(e);
        }
        return markings;
    }
    public boolean addMarking(Marking marking){
        try {
            ////////////////
            if (connection.isClosed()){
                connection = DriverManager.getConnection(URL);
            }
            ///////////////

            PreparedStatement statement = connection.prepareStatement("INSERT INTO '" + DBTITLE + "' (resource, pass) VALUES (?, ?)");
            statement.setObject(1, marking.getResource());
            statement.setObject(2, marking.getPassword());

            statement.execute();

            statement.close();
        } catch (SQLException e) {
            System.out.println("addRecord-Какая-то ошибка с добавлением в базу");
            System.out.println(e);
            return false;
        }
        return true;
    }
    public ArrayList<Marking> getMarking(String resource){
        ArrayList<Marking> markings = new ArrayList<>();
        //ArrayList<String> passwords = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();

            ResultSet rs = statement.executeQuery("select * from " + DBTITLE + " WHERE (resource = '" + resource + "')");
            while (rs.next())
            {
                //String message =    rs.getInt(1) + " | " +
//                                    rs.getString(2) + " | " +
//                                    rs.getString(3);

                //System.out.println(message);
                //passwords.add(rs.getString(3));
                markings.add(new Marking(resource, rs.getString(3)));
            }
            rs.close();

            statement.close();
        } catch (SQLException e) {
            System.out.println("getRecord- при попытке получить определенные данные из бд");
            System.out.println(e);
        }
        return markings;
    }
}