package MovieInformation.Form;

import MovieInformation.entity.Movie;

import java.sql.Date;

public class MovieUpdateForm {
    private String name;
    private Date releaseDate;
    private String directorName;
    private long boxOffice;

    public MovieUpdateForm(String name, Date releaseDate, String directorName, long boxOffice) {
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
