import java.sql.*;
import java.util.Scanner;

public class Zadanie2 {
    public static void main(String[] args) throws SQLException {
        Scanner scan = new Scanner(System.in);
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Błąd wczytania sterownika " + e.getMessage());
        }

        String url = "jdbc:mysql://127.0.0.1:3306/world?characterEncoding=utf8&serverTimezone=UTC&useSSL=false";
        Connection connection = null;

        try {
            connection = DriverManager.getConnection(url, "root", "root");
        } catch (SQLException e) {
            System.out.println("Błąd połączenia, powód: " + e.getMessage());
        }

        System.out.println("Podaj nazwę miasta");
        String cityName = scan.nextLine();
        String query2 = "update City set Population=? where Name=?";
        PreparedStatement prepStat = null;
        prepStat = connection.prepareStatement(query2);

        prepStat.setString(2, cityName);

        System.out.println("Podaj nową liczbę ludności");
        int population = scan.nextInt();
        scan.nextLine();

        prepStat.setInt(1, population);
        int resultSet1 = 0;
        resultSet1 = prepStat.executeUpdate();
        System.out.println("Ilość zaktualizowanych rekordów: " + resultSet1);

        String newCityPopulation = "select * from City where name=?";
        PreparedStatement getCity = null;
        getCity = connection.prepareStatement(newCityPopulation);
        getCity.setString(1, cityName);
        ResultSet resultSet = getCity.executeQuery();

        if (resultSet.next()) {
            int id = resultSet.getInt(1);
            String name = resultSet.getString(2);
            String countryCode = resultSet.getString(3);
            String district = resultSet.getString(4);
            int population1 = resultSet.getInt(5);

            System.out.println(id + " " + name + " " + countryCode + " " + district + " " + population1);

        }


    }
}
