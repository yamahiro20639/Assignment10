package movieinformation.entity;

import java.time.LocalDate;
import java.sql.Date;

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
    public Movie(String name, LocalDate releaseDate, String directorName, long boxOffice) {
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
}
