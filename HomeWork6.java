package Lesson6;
/*
 * Java Core. Homework #6
 *
 * @author Bakeshko Daria
 * @version 22.04.22
 *
 */
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import java.io.IOException;

public class HomeWork6 {
    public static void main(String[] args) throws IOException {
        AccuweatherModel accuweatherModel = new AccuweatherModel();
        accuweatherModel.getWeather("City", Period.NOW);
    }

    public static class AccuweatherModel implements WeatherModel {
        //URL url = new URL("http://dataservice.accuweather.com/forecasts/v1/daily/1day/25123?apikey=vbBhsI6AJI5HWZq55SD0WAO2J8SSdEWs")
        private static final String PROTOCOL = "http";
        private static final String BASE_HOST = "dataservice.accuweather.com";
        private static final String FORECASTS = "forecasts";
        private static final String VERSION = "v1";
        private static final String DAILY = "daily";
        private static final String ONE_DAY = "1day";

        private static final String API_KEY = "vbBhsI6AJI5HWZq55SD0WAO2J8SSdEWs";
        private static final String API_KEY_QUERY_PARAM = "apikey";
        //private static final String LOCATIONS = "locations";
        //private static final String CITIES = "cities";
        //private static final String AUTOCOMPLETE = "autocomplete";

        private static final OkHttpClient okHttpClient = new OkHttpClient();
        private static final ObjectMapper objectMapper = new ObjectMapper();

        @Override
        public void getWeather(String selectedCity, Period period) throws IOException {
            switch(period){
                case NOW:
                    HttpUrl httpUrl = new HttpUrl.Builder()
                            .scheme(PROTOCOL)
                            .host(BASE_HOST)
                            .addPathSegment(FORECASTS)
                            .addPathSegment(VERSION)
                            .addPathSegment(DAILY)
                            .addPathSegment(ONE_DAY)
                            .addPathSegment("25123")
                            .addQueryParameter(API_KEY_QUERY_PARAM, API_KEY)
                            .build();

                    Request request = new Request.Builder()
                            .url(httpUrl)
                            .build();

                    Response oneDayForecastsResponse = okHttpClient.newCall(request).execute();
                    String weatherResponse = oneDayForecastsResponse.body().string();
                    System.out.println(weatherResponse);

                    break;

                case FIFE_DAYS:
                    //lesson7
                    break;
            }
        }
    }

    public interface WeatherModel {
        void getWeather(String selectedCity, Period period) throws IOException;
    }

    public enum Period {
        NOW, FIFE_DAYS
    }


}