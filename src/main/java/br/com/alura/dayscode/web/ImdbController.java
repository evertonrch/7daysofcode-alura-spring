package br.com.alura.dayscode.web;

import br.com.alura.dayscode.model.ImdbMovie;
import br.com.alura.dayscode.view.HTMLGenerator;
import br.com.alura.dayscode.web.client.ImdbApiClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ImdbController {

    @Autowired
    private ImdbApiClient imdbClient;

    @Autowired
    private HTMLGenerator html;

    @GetMapping("/top250/console")
    public ResponseEntity<List<ImdbMovie>> listMovies() {
        imdbClient
                .getImdbMovies()
                .getBody()
                .forEach(System.out::println);

        return ResponseEntity.ok(imdbClient.getImdbMovies().getBody());
    }

    @GetMapping("/top250/html")
    public ResponseEntity<ImdbMovie[]> top250Movies() {
        List<ImdbMovie> movies = imdbClient
                .getImdbMovies()
                .getBody();

        html.generate(movies);
        return ResponseEntity
                .ok(movies.toArray(new ImdbMovie[movies.size()]));
    }

}
