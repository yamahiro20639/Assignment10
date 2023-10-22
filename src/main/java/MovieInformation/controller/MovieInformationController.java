package MovieInformation.controller;

import MovieInformation.entity.Movie;
import MovieInformation.service.MovieInformationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

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


}