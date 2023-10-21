package MovieInformation.service;


import MovieInformation.MovieInformationNotFoundException;
import MovieInformation.entity.Movie;
import MovieInformation.mapper.MovieInformationMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieInformationService {
    public final MovieInformationMapper movieInformationMapper;

    public MovieInformationService(MovieInformationMapper movieInformationMapper) {
        this.movieInformationMapper = movieInformationMapper;
    }

    //GET
    public List<Movie> findAllMovies() {
        return movieInformationMapper.findAllMovies();
    }

    public Movie findByMovieId(int id) {
        return this.movieInformationMapper.findByMovieId(id)
                .orElseThrow(() -> new MovieInformationNotFoundException("movie information not found"));
    }
}
