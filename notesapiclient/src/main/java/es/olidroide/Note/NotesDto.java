package es.olidroide.Note;

import es.olidroide.PaginationResponse;
import java.util.List;

public class NotesDto extends PaginationResponse<NoteDto> {

    public List<NoteDto> getNotes() {
        return getResults();
    }
}
