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

    public List<Movie> getAllMovieInformation() {
        return movieInformationMapper.findAll();
    }

    public Movie getSpecificMovieInformation(int id) {
        return this.movieInformationMapper.getSpecificMovieInformation(id)
                .orElseThrow(() -> new MovieInformationNotFoundException("movie information not found"));
    }
}
