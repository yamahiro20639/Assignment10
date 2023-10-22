package MovieInformation.controller;

import MovieInformation.Form.MovieRegistrationForm;
import MovieInformation.entity.Movie;
import MovieInformation.service.MovieInformationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class MovieInformationController {
    private final MovieInformationService movieInformationService;

    public MovieInformationController(MovieInformationService movieInformationService) {
        this.movieInformationService = movieInformationService;
    }

    //GETの実装
    //全件取得の実装
    @GetMapping("/movies")
    public List<Movie> findAllMovies() {
        return movieInformationService.findAllMovies();
    }

    //ID検索により該当データ取得+例外処理
    @GetMapping("/movie/{id}")
    public Movie findByMovieId(@PathVariable("id") int id) {
        return movieInformationService.findByMovieId(id);
    }

    //POST
    //映画を新規登録
    @PostMapping("/new-movie")
    public ResponseEntity<MovieResponse> insertMovie(@RequestBody MovieRegistrationForm movieRegistrationForm, UriComponentsBuilder uriBuilder) {
        Movie movie = movieInformationService.insertMovie(movieRegistrationForm.getName(), movieRegistrationForm.getReleaseDate(), movieRegistrationForm.getDirectorName(), movieRegistrationForm.getBoxOffice());
        URI location = uriBuilder.path("/movie/{id}").buildAndExpand(movie.getId()).toUri();
        MovieResponse body = new MovieResponse("Movie created");
        return ResponseEntity.created(location).body(body);
    }
}
