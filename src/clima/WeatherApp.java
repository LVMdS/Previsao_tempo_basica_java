package clima;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.json.JSONObject;
import org.json.JSONArray;

public class WeatherApp {
    private JFrame frame;
    private JTextField cityTextField;
    private JList<String> weatherDataList;
    private DefaultListModel<String> listModel;
    private WeatherService weatherService;

    public WeatherApp() {
        // Inicializa o serviço de clima
        weatherService = new WeatherService();
        
        // Criação da janela principal
        frame = new JFrame("Monitoramento de Clima");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setLayout(new BorderLayout());

        // Criação de componentes
        cityTextField = new JTextField();
        cityTextField.setToolTipText("Digite o nome da cidade");

        JButton getWeatherButton = new JButton("Obter Clima");
        
        // Modelo de lista para exibição dos dados
        listModel = new DefaultListModel<>();
        weatherDataList = new JList<>(listModel);
        weatherDataList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        weatherDataList.setVisibleRowCount(10);
        JScrollPane listScrollPane = new JScrollPane(weatherDataList);

        // Adiciona os componentes à janela
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BorderLayout());
        inputPanel.add(cityTextField, BorderLayout.CENTER);
        inputPanel.add(getWeatherButton, BorderLayout.EAST);

        frame.add(inputPanel, BorderLayout.NORTH);
        frame.add(listScrollPane, BorderLayout.CENTER);

        // Ação do botão "Obter Clima"
        getWeatherButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String city = cityTextField.getText();
                if (!city.isEmpty()) {
                    getWeather(city);
                } else {
                    listModel.clear();
                    listModel.addElement("Por favor, insira o nome de uma cidade.");
                }
            }
        });

        // Exibe a janela
        frame.setVisible(true);
    }

    // Método que chama o WeatherService e exibe os dados na lista
    private void getWeather(String city) {
        // Chama a API de Geocodificação para obter as coordenadas da cidade
        String geoData = weatherService.getCityCoordinates(city);

        // Limpa a lista antes de adicionar novos dados
        listModel.clear();

        if (geoData.contains("Erro")) {
            listModel.addElement(geoData);
        } else {
            try {
                // Processa os dados de geocodificação para obter as coordenadas
                JSONObject jsonGeo = new JSONObject(geoData);
                double lat = jsonGeo.getJSONObject("coord").getDouble("lat");
                double lon = jsonGeo.getJSONObject("coord").getDouble("lon");

                // Agora, obtemos os dados de clima usando as coordenadas obtidas
                String weatherData = weatherService.getWeatherData(lat, lon);

                // Processa os dados de clima
                JSONObject json = new JSONObject(weatherData);
                String cityName = json.getString("name");
                JSONObject main = json.getJSONObject("main");
                double temperature = main.getDouble("temp");
                int humidity = main.getInt("humidity");
                JSONArray weatherArray = json.getJSONArray("weather");
                JSONObject weather = weatherArray.getJSONObject(0);
                String description = weather.getString("description");

                // Adiciona os dados à lista
                listModel.addElement("Cidade: " + cityName);
                listModel.addElement("Temperatura: " + temperature + "°C");
                listModel.addElement("Umidade: " + humidity + "%");
                listModel.addElement("Descrição: " + description);

            } catch (Exception e) {
                listModel.addElement("Erro ao processar os dados.");
            }
        }
    }

    // Método main para rodar a aplicação
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new WeatherApp();
            }
        });
    }
}


