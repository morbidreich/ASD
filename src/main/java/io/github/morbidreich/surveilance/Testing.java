package io.github.morbidreich.surveilance;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import org.opensky.api.*;


public class Testing {
    public static void main(String[] args) {

        try {


            // api endpoint covering almost entire poland
            URI AUP = new URI("https://opensky-network.org/api/states/all?lamin=50.8389&lomin=14.9962&lamax=54.8229&lomax=24.5226");

            HttpClient client = HttpClient.newBuilder()
                    .build();

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(AUP)
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            String myJson = response.body();
            //Map<String, Object> objMapper =

//            System.out.println(response.statusCode());
            System.out.println(response.body());
            System.out.println(System.currentTimeMillis());
        }
        catch (Exception e) {
            System.out.println("Some kind of error");
        }

    }

}
