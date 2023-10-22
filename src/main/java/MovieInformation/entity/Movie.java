package MovieInformation.entity;

import java.sql.Date;

public class Movie {
    private int id;
    private String name;
    private Date releaseDate;
    private String directorName;
    private long boxOffice;

    public Movie(Object object, String name, Date releaseDate, String directorName, long boxOffice) {
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
