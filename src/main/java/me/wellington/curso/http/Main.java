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
        var client = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder()
                .uri(URI.create("https://www.omdbapi.com/?t=matrix&apikey=6585022c"))
                .build();

        var response = client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println(response.body());

        var gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
                .create();

        var myTitle = gson.fromJson(response.body(), TitleEz.class);
        System.out.println(myTitle);
    }

    public static void main(String[] args) {
        try {
            new Main().init();
        } catch (Exception e) {
            System.err.println(Arrays.toString(e.getStackTrace()));;
        }

    }

}
