package movieinformation.service;


import movieinformation.MovieDuplicationException;
import movieinformation.MovieInformationNotFoundException;
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
            //Movie movie = new Movie(null, name, releaseDate, directorName, boxOffice);
            movieInformationMapper.insertMovie(movie);
            return movie;
        }
    }

    public Movie updateMovie(int id, Movie movie) {
        movieInformationMapper.updateMovie(id, movie.getName(), movie.getReleaseDate(), movie.getDirectorName(), movie.getBoxOffice());
        return movie;
    }

    public void deleteMovie(int id) {
        movieInformationMapper.deleteMovie(id);
    }
}
