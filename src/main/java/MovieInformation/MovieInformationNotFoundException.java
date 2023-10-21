package MovieInformation;

public class MovieInformationNotFoundException extends RuntimeException {
    private String massage;

    public MovieInformationNotFoundException(String massage) {
        super(massage);
    }
}
