package br.com.alura.dayscode.web;

import br.com.alura.dayscode.dto.ImdbMovie;
import br.com.alura.dayscode.dto.RootImdbMovieJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@RestController
@RequestMapping("/api")
public class ImdbController {

    @Autowired
    private RestTemplate restTemplate;

    private final String apiKey = "";

    @GetMapping("/top250Movies")
    public ResponseEntity<ImdbMovie[]> top250Movies() {

        ResponseEntity<RootImdbMovieJson> response = restTemplate
                .getForEntity(URI.create("https://imdb-api.com/en/API/Top250Movies/" + apiKey), RootImdbMovieJson.class);

        if(response.getStatusCodeValue() == 200) {
            for (ImdbMovie imdbMovie : response.getBody().getImdbMovies())
                System.out.println(imdbMovie);

            return  ResponseEntity.ok(response.getBody().getImdbMovies());
        }
        return ResponseEntity.noContent().build();
    }
}
