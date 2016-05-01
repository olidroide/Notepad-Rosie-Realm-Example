package es.olidroide.notepad.notes.repository;

import com.karumi.rosie.repository.datasource.WriteableDataSource;
import com.karumi.rosie.repository.datasource.paginated.PaginatedReadableDataSource;
import com.karumi.rosie.repository.datasource.paginated.PaginatedWriteableDataSource;
import es.olidroide.Note.NoteApiClient;
import es.olidroide.NotesApiConfig;
import es.olidroide.notepad.notes.domain.Note;
import es.olidroide.notepad.notes.repository.datasource.NotesApiSource;
import es.olidroide.notepad.notes.repository.datasource.NotesDatabaseSource;
import es.olidroide.notepad.notes.repository.datasource.NotesWritableDatabaseSource;
import javax.inject.Inject;

public class NotesDataSourceFactory {

    private final NotesDatabaseSource notesDataBaseSource;
    private final NotesWritableDatabaseSource notesWritableDatabaseSource;

    @Inject NotesDataSourceFactory(NotesDatabaseSource notesDatabaseSource,
        NotesWritableDatabaseSource notesWritableDatabaseSource) {
        this.notesDataBaseSource = notesDatabaseSource;
        this.notesWritableDatabaseSource = notesWritableDatabaseSource;
    }

    PaginatedReadableDataSource<String, Note> createApiDataSource() {
        NotesApiConfig notesApiConfig = NotesApiConfig.with();
        NoteApiClient noteApiClient = new NoteApiClient(notesApiConfig);

        return new NotesApiSource(noteApiClient);
    }

    PaginatedReadableDataSource<String, Note> createDatabaseDataSource() {
        return notesDataBaseSource;
    }

    WriteableDataSource<String, Note> createWritableDatabaseDataSource() {
        return notesWritableDatabaseSource;
    }
}
