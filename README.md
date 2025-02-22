# Aplicação de Monitoramento de Clima

Esta é uma aplicação de monitoramento de clima construída em Java. Ela fornece uma interface onde os usuários podem inserir o nome de uma cidade para obter informações em tempo real sobre o clima, como temperatura, umidade e descrição do clima.

A aplicação faz uso de diversas APIs para obter dados geográficos e meteorológicos:

- **API OpenWeatherMap**: Para obter dados meteorológicos com base nas coordenadas geográficas.
- **API OpenStreetMap Nominatim**: Para obter as coordenadas geográficas com base no nome da cidade.

## Funcionalidades

- Buscar dados meteorológicos com base no nome da cidade.
- Exibir informações sobre o clima, como temperatura, umidade e uma breve descrição do clima.
- Interface gráfica simples (GUI) construída com Java Swing.

## Pré-requisitos

Antes de rodar a aplicação, verifique se você tem:

- **Java 11 ou superior** instalado.
- Uma **chave de API do OpenWeatherMap** (pode ser obtida ao se inscrever em [OpenWeatherMap](https://openweathermap.org/api)).

## Configuração

1. Clone este repositório:
   ```bash
   git clone https://github.com/yourusername/weather-monitoring-app.git
   cd weather-monitoring-app


Obtenha sua chave de API em OpenWeatherMap e substitua o valor no arquivo WeatherService:

"private static final String API_KEY = "SUA_CHAVE_DE_API";"

Compile e execute a aplicação:

Abra o projeto em sua IDE favorita (por exemplo, Eclipse).
Execute a classe WeatherApp.java.

Como Usar
Abra a aplicação.
Digite o nome de uma cidade no campo de texto.
Clique em "Obter Clima" para buscar os dados meteorológicos.
Os detalhes do clima, como temperatura, umidade e descrição, serão exibidos na lista abaixo.

##Arquitetura da Aplicação
#Classes
WeatherService: Responsável por interagir com a API OpenWeatherMap para buscar dados meteorológicos com base nas coordenadas da cidade.
WeatherApp: Classe GUI que permite ao usuário inserir o nome da cidade e exibe os dados meteorológicos.
Main: Ponto de entrada da aplicação. Inicializa e executa o WeatherApp.
CityLocator: Recupera as coordenadas geográficas de uma cidade usando a API OpenStreetMap Nominatim.
JsonUtils: Fornece métodos utilitários para analisar respostas JSON das APIs.
Coordinates: Um simples registro para representar coordenadas geográficas (latitude e longitude).

##Dependências
Bibliotecas padrão do Java (não há dependências externas).

##Contribuindo
Fique à vontade para abrir problemas ou pull requests se desejar contribuir para o projeto. Caso encontre algum erro ou tenha sugestões de melhorias, por favor, crie um problema.

#Licença
Este projeto está licenciado sob a Licença MIT. Veja o arquivo LICENSE para mais informações.
