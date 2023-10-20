package MovieInformation.mapper;

import MovieInformation.entity.Movie;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface MovieInformationMapper {
    @Select("SELECT * FROM movie_list ")
    List<Movie> findAll();

}
