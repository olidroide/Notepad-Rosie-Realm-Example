package es.olidroide.notepad.notes.repository;

import com.karumi.rosie.repository.PaginatedRosieRepository;
import com.karumi.rosie.repository.datasource.paginated.PaginatedCacheDataSource;
import com.karumi.rosie.repository.datasource.paginated.PaginatedReadableDataSource;
import es.olidroide.notepad.notes.domain.Note;
import javax.inject.Inject;

public class NotesRepository extends PaginatedRosieRepository<String, Note> {

    @Inject NotesRepository(NotesDataSourceFactory notesDataSourceFactory,
        PaginatedCacheDataSource<String, Note> inMemoryPaginatedCache) {

        addCacheDataSources(inMemoryPaginatedCache);
        addPaginatedCacheDataSources(inMemoryPaginatedCache);

        PaginatedReadableDataSource<String, Note> noteDataSource = notesDataSourceFactory.createDataSource();
        addReadableDataSources(noteDataSource);
        addPaginatedReadableDataSources(noteDataSource);
    }
}
