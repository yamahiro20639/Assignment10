package MovieInformation.service;


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

    public List<Movie> getAllMovieInformation() {
        List<Movie> movies = movieInformationMapper.findAll();
        return movies;
    }
}
