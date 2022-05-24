package Lesson7;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class Controller {
    private WeatherModel weatherModel = new AccuweatherModel();
    private Map<Integer, Option> variants = new HashMap<>();

    public Controller() {
        variants.put(1, Option.NOW);
        variants.put(5, Option.FIFE_DAYS);
        variants.put(2, Option.DB);

    }

    public void getWeather(String userInput, String selectedCity) throws IOException, SQLException {
        Integer userIntegerInput = Integer.parseInt(userInput);

        switch(variants.get(userIntegerInput)) {
            case NOW:
                weatherModel.getWeather(selectedCity, Option.NOW);
                break;

            case FIFE_DAYS:
                weatherModel.getWeather(selectedCity, Option.FIFE_DAYS);

            case DB:
                weatherModel.getSavedToDBWeather();

        }
    }
}
