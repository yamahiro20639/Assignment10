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
    public List<Movie> findAll() {
        return movieInformationMapper.findAll();
    }

    public Movie findById(int id) {
        return this.movieInformationMapper.findById(id)
                .orElseThrow(() -> new MovieInformationNotFoundException("movie information not found"));
    }

    //POST
    public Movie insert(Movie movie) {
        if (movieInformationMapper.findByName(movie.getName()).isPresent()) {
            throw new MovieDuplicationException("Already registered data");
        } else {
            movieInformationMapper.insert(movie);
            return movie;
        }
    }

    //PATCH
    public Movie update(Movie movie) {
        movieInformationMapper.findById(movie.getId())
                .orElseThrow(() -> new MovieNotFoundException("Movie not found"));
        movieInformationMapper.update(movie);
        return movie;
    }

    //DELETE
    public void delete(int id) {
        movieInformationMapper.findById(id)
                .orElseThrow(() -> new MovieNotFoundException("Movie not found"));
        movieInformationMapper.delete(id);
    }
}
