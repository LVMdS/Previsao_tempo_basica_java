package clima;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

public class CityLocator {
    private static final String GEOCODE_URL = "https://nominatim.openstreetmap.org/search?q=";
    private static final HttpClient CLIENT = HttpClient.newHttpClient();

    public Coordinates getCityCoordinates(String city) {
        try {
            // Codificando corretamente a cidade na URL
            String encodedCity = URLEncoder.encode(city.trim(), StandardCharsets.UTF_8);

            String urlString = GEOCODE_URL + encodedCity + "&format=json&limit=1";
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(urlString))
                    .GET()
                    .build();

            HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());

            // Verificando a resposta da API
            if (response.statusCode() != 200 || response.body().isEmpty()) {
                System.out.println("❌ Não foi possível encontrar a cidade.");
                return null;
            }

            // Processando o JSON de resposta
            List<Map<String, Object>> jsonList = JsonUtils.parseJsonArray(response.body());
            if (jsonList.isEmpty()) {
                System.out.println("❌ Nenhum resultado encontrado para a cidade.");
                return null;
            }

            Map<String, Object> cityData = jsonList.get(0);
            double lat = Double.parseDouble((String) cityData.get("lat"));
            double lon = Double.parseDouble((String) cityData.get("lon"));

            return new Coordinates(lat, lon);
        } catch (Exception e) {
            System.out.println("❌ Erro ao conectar à API de Geocodificação: " + e.getMessage());
            return null;
        }
    }
}

