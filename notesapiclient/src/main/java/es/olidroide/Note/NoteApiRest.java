package es.olidroide.Note;

import java.util.Map;
import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;
import retrofit.http.QueryMap;

public interface NoteApiRest {
    @GET("notes/") Call<NotesDto> getNotes(@QueryMap Map<String, Object> notesFilter);

    @GET("notes/{id}") Call<NotesDto> getNote(@Path("id") String characterId);

    @POST("notes/") Call<NoteDto> saveNote(@Body NoteDto noteDto);
}
