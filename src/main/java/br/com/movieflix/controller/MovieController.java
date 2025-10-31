package br.com.movieflix.controller;

import br.com.movieflix.controller.request.MovieRequest;
import br.com.movieflix.controller.response.MovieResponse;
import br.com.movieflix.service.MovieService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movieflix/movies")
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @PostMapping("/save")
    public ResponseEntity<MovieResponse> saveMovie(@RequestBody MovieRequest request) {
        return ResponseEntity.ok(movieService.saveMovie(request));
    }

    @GetMapping("")
    public ResponseEntity<List<MovieResponse>> findAllMovies() {
        return ResponseEntity.ok(movieService.findAllMovies());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        if (movieService.findById(id) != null) {
            return ResponseEntity.ok(movieService.findById(id));
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateMovie(@PathVariable Long id, @RequestBody MovieRequest request) {
        if (movieService.updateMovie(id, request) != null) {
            return ResponseEntity.ok(movieService.updateMovie(id, request));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        if (movieService.deleteById(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<?> findMovieByCategory(@PathVariable Long id) {
        if (!movieService.findMovieByCategory(id).isEmpty()) {
            return ResponseEntity.ok(movieService.findMovieByCategory(id));
        }
        return ResponseEntity.notFound().build();
    }
}

