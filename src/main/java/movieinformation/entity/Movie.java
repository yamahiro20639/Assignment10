package movieinformation.entity;

import java.time.LocalDate;
import java.sql.Date;
import java.util.Objects;

public class Movie {
    private int id;
    private String name;
    private LocalDate releaseDate;
    private String directorName;
    private long boxOffice;

    //MovieRegistrationFormのconvertToMovie()
    public Movie(Object object, String name, LocalDate releaseDate, String directorName, long boxOffice) {
        this.id = id;
        this.name = name;
        this.releaseDate = releaseDate;
        this.directorName = directorName;
        this.boxOffice = boxOffice;
    }

    //MovieUpdateFormのconvertToMovie()
    public Movie(int id, String name, LocalDate releaseDate, String directorName, long boxOffice) {
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

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public String getDirectorName() {
        return directorName;
    }

    public long getBoxOffice() {
        return boxOffice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Movie movie = (Movie) o;
        return id == movie.id && Objects.equals(name, movie.name) && Objects.equals(releaseDate, movie.releaseDate) && Objects.equals(directorName, movie.directorName) && Objects.equals(boxOffice, movie.boxOffice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, releaseDate, directorName, boxOffice);
    }
}

