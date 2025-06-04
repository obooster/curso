package me.wellington.curso.http;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.GsonBuilder;
import me.wellington.curso.http.objects.TitleEz;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;

public final class Main {

    public void init() throws IOException, InterruptedException {
        var client = HttpClient.newHttpClient(); // novo cliente
        var request = HttpRequest.newBuilder() // nova requisição
                .uri(URI.create("https://www.omdbapi.com/?t=matrix&apikey=6585022c")) // URL
                .build();

        var response = client.send(request, HttpResponse.BodyHandlers.ofString()); // adquire a resposta

        System.out.println(response.body()); // adquire o corpo da resposta

        var gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
                .create(); // cria a porra de um GSON

        var myTitle = gson.fromJson(response.body(), TitleEz.class); // transforma o gson na classe q eu criei
        System.out.println(myTitle); // printa a resposta
    }

    public static void main(String[] args) {
        try {
            new Main().init();
        } catch (Exception e) {
            System.err.println(Arrays.toString(e.getStackTrace()));;
        }

    }

}
