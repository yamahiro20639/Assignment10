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
