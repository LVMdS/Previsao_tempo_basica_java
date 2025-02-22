package clima; 

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

public class WeatherService {
    private static final String API_KEY = "18c40330ff57a829fe4a24408631433a";
    private static final String WEATHER_API_URL = "https://api.openweathermap.org/data/2.5/weather?lat=%f&lon=%f&appid=%s&units=metric";
    private static final String GEO_API_URL = "https://api.openweathermap.org/data/2.5/weather?q=%s&appid=%s";

    // Método para obter as coordenadas da cidade
    public String getCityCoordinates(String city) {
        try {
            // Codifica o nome da cidade para a URL
            String encodedCity = URLEncoder.encode(city, StandardCharsets.UTF_8.toString());
            String geoUrl = String.format(GEO_API_URL, encodedCity, API_KEY);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(geoUrl))
                    .GET()
                    .build();

            HttpClient client = HttpClient.newHttpClient();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            return response.body();
        } catch (UnsupportedEncodingException e) {
            return "Erro ao codificar a cidade: " + e.getMessage();
        } catch (Exception e) {
            return "Erro ao obter coordenadas: " + e.getMessage();
        }
    }

    // Método para obter os dados do clima usando as coordenadas
    public String getWeatherData(double lat, double lon) {
        try {
            String urlString = String.format(WEATHER_API_URL, lat, lon, API_KEY);
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(urlString))
                    .GET()
                    .build();

            HttpClient client = HttpClient.newHttpClient();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            return response.body();
        } catch (Exception e) {
            return "Erro ao obter os dados de clima: " + e.getMessage();
        }
    }
}
