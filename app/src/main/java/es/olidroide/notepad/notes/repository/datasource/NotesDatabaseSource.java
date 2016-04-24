package es.olidroide.notepad.notes.repository.datasource;

import android.util.ArrayMap;
import com.karumi.rosie.repository.PaginatedCollection;
import com.karumi.rosie.repository.datasource.EmptyPaginatedReadableDataSource;
import com.karumi.rosie.repository.datasource.paginated.Page;
import com.karumi.rosie.repository.datasource.paginated.PaginatedReadableDataSource;
import es.olidroide.notepad.notes.domain.Note;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Map;
import javax.inject.Inject;

public class NotesDatabaseSource extends EmptyPaginatedReadableDataSource<String, Note>
    implements PaginatedReadableDataSource<String, Note> {

    private final Map<String, Note> notes;

    @Inject public NotesDatabaseSource() {
        notes = getListNotes();
    }

    private Map<String, Note> getListNotes() {
        Map<String, Note> notes = new ArrayMap();

        Note.Builder noteBuilder = new Note.Builder();
        noteBuilder.setKey("1").setName("1").setNote("1");
        notes.put("1", noteBuilder.build());

        noteBuilder = new Note.Builder();
        noteBuilder.setKey("2").setName("2").setNote("2");
        notes.put("2", noteBuilder.build());

        return notes;
    }

    @Override public Note getByKey(String key) throws Exception {

        return notes.get(key);
    }

    @Override public PaginatedCollection<Note> getPage(Page page) throws Exception {
        int offset = page.getOffset();
        int limit = page.getLimit();

        Collection<Note> charactersToReturn = new LinkedList<>();

        if (offset < notes.size()) {
            for (int i = offset; i - offset < limit && i < notes.size(); i++) {
                charactersToReturn.add((Note) notes.values().toArray()[i]);
            }
        }

        PaginatedCollection<Note> charactersPage = new PaginatedCollection<>(charactersToReturn);
        charactersPage.setPage(page);
        charactersPage.setHasMore(offset + charactersToReturn.size() < notes.size());
        return charactersPage;
    }
}
