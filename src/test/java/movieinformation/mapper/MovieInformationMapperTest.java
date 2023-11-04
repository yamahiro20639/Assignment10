package movieinformation.mapper;

import movieinformation.entity.Movie;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;


import static org.junit.jupiter.api.Assertions.*;

@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class MovieInformationMapperTest {

    @Autowired
    MovieInformationMapper movieInformationMapper;

    //GETのDBテスト
    @Test
    @Sql(
            scripts = {"classpath:/databases/delete-movies.sql", "classpath:/databases/insert-movies.sql"},
            executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD
    )
    @Transactional
    void 全ての映画情報が取得出来ること() {
        List<Movie> movies = movieInformationMapper.findAll();
        assertThat(movies)
                .hasSize(3)
                .contains(
                        new Movie(1, "Episode IV – A New Hope", LocalDate.of(1978, 6, 30), "George Walton Lucas Jr.", 775398007),
                        new Movie(2, "Episode V – The Empire Strikes Back", LocalDate.of(1980, 6, 28), "Irvin Kershner", 538375067),
                        new Movie(3, "Episode VI – Return of the Jedi", LocalDate.of(1983, 7, 2), "Richard Marquand", 475106177)
                );
    }

    @Test
    @Sql(
            scripts = {"classpath:/databases/delete-movies.sql", "classpath:/databases/insert-movies.sql"},
            executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD
    )
    @Transactional
    void ID指定した映画の情報を獲得すること() {
        Optional<Movie> movie = movieInformationMapper.findById(1);
        assertThat(movie).contains(new Movie(1, "Episode IV – A New Hope", LocalDate.of(1978, 6, 30), "George Walton Lucas Jr.", 775398007));
    }

    @Test
    @Sql(
            scripts = {"classpath:/databases/delete-movies.sql", "classpath:/databases/insert-movies.sql"},
            executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD
    )
    @Transactional
    void 存在しない映画IDを指定する場合に空の情報を獲得すること() {
        Optional<Movie> movie = movieInformationMapper.findById(4);
        assertThat(movie).isEmpty();
    }
    
}
