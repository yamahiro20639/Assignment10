package MovieInformation.service;


import MovieInformation.MovieInformationNotFoundException;
import MovieInformation.entity.Movie;
import MovieInformation.mapper.MovieInformationMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieInformationService {
    public final MovieInformationMapper movieInformationMapper;

    public MovieInformationService(MovieInformationMapper movieInformationMapper) {
        this.movieInformationMapper = movieInformationMapper;
    }

    //GET

    public List<Movie> getAllMovieInformation() {
        List<Movie> movies = movieInformationMapper.findAll();
        return movies;
    }

    public Movie getSpecificMovieInformation(int movieId) {
        return this.movieInformationMapper.getSpecificMovieInformation(movieId)
                .orElseThrow(() -> new MovieInformationNotFoundException("movie information not found"));
    }
}
