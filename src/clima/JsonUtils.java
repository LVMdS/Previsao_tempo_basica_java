package clima;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class JsonUtils {

    // Método para analisar o JSON retornado pela API e convertê-lo em uma lista de mapas
    public static List<Map<String, Object>> parseJsonArray(String jsonResponse) {
        List<Map<String, Object>> result = new ArrayList<>();
        JSONArray jsonArray = new JSONArray(jsonResponse);

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            result.add(jsonObject.toMap());
        }

        return result;
    }
}
