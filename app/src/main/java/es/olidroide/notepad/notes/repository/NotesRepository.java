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

        PaginatedReadableDataSource<String, Note> noteApiDataSource = notesDataSourceFactory.createApiDataSource();
        PaginatedReadableDataSource<String, Note> noteDatabaseDataSource =
            notesDataSourceFactory.createDatabaseDataSource();

        addReadableDataSources(noteApiDataSource, noteDatabaseDataSource);
        addPaginatedReadableDataSources(noteApiDataSource, noteDatabaseDataSource);

        addWriteableDataSources(notesDataSourceFactory.createApiWritableDataSource(),
            notesDataSourceFactory.createWritableDatabaseDataSource());

        //addWriteableDataSources(noteDataSource);
    }

    //public void addDataBaseSourceContext(Context context) {
    //    PaginatedReadableDataSource<String, Note> noteDatabaseDataSource =
    //        notesDataSourceFactory.createDatabaseDataSource(context);
    //
    //    addReadableDataSources(noteDatabaseDataSource);
    //    addPaginatedReadableDataSources(noteDatabaseDataSource);
    //}
}
