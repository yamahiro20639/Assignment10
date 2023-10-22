package MovieInformation.Form;

import jakarta.validation.constraints.*;

import java.sql.Date;

public class MovieRegistrationForm {
    private int id;
    @NotEmpty //文字列やコレクションなどの文字列が空でないことを検証
    private String name;
    @NotNull //空（null）であるかどうかを検証
    private Date releaseDate;
    @NotEmpty //文字列やコレクションなどの文字列が空でないことを検証
    private String directorName;
    @PositiveOrZero //数値が正か 0 であることを検証
    private long boxOffice;

    public MovieRegistrationForm(int id, String name, Date releaseDate, String directorName, long boxOffice) {
        this.id = id;
        this.name = name;
        this.releaseDate = releaseDate;
        this.directorName = directorName;
        this.boxOffice = boxOffice;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public String getDirectorName() {
        return directorName;
    }

    public long getBoxOffice() {
        return boxOffice;
    }
}
