package MovieInformation.Form;

import MovieInformation.entity.Movie;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.time.LocalDate;

public class MovieUpdateForm {
    @NotEmpty //文字列やコレクションなどの文字列が空でないことを検証
    private String name;
    @NotNull //空（null）であるかどうかを検証
    private LocalDate releaseDate;
    @NotEmpty //文字列やコレクションなどの文字列が空でないことを検証
    private String directorName;
    @PositiveOrZero //数値が正か 0 であることを検証
    private long boxOffice;

    public MovieUpdateForm(String name, LocalDate releaseDate, String directorName, long boxOffice) {
        this.name = name;
        this.releaseDate = releaseDate;
        this.directorName = directorName;
        this.boxOffice = boxOffice;
    }

    public Movie convertToMovie() {
        Movie movie = new Movie(this.name, this.releaseDate, this.directorName, this.boxOffice);
        return movie;
    }

    public String getName() {
        return name;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public String getDirectorName() {
        return directorName;
    }

    public long getBoxOffice() {
        return boxOffice;
    }
}
