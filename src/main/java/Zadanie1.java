import java.sql.*;
import java.util.Scanner;

public class Zadanie1 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Scanner scan = new Scanner(System.in);
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/world?characterEncoding=utf8&serverTimezone=UTC";
        String username = "root";
        String password = "root";
        Connection connection = DriverManager.getConnection(url, username, password);
        Statement statement = connection.createStatement();
        System.out.println("Podaj kod kraju");
        String code = scan.nextLine();
        String query = "select * from city where CountryCode='" + code + "'";
        java.sql.ResultSet resultSet = statement.executeQuery(query);
        while (resultSet.next()) {
            int ID = resultSet.getInt(1);
            String name = resultSet.getString("Name");
            String countryCode = resultSet.getString(3);
            String district = resultSet.getString("District");
            int population = resultSet.getInt("Population");

            System.out.println(ID + " " + name + " " + countryCode + " " + district + " " + population);
        }
        /*
        Napisz również funkcjonalność, która pobierze od użytkownika język i wyświetli wszystkie kraje
         w których on funkcjonuje, wraz z informacją o tym, czy język jest oficjalny oraz z procentem
         posługujących się nim osób.

         */
        System.out.println("Podaj kod języka");
        String language = scan.nextLine();
        String queryLanguage = "select Name, Language, IsOfficial, Percentage from country co\n" +
                "INNER JOIN countrylanguage cl ON co.Code=cl.CountryCode\n" +
                "where Language='" + language + "' order by Percentage desc";
        ResultSet resultSet1 = statement.executeQuery(queryLanguage);
        while (resultSet1.next()) {
            String name = resultSet1.getString(1);
            String languageSql = resultSet1.getString(2);
            String official = resultSet1.getString(3);
            String percentage = resultSet1.getString(4);

            System.out.println(name + " " + languageSql + " " + official + " " + percentage);
        }
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
