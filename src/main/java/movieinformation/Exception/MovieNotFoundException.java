package movieinformation.Exception;

public class MovieNotFoundException extends RuntimeException {
    private String massage;

    public MovieNotFoundException(String massage) {
        super(massage);
    }
}
