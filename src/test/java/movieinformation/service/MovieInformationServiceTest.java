package movieinformation.service;

import movieinformation.Exception.MovieInformationNotFoundException;
import movieinformation.entity.Movie;
import movieinformation.mapper.MovieInformationMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MovieInformationServiceTest {

    @InjectMocks
    MovieInformationService movieInformationService;

    @Mock
    MovieInformationMapper movieInformationMapper;


    //GETのテストコード
    @Test
    void findAllMoviesメソッドの呼び出しで存在する映画を全部取得すること() {
        List<Movie> movie = List.of(
                new Movie(1, "Episode IV – A New Hope", LocalDate.of(1978, 6, 30), "George Walton Lucas Jr.", 775398007),
                new Movie(2, "Episode V – The Empire Strikes Back", LocalDate.of(1980, 6, 28), "Irvin Kershner", 538375067),
                new Movie(3, "Episode VI – Return of the Jedi", LocalDate.of(1983, 7, 2), "Richard Marquand", 475106177),
                new Movie(4, "Episode I – The Phantom Menace", LocalDate.of(1999, 7, 10), "George Walton Lucas Jr.", 1027082707),
                new Movie(5, "Episode II – Attack of the Clones", LocalDate.of(2002, 5, 16), "George Walton Lucas Jr.", 653779970),
                new Movie(6, "Episode III – Revenge of the Sith", LocalDate.of(2005, 7, 9), "George Walton Lucas Jr.", 868390560),
                new Movie(7, "Episode VII – The Force Awakens", LocalDate.of(2015, 12, 18), "Jeffrey Jacob Abrams", 2071310218),
                new Movie(8, "Episode VIII – The Last Jedi", LocalDate.of(2017, 12, 15), "Rian Craig Johnson", 1334407706),
                new Movie(9, "Episode IX – The Rise of Skywalker", LocalDate.of(2019, 12, 20), "Jeffrey Jacob Abrams", 1077022372),
                new Movie(13, "Rogue One: A Star Wars Story", LocalDate.of(2016, 12, 16), "Gareth Edwards", 1056057273),
                new Movie(21, "Episode IV – A New Hope", LocalDate.of(2012, 8, 14), "Joseph Hill Whedon", 1518812988)

        );
        doReturn(movie).when(movieInformationMapper).findAllMovies();
        List<Movie> actual = movieInformationService.findAllMovies();
        assertThat(actual).isEqualTo(movie);
    }

    @Test
    public void 存在する映画のIDを指定したときに正常に映画が返されること() throws
            MovieInformationNotFoundException {
        doReturn(Optional.of(new Movie(1, "Episode IV – A New Hope", LocalDate.of(1978, 6, 30), "George Walton Lucas Jr.", 775398007)))
                .when(movieInformationMapper).findByMovieId(1);
        Movie actual = movieInformationService.findByMovieId(1);
        assertThat(actual).isEqualTo(new Movie(1, "Episode IV – A New Hope", LocalDate.of(1978, 6, 30), "George Walton Lucas Jr.", 775398007));
    }

    @Test
    public void 存在しない映画のIDを指定したときに例外処理が動作すること() throws
            MovieInformationNotFoundException {
        doThrow(new MovieInformationNotFoundException("movie information not found")).when(movieInformationMapper).findByMovieId(100);
        assertThrows(MovieInformationNotFoundException.class, () -> {
            movieInformationService.findByMovieId(100);
        });
    }
}
