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
    @GetMapping("/movie-information")
    public List<Movie> getAllMovieInformation() {
        List<Movie> movies = movieInformationService.getAllMovieInformation();
        return movies;
    }

    //ID検索により該当データ取得+例外処理
    @GetMapping("/movie-information/{movieId}")
    public Movie getSpecificMovieInformation(@PathVariable("movieId") int movieId) {
        Movie movie = movieInformationService.getSpecificMovieInformation(movieId);
        return movie;
    }

}
