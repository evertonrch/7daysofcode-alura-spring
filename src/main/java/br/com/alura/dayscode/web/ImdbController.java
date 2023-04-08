package br.com.alura.dayscode.web;

import br.com.alura.dayscode.dto.RootImdbMovieJson;
import br.com.alura.dayscode.model.ImdbMovie;
import br.com.alura.dayscode.view.HTMLGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.PrintWriter;
import java.io.Writer;
import java.net.URI;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ImdbController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private HTMLGenerator html;

    private final String apiKey = "";

    @GetMapping("/top250Movies")
    public ResponseEntity<ImdbMovie[]> top250Movies() {

        ResponseEntity<RootImdbMovieJson> response = restTemplate
                .getForEntity(URI.create("https://imdb-api.com/en/API/Top250Movies/" + apiKey), RootImdbMovieJson.class);

        if (response.getStatusCodeValue() != 200)
            return ResponseEntity.noContent().build();


        List<ImdbMovie> movies = toList(response.getBody().getImdbMovies());
        for (ImdbMovie imdbMovie : movies)
            System.out.println(imdbMovie);

        html.generate(movies);

        return ResponseEntity.ok(response.getBody().getImdbMovies());
    }

    private List<ImdbMovie> toList(ImdbMovie... movies) {
        return Arrays.asList(movies);
    }
}
