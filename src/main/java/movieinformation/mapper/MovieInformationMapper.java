package movieinformation.mapper;

import movieinformation.entity.Movie;
import org.apache.ibatis.annotations.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Mapper
public interface MovieInformationMapper {
    //GET
    @Select("SELECT * FROM movie_list ")
    List<Movie> findAllMovies();

    @Select("SELECT * FROM movie_list WHERE id=#{id}")
    Optional<Movie> findByMovieId(int id);

    //POST

    @Select("SELECT name FROM movie_list WHERE name = #{name}")
    Optional<String> findMovie(String name);

    @Insert("INSERT INTO movie_list (name,release_date,director_name,box_office) VALUES (#{name}, #{releaseDate},#{directorName},#{boxOffice})")
    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
    void insertMovie(Movie movie);

    //PATCH
    @Update("UPDATE movie_list SET name=#{name},release_date=#{releaseDate},director_name=#{directorName},box_office = #{boxOffice} WHERE id=#{id} ")
    void updateMovie(int id, String name, LocalDate releaseDate, String directorName, long boxOffice);
}
