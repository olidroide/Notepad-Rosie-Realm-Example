package es.olidroide.notepad.notes.repository;

import android.content.Context;
import com.karumi.rosie.repository.datasource.paginated.PaginatedReadableDataSource;
import es.olidroide.Note.NoteApiClient;
import es.olidroide.NotesApiConfig;
import es.olidroide.notepad.notes.domain.Note;
import es.olidroide.notepad.notes.repository.datasource.NotesApiSource;
import es.olidroide.notepad.notes.repository.datasource.NotesDatabaseSource;
import es.olidroide.notesdatabaseclient.Note.NoteDatabaseClient;
import es.olidroide.notesdatabaseclient.NotesDatabaseConfig;
import javax.inject.Inject;

public class NotesDataSourceFactory {

    private final Context context;

    @Inject NotesDataSourceFactory(Context context) {
        this.context = context;
    }

    PaginatedReadableDataSource<String, Note> createApiDataSource() {
        NotesApiConfig notesApiConfig = NotesApiConfig.with();
        NoteApiClient noteApiClient = new NoteApiClient(notesApiConfig);

        return new NotesApiSource(noteApiClient);
    }

    PaginatedReadableDataSource<String, Note> createDatabaseDataSource() {
        NoteDatabaseClient noteDatabaseClient = new NoteDatabaseClient(NotesDatabaseConfig.with(context));
        //NoteDatabaseClient noteDatabaseClient = new NoteDatabaseClient(NotesDatabaseConfig.get());
        return new NotesDatabaseSource(noteDatabaseClient);
    }
}
