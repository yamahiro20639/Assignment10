package movieinformation.entity;

import java.time.LocalDate;
import java.sql.Date;

public class Movie {
    private int id;
    private String name;
    private Date sqlDate;
    private String directorName;
    private long boxOffice;

    //MovieRegistrationFormのconvertToMovie()
    public Movie(Object object, String name, Date sqlDate, String directorName, long boxOffice) {
        this.id = id;
        this.name = name;
        this.sqlDate = sqlDate;
        this.directorName = directorName;
        this.boxOffice = boxOffice;
    }

    //MovieUpdateFormのconvertToMovie()
    public Movie(String name, Date sqlDate, String directorName, long boxOffice) {
        this.name = name;
        this.sqlDate = sqlDate;
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
        return sqlDate;
    }

    public String getDirectorName() {
        return directorName;
    }

    public long getBoxOffice() {
        return boxOffice;
    }
}
