package es.olidroide.notepad.notes.repository;

import com.karumi.rosie.repository.datasource.paginated.PaginatedReadableDataSource;
import es.olidroide.notepad.notes.domain.Note;
import es.olidroide.notepad.notes.repository.datasource.NotesDatabaseSource;
import javax.inject.Inject;

public class NotesDataSourceFactory {

    @Inject NotesDataSourceFactory() {
    }

    PaginatedReadableDataSource<String, Note> createDataSource() {
        return new NotesDatabaseSource();
    }
}
