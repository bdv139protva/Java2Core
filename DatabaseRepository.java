package Lesson7;

import Lesson7.entity.Weather;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseRepository {
    public List<Weather> getSavedToDBWeather;
    private String insertWeather = "insert into weather (city, localdate, temperature) values (?, ?, ?)";
    private String getWeather = "select * from weather";
    private static final String DB_PATH = "jdbc:sqlite:geekbrains.db";

    static {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public boolean saveWeatherToDatabase(Weather weather) throws SQLException{
        try (Connection connection = DriverManager.getConnection(DB_PATH)) {
            PreparedStatement saveWeather = connection.prepareStatement(insertWeather);
            saveWeather.setString(1, weather.getCity());
            saveWeather.setString(2, weather.getLocalDate());
            saveWeather.setDouble(3, weather.getTemperature());

            return saveWeather.execute();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        throw new SQLException("Saving the weather forecast in database is not done!");
    }

    public void saveWeatherToDatabase(List<Weather> weatherList) throws SQLException {
        try (Connection connection = DriverManager.getConnection(DB_PATH)) {
            PreparedStatement saveWeather = connection.prepareStatement(insertWeather);
            for (Weather w : weatherList) {
                saveWeather.setString(1, w.getCity());
                saveWeather.setString(2, w.getLocalDate());
                saveWeather.setDouble(3, w.getTemperature());
                saveWeather.addBatch();
            }
            saveWeather.executeBatch();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public List<Weather> getSavedToDBWeather() {
        List<Weather> weatherList = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(DB_PATH)) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(getWeather);
            while (resultSet.next()) {
                System.out.print(resultSet.getInt("id") + " ");
                System.out.print(resultSet.getString("city") + " ");
                System.out.print(resultSet.getString("localdate") + " ");
                System.out.print(resultSet.getDouble("temperature") + "\n");
                weatherList.add(new Weather(resultSet.getString("city"),
                        resultSet.getString("localdate"),
                        resultSet.getDouble("temperature")));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return weatherList;
    }

    /*public static void main(String[] args) throws SQLException {
        DatabaseRepository dataBaseRepository = new DatabaseRepository();
        System.out.println(dataBaseRepository.getSavedToDBWeather());
    }*/
}

