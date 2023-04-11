package br.com.alura.dayscode.web;

import br.com.alura.dayscode.model.ImdbMovie;
import br.com.alura.dayscode.view.HTMLGenerator;
import br.com.alura.dayscode.web.client.ImdbApiClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/top250")
public class ImdbController {

    @Autowired
    private ImdbApiClient imdbClient;

    @Autowired
    private HTMLGenerator html;

    private Set<ImdbMovie> favorites = new HashSet<>();

    @GetMapping
    public ResponseEntity<List<ImdbMovie>> byTitle(@RequestParam("title") String title) {
        List<ImdbMovie> movies = imdbClient.getImdbMovies()
                .getBody()
                .stream()
                .filter(movie -> movie.getTitle().equals(title))
                .collect(Collectors.toList());

        html.generate(movies);
        movies.forEach(System.out::println);
        return ResponseEntity.ok(movies);
    }

    @GetMapping("/console")
    public ResponseEntity<List<ImdbMovie>> listMovies(String title) {
        imdbClient
                .getImdbMovies()
                .getBody()
                .forEach(System.out::println);

        return ResponseEntity.ok(imdbClient.getImdbMovies().getBody());
    }

    @GetMapping("/html")
    public ResponseEntity<ImdbMovie[]> top250Movies() {
        List<ImdbMovie> movies = imdbClient
                .getImdbMovies()
                .getBody();

        if(movies == null) return ResponseEntity.noContent().build();

        html.generate(movies);
        return ResponseEntity
                .ok(movies.toArray(new ImdbMovie[movies.size()]));
    }

    @PostMapping("{title}")
    public ResponseEntity<List<ImdbMovie>> createMovie(@PathVariable("title") String title) {
        // Para strings mutáveis
        StringBuilder newTitle = new StringBuilder();

        if (title.contains("+"))
            newTitle.append(title.replace("+", " "));
        else
            newTitle.append(title);

        System.out.println(newTitle);
        for (ImdbMovie imdbMovie : imdbClient.getImdbMovies().getBody()) {
            if(imdbMovie.getTitle().equals(newTitle.toString()))
                this.favorites.add(imdbMovie);

        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/favorites")
    public ResponseEntity<Set<ImdbMovie>> favoritesMovies() {
        return ResponseEntity.ok(this.favorites);
    }

}
