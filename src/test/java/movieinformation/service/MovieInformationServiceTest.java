package movieinformation.service;

import movieinformation.Exception.MovieDuplicationException;
import movieinformation.Exception.MovieInformationNotFoundException;
import movieinformation.Exception.MovieNotFoundException;
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

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
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
        doReturn(movie).when(movieInformationMapper).findAll();
        List<Movie> actual = movieInformationService.findAll();
        assertThat(actual).isEqualTo(movie);
        verify(movieInformationMapper, times(1)).findAll();
    }

    @Test
    public void 存在する映画のIDを指定したときに正常に映画が返されること() throws
            MovieInformationNotFoundException {
        doReturn(Optional.of(new Movie(1, "Episode IV – A New Hope", LocalDate.of(1978, 6, 30), "George Walton Lucas Jr.", 775398007)))
                .when(movieInformationMapper).findById(1);
        Movie actual = movieInformationService.findById(1);
        assertThat(actual).isEqualTo(new Movie(1, "Episode IV – A New Hope", LocalDate.of(1978, 6, 30), "George Walton Lucas Jr.", 775398007));
        verify(movieInformationMapper, times(1)).findById(1);
    }

    @Test
    public void 存在しない映画のIDを指定したときに例外処理が動作すること() throws
            MovieInformationNotFoundException {
        doReturn(Optional.empty()).when(movieInformationMapper).findById(100);
        assertThrows(MovieInformationNotFoundException.class, () -> {
            movieInformationService.findById(100);
        });
        verify(movieInformationMapper, times(1)).findById(100);
    }

    //POSTのテストコード
    @Test
    public void 存在しない映画情報を新規登録すること() {
        Movie movie = new Movie("Marvel'sTheAvengers", LocalDate.of(2012, 8, 14), "Joseph Hill Whedon", 1518812988);
        doNothing().when(movieInformationMapper).insert(movie);
        Movie actual = movieInformationService.insert(movie);
        assertThat(actual).isEqualTo(new Movie("Marvel'sTheAvengers", LocalDate.of(2012, 8, 14), "Joseph Hill Whedon", 1518812988));
        verify(movieInformationMapper, times(1)).findByName(movie.getName());
        verify(movieInformationMapper, times(1)).insert(movie);
    }

    @Test
    public void 存在する映画情報を新規登録する場合に重複登録の例外処理が動作すること() throws MovieDuplicationException {
        Movie movie = new Movie("EpisodeIV–ANewHope", LocalDate.of(1978, 6, 30), "George Walton Lucas Jr.", 775398007);
        doReturn(Optional.of(movie.getName())).when(movieInformationMapper).findByName(movie.getName());
        assertThrows(MovieDuplicationException.class, () -> movieInformationService.insert(new Movie("EpisodeIV–ANewHope", LocalDate.of(1978, 6, 30), "George Walton Lucas Jr.", 775398007)));
        verify(movieInformationMapper, times(1)).findByName(movie.getName());
        verify(movieInformationMapper, times(0)).insert(movie);
    }

    //PATCHのテストコード
    @Test
    public void 存在する映画IDの情報を更新すること() {
        Movie movie = new Movie(1, "Episode IV – A New Hope", LocalDate.of(1997, 6, 30), "George Walton Lucas Jr.", 775398007);
        doReturn(Optional.of(movie.getId()))
                .when(movieInformationMapper).findById(1);
        doNothing().when(movieInformationMapper).update(new Movie(1, "Big Hero 6", LocalDate.of(2014, 12, 20), "Chris Williams", 657827828));
        Movie actual = movieInformationService.update(new Movie(1, "Big Hero 6", LocalDate.of(2014, 12, 20), "Chris Williams", 657827828));
        assertThat(actual).isEqualTo(new Movie(1, "Big Hero 6", LocalDate.of(2014, 12, 20), "Chris Williams", 657827828));
        verify(movieInformationMapper, times(1)).findById(1);
        verify(movieInformationMapper, times(1)).update(new Movie(1, "Big Hero 6", LocalDate.of(2014, 12, 20), "Chris Williams", 657827828));
    }

    @Test
    public void 存在しない映画IDの情報を更新する場合の例外処理() throws MovieNotFoundException {
        doReturn(Optional.empty()).when(movieInformationMapper).findById(100);
        assertThatThrownBy(
                () -> movieInformationService.update(new Movie(100, "Big Hero 6", LocalDate.of(2014, 12, 20), "Chris Williams", 657827828))
        ).isInstanceOf(
                MovieNotFoundException.class);
        verify(movieInformationMapper, times(1)).findById(100);
        verify(movieInformationMapper, times(0)).update(new Movie(100, "Big Hero 6", LocalDate.of(2014, 12, 20), "Chris Williams", 657827828));
    }

    //DELETEのテストコード
    @Test
    public void 存在する映画IDの情報を削除すること() {
        Movie movie = new Movie(1, "Episode IV – A New Hope", LocalDate.of(1997, 6, 30), "George Walton Lucas Jr.", 775398007);
        doReturn(Optional.of(movie.getId()))
                .when(movieInformationMapper).findById(1);
        doNothing().when(movieInformationMapper).delete(1);
        movieInformationService.delete(1);
        verify(movieInformationMapper, times(1)).findById(1);
        verify(movieInformationMapper, times(1)).delete(1);
    }

    @Test
    public void 存在しない映画を削除する場合に例外処理が動作すること() throws MovieNotFoundException {
        doReturn(Optional.empty()).when(movieInformationMapper).findById(100);
        assertThrows(MovieNotFoundException.class, () -> movieInformationService.delete(100));
        verify(movieInformationMapper, times(1)).findById(100);
        verify(movieInformationMapper, times(0)).delete(100);
    }

}
