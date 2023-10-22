package MovieInformation.mapper;

import MovieInformation.entity.Movie;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Optional;

@Mapper
public interface MovieInformationMapper {
    @Select("SELECT * FROM movie_list ")
    List<Movie> findAllMovies();

    @Select("SELECT * FROM movie_list WHERE id=#{id}")
    Optional<Movie> findByMovieId(int id);
}
