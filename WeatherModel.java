package Lesson7;

import Lesson7.entity.Weather;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface WeatherModel {
    void getWeather(String selectedCity, Option option) throws IOException, SQLException;

    public List<Weather> getSavedToDBWeather();
}
