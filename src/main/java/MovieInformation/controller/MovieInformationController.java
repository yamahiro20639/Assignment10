package MovieInformation.controller;

import MovieInformation.entity.Movie;
import MovieInformation.service.MovieInformationService;
import org.springframework.web.bind.annotation.GetMapping;
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

}
