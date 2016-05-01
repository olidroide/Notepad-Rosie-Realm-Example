package es.olidroide.notepad.notes;

import android.content.Context;
import com.karumi.rosie.repository.datasource.paginated.InMemoryPaginatedCacheDataSource;
import com.karumi.rosie.repository.datasource.paginated.PaginatedCacheDataSource;
import com.karumi.rosie.time.TimeProvider;
import dagger.Module;
import dagger.Provides;
import es.olidroide.notepad.notes.domain.Note;
import es.olidroide.notepad.notes.repository.datasource.NotesDatabaseSource;
import es.olidroide.notepad.notes.view.NotesActivity;
import es.olidroide.notepad.notes.view.NotesFragment;
import javax.inject.Singleton;

import static java.util.concurrent.TimeUnit.MINUTES;

@Module(
    library = true,
    complete = false,
    injects = {
        NotesActivity.class, NotesFragment.class
    })
public class NotesModule {

    private static final long NOTES_IN_MEMORY_CACHE_TTL = MINUTES.toMillis(5);
    private final Context context;

    public NotesModule(Context context) {
        this.context = context;
    }

    @Provides @Singleton public PaginatedCacheDataSource<String, Note> provideNotesPageInMemoryCache() {
        return new InMemoryPaginatedCacheDataSource<>(new TimeProvider(), NOTES_IN_MEMORY_CACHE_TTL);
    }

    //Required for NotesDatabaseClient
    @Provides public Context providesContext() {
        return context;
    }

    @Provides public NotesDatabaseSource providesNotesDataBaseSource() {
        return new NotesDatabaseSource(context);
    }
}