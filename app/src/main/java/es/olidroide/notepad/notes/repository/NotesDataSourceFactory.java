package es.olidroide.notepad.notes.repository;

import com.karumi.rosie.repository.datasource.paginated.PaginatedReadableDataSource;
import es.olidroide.Note.NoteApiClient;
import es.olidroide.NotesApiConfig;
import es.olidroide.notepad.notes.domain.Note;
import es.olidroide.notepad.notes.repository.datasource.NotesApiSource;
import javax.inject.Inject;

public class NotesDataSourceFactory {

    @Inject NotesDataSourceFactory() {
    }

    PaginatedReadableDataSource<String, Note> createDataSource() {
        NotesApiConfig notesApiConfig = NotesApiConfig.with();
        NoteApiClient noteApiClient = new NoteApiClient(notesApiConfig);


        //Or use new NotesDatabaseSource();

        return new NotesApiSource(noteApiClient);
    }
}
