package es.olidroide.notepad.notes.repository;

import com.karumi.rosie.repository.datasource.paginated.PaginatedReadableDataSource;
import es.olidroide.Note.NoteApiClient;
import es.olidroide.NotesApiConfig;
import es.olidroide.notepad.notes.domain.Note;
import es.olidroide.notepad.notes.repository.datasource.NotesApiSource;
import es.olidroide.notepad.notes.repository.datasource.NotesDatabaseSource;
import javax.inject.Inject;

public class NotesDataSourceFactory {

    private final NotesDatabaseSource notesDataBaseSource;

    @Inject NotesDataSourceFactory(NotesDatabaseSource notesDatabaseSource) {
        this.notesDataBaseSource = notesDatabaseSource;
    }

    PaginatedReadableDataSource<String, Note> createApiDataSource() {
        NotesApiConfig notesApiConfig = NotesApiConfig.with();
        NoteApiClient noteApiClient = new NoteApiClient(notesApiConfig);

        return new NotesApiSource(noteApiClient);
    }

    PaginatedReadableDataSource<String, Note> createDatabaseDataSource() {
        return notesDataBaseSource;
    }
}
