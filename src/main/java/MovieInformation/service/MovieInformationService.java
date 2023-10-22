package MovieInformation.service;


import MovieInformation.MovieDuplicationException;
import MovieInformation.MovieInformationNotFoundException;
import MovieInformation.entity.Movie;
import MovieInformation.mapper.MovieInformationMapper;
import org.springframework.stereotype.Service;

import java.sql.Date;
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

    //POST
    public Movie insertMovie(String name, Date releaseDate, String directorName, long boxOffice) {
        if (movieInformationMapper.findMovie(name).isPresent()) {
            throw new MovieDuplicationException("Already registered data");
        } else {
            Movie movie = new Movie(null, name, releaseDate, directorName, boxOffice);
            movieInformationMapper.insertMovie(movie);
            return movie;
        }
    }
}
