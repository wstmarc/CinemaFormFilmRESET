package fr.laerce.cinema.service;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.TimeZone;

@Component
public class TmdbClient {

    //   !!!!!!!! AVEC AUTHENTIFICATION  !!!!!!!
    @Value("${tmdb.api.key}") // <<--
    private String apiKey;

    @Value("${path.movies}")
    private String pathMovie;

    private long secondsBeforeReset(String value){
        long timestamp = Long.valueOf(stripBraces(value));
        LocalDateTime resetTime =
                LocalDateTime.ofInstant(Instant.ofEpochSecond(timestamp), ZoneId.systemDefault());
        LocalDateTime now = LocalDateTime.now();
        return now.until( resetTime, ChronoUnit.SECONDS);
    }

    private String stripBraces(String value){
        return value.substring(0, value.length()-1).substring(1);
    }

    public void getMovieByTmdbId(long id) throws Exception {
        RestTemplate template = new RestTemplate();
        ResponseEntity<String> response;
        long reset;

//      TODO:  Dans la BDD interne de l'appli, ajouter les genres de la BDD externe tmdb avec leur id propre dans la BDD interne.
        // à l'aide de ce lien
//  "/3/genre/movie/list?api_key="+apiKey+"&language=fr-FR";
        // Autrement dit: Adapter la BDD interne à celle de TMDb.

        // ---->>
        String resourceUrl = pathMovie+id+"?api_key="+apiKey+"&language=fr-FR";
        response = template.getForEntity(resourceUrl, String.class);
        System.out.println(response.getBody());                             //*1
        JSONObject film = new JSONObject(response.getBody());
        JSONArray genres = (JSONArray) film.get("genres");
        System.out.println("Titre : "+film.getString("title"));          //*2
        for(int i = 0; i < genres.length(); i++){
            JSONObject genre = (JSONObject) genres.get(i);
            System.out.println("- Genre : "+genre.getString("name"));    //*3
        }
                                                                            //*4
        System.out.println("--------\nRequetes restantes : "+stripBraces(response.getHeaders().get("x-ratelimit-remaining").toString())); //stripBraces() pour enlever les crochets.
        reset = secondsBeforeReset(response.getHeaders().get("x-ratelimit-reset").toString());
        System.out.println("Temps restant avant reset : "+reset+"\n\n");    //*5

        // ---->>
        String resourceCredit = pathMovie+id+"/credits?api_key="+apiKey+"&language=fr-FR";
        response = template.getForEntity(resourceCredit, String.class);
        JSONObject credit = new JSONObject(response.getBody());
        JSONArray cast = (JSONArray) credit.get("cast");
        for (int i = 0; i < cast.length(); i++ ) {
            JSONObject role = (JSONObject) cast.get(i);                     //*6
            System.out.println(role.getString("name")+" joue "+ role.getString("character"));
        }                                                                   //*7
        System.out.println("--------\nRequetes restantes : "+stripBraces(response.getHeaders().get("x-ratelimit-remaining").toString()));
        reset = reset = secondsBeforeReset(response.getHeaders().get("x-ratelimit-reset").toString());
        System.out.println("Temps restant avant reset : "+reset+"\n\n");    //*8
    }
}
