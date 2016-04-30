package es.olidroide.Note;

import es.olidroide.NotesApiClient;
import es.olidroide.NotesApiConfig;
import es.olidroide.NotesApiException;
import java.util.Map;
import retrofit.Call;

public class NoteApiClient extends NotesApiClient {
    public NoteApiClient(NotesApiConfig notesApiConfig) {
        super(notesApiConfig);
    }

    public NoteDto getNote(String noteId) throws NotesApiException {
        if (noteId == null || noteId.isEmpty()) {
            throw new IllegalArgumentException("The NoteId must not be null or empty");
        }

        NoteApiRest api = getApi(NoteApiRest.class);

        Call<NotesDto> call = api.getNote(noteId);
        NotesDto notesDto = execute(call);

        if (notesDto != null && notesDto.getCount() > 0) {
            NoteDto noteDto = notesDto.getNotes().get(0);
            //NotesResponse<NoteDto> noteResponse = new NotesResponse<>(noteDto);
            return noteDto;
        } else {
            throw new NotesApiException("Note not found", null);
        }
    }

    public NotesDto getAll(int offset, int limit) throws NotesApiException {
        NotesQuery query = NotesQuery.Builder.create().withOffset(offset).withLimit(limit).build();
        return getAll(query);
    }

    public NotesDto getAll(NotesQuery charactersQuery) throws NotesApiException {
        NoteApiRest api = getApi(NoteApiRest.class);

        Map<String, Object> queryAsMap = charactersQuery.toMap();
        Call<NotesDto> call = api.getNotes(queryAsMap);
        return execute(call);
    }
}
