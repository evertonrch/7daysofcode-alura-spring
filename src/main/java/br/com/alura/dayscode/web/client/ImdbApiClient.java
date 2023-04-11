package br.com.alura.dayscode.web.client;

import br.com.alura.dayscode.dto.RootImdbMovieJson;
import br.com.alura.dayscode.model.ImdbMovie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

@Component
public class ImdbApiClient {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${imdb.key}")
    private String apiKey;

    public ResponseEntity<List<ImdbMovie>> getImdbMovies() {
        ResponseEntity<RootImdbMovieJson> response = restTemplate
                .getForEntity(URI.create("https://imdb-api.com/en/API/Top250Movies/" + apiKey), RootImdbMovieJson.class);

        if (response.getStatusCodeValue() != 200) return ResponseEntity.notFound().build();

        if (response.hasBody()) {
            RootImdbMovieJson imdbMovieJson = response.getBody();
            return ResponseEntity.ok(toList(imdbMovieJson.getImdbMovies()));
        }
        return ResponseEntity.noContent().build();
    }

    private List<ImdbMovie> toList(ImdbMovie... movies) {
        return Arrays.asList(movies);
    }
}
