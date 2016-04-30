package es.olidroide;


public class NotesApiException extends Exception{
    private final int httpCode;
    private final String notesApiCode;

    public NotesApiException(int httpCode, String notesApiCode, String description, Throwable cause) {
        super(description, cause);
        this.httpCode = httpCode;
        this.notesApiCode = notesApiCode;
    }

    public NotesApiException(String message, Throwable cause) {
        this(-1, "", message, cause);
    }

    public int getHttpCode() {
        return httpCode;
    }

    public String getNotesApiCode() {
        return notesApiCode;
    }
}
