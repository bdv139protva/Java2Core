package Lesson7;
/*
 * Java Core. Homework #7
 *
 * @author Bakeshko Daria
 * @version 02.04.22
 *
 */
import Lesson7.entity.Weather;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AccuweatherModel implements WeatherModel {
    //URL url = new URL("http://dataservice.accuweather.com/forecasts/v1/daily/1day/25123?apikey=vbBhsI6AJI5HWZq55SD0WAO2J8SSdEWs")
    private static final String PROTOCOL = "http";
    private static final String BASE_HOST = "dataservice.accuweather.com";
    private static final String FORECASTS = "forecasts";
    private static final String VERSION = "v1";
    private static final String DAILY = "daily";
    private static final String ONE_DAY = "1day";
    private static final String FIFE_DAYS = "5day";
    //private static final String API_KEY = "Ya4FUosjMlpExQnC7AiHamzrph2KtGdg";
    private static final String API_KEY = "VfmLVtqphgd5pzVY1KyrO9OcnBbv8Iwv";

    private static final String API_KEY_QUERY_PARAM = "apikey";
    private static final String LOCATIONS = "locations";
    private static final String CITIES = "cities";
    private static final String AUTOCOMPLETE = "autocomplete";

    private static final OkHttpClient okHttpClient = new OkHttpClient();
    private static final ObjectMapper objectMapper = new ObjectMapper();

    private DatabaseRepository databaseRepository = new DatabaseRepository();


    @Override
    public void getWeather(String selectedCity, Option option) throws IOException, SQLException {
        switch (option) {
            case NOW:
                HttpUrl httpUrl = new HttpUrl.Builder()
                        .scheme(PROTOCOL)
                        .host(BASE_HOST)
                        .addPathSegment(FORECASTS)
                        .addPathSegment(VERSION)
                        .addPathSegment(DAILY)
                        .addPathSegment(ONE_DAY)
                        .addPathSegment(detectCityKey(selectedCity))
                        .addQueryParameter(API_KEY_QUERY_PARAM, API_KEY)
                        .addQueryParameter("language", "ru-ru")
                        .addQueryParameter("metric", "true")
                        .build();

                Request request = new Request.Builder()
                        .url(httpUrl)
                        .build();

                Response oneDayForecastsResponse = okHttpClient.newCall(request).execute();
                String weatherResponse = oneDayForecastsResponse.body().string();
                //System.out.println(weatherResponse);
                //сделать вывод для людей, например: Погода в городе Москва - 5 градусов по цельсию ожидается дождь...
                String date = objectMapper.readTree(weatherResponse).at("/DailyForecasts").get(0).at("/Date").asText();
                Double temperature = objectMapper.readTree(weatherResponse).at("/DailyForecasts").get(0).at("/Temperature/Maximum/Value").asDouble();
                String temperature_unit = objectMapper.readTree(weatherResponse).at("/DailyForecasts").get(0).at("/Temperature/Maximum/Unit").asText();
                String weather_text = objectMapper.readTree(weatherResponse).at("/DailyForecasts").get(0).at("/Day/IconPhrase").asText();
                //System.out.println(date);
                //System.out.println(weather_text);
                //System.out.println(temperature);
                System.out.println("Погода в городе " + selectedCity + " на " + date + " днем составит " + temperature + " градуса по " + temperature_unit + " и будет " + weather_text);

                databaseRepository.saveWeatherToDatabase(new Weather(selectedCity, date, temperature));//нужно добавить то, что напарсила

                break;

            case FIFE_DAYS:
                //lesson7 реализовать
                //http://dataservice.accuweather.com/forecasts/v1/daily/5day/
               HttpUrl httpUrl5Days = new HttpUrl.Builder()
                        .scheme(PROTOCOL)
                        .host(BASE_HOST)
                        .addPathSegment(FORECASTS)
                        .addPathSegment(VERSION)
                        .addPathSegment(DAILY)
                        .addPathSegment(FIFE_DAYS)
                        .addPathSegment(detectCityKey(selectedCity))
                        .addQueryParameter(API_KEY_QUERY_PARAM, API_KEY)
                        .build();

                Request request5Days = new Request.Builder()
                        .url(httpUrl5Days)
                        .build();

                Response fifeDaysForecastsResponse = okHttpClient.newCall(request5Days).execute();
                String weatherResponse5Days = fifeDaysForecastsResponse.body().string();

                String date1 = objectMapper.readTree(weatherResponse5Days).at("/DailyForecasts").get(0).at("/Date").asText();
                Double temperature1 = objectMapper.readTree(weatherResponse5Days).at("/DailyForecasts").get(0).at("/Temperature/Maximum/Value").asDouble();
                String temperature_unit1 = objectMapper.readTree(weatherResponse5Days).at("/DailyForecasts").get(0).at("/Temperature/Maximum/Unit").asText();
                String weather_text1 = objectMapper.readTree(weatherResponse5Days).at("/DailyForecasts").get(0).at("/Day/IconPhrase").asText();

                String date2 = objectMapper.readTree(weatherResponse5Days).at("/DailyForecasts").get(1).at("/Date").asText();
                Double temperature2 = objectMapper.readTree(weatherResponse5Days).at("/DailyForecasts").get(1).at("/Temperature/Maximum/Value").asDouble();
                String temperature_unit2 = objectMapper.readTree(weatherResponse5Days).at("/DailyForecasts").get(1).at("/Temperature/Maximum/Unit").asText();
                String weather_text2 = objectMapper.readTree(weatherResponse5Days).at("/DailyForecasts").get(1).at("/Day/IconPhrase").asText();

                String date3 = objectMapper.readTree(weatherResponse5Days).at("/DailyForecasts").get(2).at("/Date").asText();
                Double temperature3 = objectMapper.readTree(weatherResponse5Days).at("/DailyForecasts").get(2).at("/Temperature/Maximum/Value").asDouble();
                String temperature_unit3 = objectMapper.readTree(weatherResponse5Days).at("/DailyForecasts").get(2).at("/Temperature/Maximum/Unit").asText();
                String weather_text3 = objectMapper.readTree(weatherResponse5Days).at("/DailyForecasts").get(2).at("/Day/IconPhrase").asText();

                String date4 = objectMapper.readTree(weatherResponse5Days).at("/DailyForecasts").get(3).at("/Date").asText();
                Double temperature4 = objectMapper.readTree(weatherResponse5Days).at("/DailyForecasts").get(3).at("/Temperature/Maximum/Value").asDouble();
                String temperature_unit4 = objectMapper.readTree(weatherResponse5Days).at("/DailyForecasts").get(3).at("/Temperature/Maximum/Unit").asText();
                String weather_text4 = objectMapper.readTree(weatherResponse5Days).at("/DailyForecasts").get(3).at("/Day/IconPhrase").asText();

                String date5 = objectMapper.readTree(weatherResponse5Days).at("/DailyForecasts").get(4).at("/Date").asText();
                Double temperature5 = objectMapper.readTree(weatherResponse5Days).at("/DailyForecasts").get(4).at("/Temperature/Maximum/Value").asDouble();
                String temperature_unit5 = objectMapper.readTree(weatherResponse5Days).at("/DailyForecasts").get(4).at("/Temperature/Maximum/Unit").asText();
                String weather_text5 = objectMapper.readTree(weatherResponse5Days).at("/DailyForecasts").get(4).at("/Day/IconPhrase").asText();

                //System.out.println(weatherResponse5Days);

                System.out.println("Weather in " + selectedCity + " city at " + date1 + " is " + temperature1 + temperature_unit1 + " " + weather_text1);
                System.out.println("Weather in " + selectedCity + " city at " + date2 + " is " + temperature2 + temperature_unit2 + " " + weather_text2);
                System.out.println("Weather in " + selectedCity + " city at " + date3 + " is " + temperature3 + temperature_unit3 + " " + weather_text3);
                System.out.println("Weather in " + selectedCity + " city at " + date4 + " is " + temperature4 + temperature_unit4 + " " + weather_text4);
                System.out.println("Weather in " + selectedCity + " city at " + date5 + " is " + temperature5 + temperature_unit5 + " " + weather_text5);

                List<Weather> weatherList = new ArrayList<>();
                weatherList.add(new Weather(selectedCity, date1, temperature1));
                weatherList.add(new Weather(selectedCity, date2, temperature2));
                weatherList.add(new Weather(selectedCity, date3, temperature3));
                weatherList.add(new Weather(selectedCity, date4, temperature4));
                weatherList.add(new Weather(selectedCity, date5, temperature5));
                //public void saveWeatherToDatabase(new List<Weather> weatherList)
                databaseRepository.saveWeatherToDatabase(weatherList);//а здесь вариант который принимает лист
                break;
        }
    }


    private String detectCityKey(String selectedCity) throws IOException {
        //http://dataservice.accuweather.com/locations/v1/cities/search
        //http://dataservice.accuweather.com/locations/v1/cities/autocomplete
        HttpUrl httpUrl = new HttpUrl.Builder()
                .scheme(PROTOCOL)
                .host(BASE_HOST)
                .addPathSegment(LOCATIONS)
                .addPathSegment(VERSION)
                .addPathSegment(CITIES)
                .addPathSegment(AUTOCOMPLETE)
                .addQueryParameter(API_KEY_QUERY_PARAM, API_KEY)
                .addQueryParameter("q", selectedCity)
                .build();

        Request request = new Request.Builder()
                .url(httpUrl)
                .get()
                .addHeader("accept", "application/json")
                .build();

        Response cityResponse = okHttpClient.newCall(request).execute();
        String weatherResponse = cityResponse.body().string();
        //System.out.println(weatherResponse);
        //294021 - Moscow
        //295212 - SPb-Ru
        String cityKey = objectMapper.readTree(weatherResponse).get(0).at("/Key").asText();
        String cityLocalizedName = objectMapper.readTree(weatherResponse).get(0).at("/LocalizedName").asText();
        //System.out.println(cityKey);
        //System.out.println(cityLocalizedName);

        return cityKey;
    }

    @Override
    public List<Weather> getSavedToDBWeather() {
        return databaseRepository.getSavedToDBWeather;
    }

    public static void main(String[] args) throws IOException, SQLException {
        //AccuweatherModel accuweatherModel = new AccuweatherModel();
        //accuweatherModel.getWeather("Saint Petersburg", Period.NOW);

        //AccuweatherModel accuweatherModel5Days = new AccuweatherModel();
        //accuweatherModel5Days.getWeather("Saint Petersburg", Period.FIFE_DAYS);

        //accuweatherModel.detectCityKey("Moscow");

        UserInterfaceView userIntefaceView = new UserInterfaceView();
        userIntefaceView.runInterface();
        DatabaseRepository dataBaseRepository = new DatabaseRepository();
        System.out.println(dataBaseRepository.getSavedToDBWeather());
        }

    }
