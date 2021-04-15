package com.news.news.service;

import com.google.gson.Gson;
import com.news.news.model.response.ApiDolarResponse;
import com.news.news.model.response.Main;
import com.news.news.model.response.OpenWeatherResponse;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
@Slf4j // Slf4j es una erramienta de logs a nivel de servicios
public class ApiCallService {

    //java 11 nos brinda HttpClient para hacer nuestras peticiones,
    private static final HttpClient client = HttpClient.newBuilder().version(HttpClient.Version.HTTP_2).build();
    private static final String URL = "https://dolarapi.p.rapidapi.com/resumen";

    //la dependencia resilience4j nos da CircuitBreaker
    //CircuitBreaker brinda una alternativa en caso de q el llamado a una api falle (cierre de conexion, o cualkier fallo del servidor)
    @CircuitBreaker(name = "ApiWeather", fallbackMethod = "fallback")
//name = "ApiWeather" nombre para los logs y fallbackMethod es un metodo q se ejecutara si falla a este metodo
    public ApiDolarResponse callAPI() throws IOException, InterruptedException {

//        HttpRequest request = HttpRequest.newBuilder()
//                .uri(URI.create("http://api.weatherapi.com/v1/current.json?key=d7c3b91391394169b1b224909211004&q=Montevideo&aqi=no"))
////                .uri(URI.create(URL))
//                .GET()
//                .build();
//
//        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
//        System.out.println(response);
//
//        //Gson es una lib de google q nos permite parsear la response en un json
//        final ApiWeatherResponse apiWeatherResponse = new Gson().fromJson(response.body(), ApiWeatherResponse.class); // hacemos un json tomando el response.body() y mapeandolo aApiWeatherResponse.class q tiene los campos q necesitamos en nuestra respuesta q mostrara postman
//
//        System.out.println(apiWeatherResponse);
//        if(RandomUtils.nextBoolean()){
//            throw new IOException("Probando Circuit Breaker...");
//        }
//
//        return apiWeatherResponse;

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(URL))
                .header("x-rapidapi-key", "392d7c7c2amsh14dc46d53a988afp19ac54jsn7866ab04b564")
                .header("x-rapidapi-host", "dolarapi.p.rapidapi.com")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());
        //Gson es una lib de google q nos permite parsear la response en un json
        final ApiDolarResponse apiDolarResponse = new Gson().fromJson(response.body(), ApiDolarResponse.class); // hacemos un json tomando el response.body() y mapeandolo aApiWeatherResponse.class q tiene los campos q necesitamos en nuestra respuesta q mostrara postman

//        if (RandomUtils.nextBoolean()) { // se usa para probar
//            throw new IOException("Probando Circuit Breaker...");
//        }
        return apiDolarResponse;
    }

    //metodo q se ejecuta en caso de q falle el llamado a la api
    //puede ser este unico metodo para N llamados a apis
    private ApiDolarResponse fallback(final Throwable t) throws IOException, InterruptedException { // el metodo recibe throws las exceptions q puede tirar
        log.error(t.getStackTrace().toString()); // Slf4j - logeo de tipo error con getStackTrace traemos el log completo del throws
        return ApiDolarResponse
                .builder()
                .build();
    }

    @CircuitBreaker(name = "ApiWeather2", fallbackMethod = "fallback2")
    public OpenWeatherResponse callAPI2() throws IOException, InterruptedException {

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://api.openweathermap.org/data/2.5/weather?q=montevideo&appid=4ae2636d8dfbdc3044bede63951a019b"))
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        //HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());

        final OpenWeatherResponse openWeatherResponse = new Gson().fromJson(response.body(), OpenWeatherResponse.class);

        if (RandomUtils.nextBoolean()) {
            throw new IOException("Probando Circuit Breaker...");
        }

        System.out.println(openWeatherResponse);

        return openWeatherResponse;
    }

    private OpenWeatherResponse fallback2(final Throwable t) {
        log.error(t.getStackTrace().toString()); //todo el error
        Main main = new Main(0d, 0d, 0d, 0d, 0, 0);
        return OpenWeatherResponse
                .builder()
                .main(main)
                .name("cayeron ambas Api's")
                .build();
    }

}

