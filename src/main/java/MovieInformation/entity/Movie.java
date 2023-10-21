package MovieInformation.entity;

import java.sql.Date;

public class Movie {
    private int id;
    private String name;
    private Date releaseDate;
    private String directorName;
    private long boxOffice;

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
