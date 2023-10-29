package movieinformation.service;


import movieinformation.Exception.MovieDuplicationException;
import movieinformation.Exception.MovieInformationNotFoundException;
import movieinformation.Exception.MovieNotFoundException;
import movieinformation.entity.Movie;
import movieinformation.mapper.MovieInformationMapper;
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

    //POST
    public Movie insertMovie(Movie movie) {
        if (movieInformationMapper.findMovie(movie.getName()).isPresent()) {
            throw new MovieDuplicationException("Already registered data");
        } else {
            movieInformationMapper.insertMovie(movie);
            return movie;
        }
    }

    //PATCH
    public Movie updateMovie(Movie movie) {
        movieInformationMapper.findMovieId(movie.getId())
                .orElseThrow(() -> new MovieNotFoundException("Movie not found"));
        movieInformationMapper.updateMovie(movie);
        return movie;
    }

    //DELETE
    public void deleteMovie(int id) {
        movieInformationMapper.findMovieId(id)
                .orElseThrow(() -> new MovieNotFoundException("Movie not found"));
        movieInformationMapper.deleteMovie(id);
    }
}
