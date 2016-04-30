package es.olidroide.notepad.notes.repository.datasource;

import android.util.ArrayMap;
import com.karumi.rosie.repository.PaginatedCollection;
import com.karumi.rosie.repository.datasource.EmptyReadableDataSource;
import com.karumi.rosie.repository.datasource.paginated.Page;
import com.karumi.rosie.repository.datasource.paginated.PaginatedReadableDataSource;
import es.olidroide.notepad.notes.domain.Note;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Map;
import javax.inject.Inject;

public class NotesDatabaseSource extends EmptyReadableDataSource<String, Note>
    implements PaginatedReadableDataSource<String, Note>
    //, PaginatedWriteableDataSource<String, Note>
{

    private final Map<String, Note> notes;

    @Inject public NotesDatabaseSource() {
        notes = getListNotes();
    }

    private Map<String, Note> getListNotes() {
        Map<String, Note> notes = new ArrayMap();

        for (int i = 0; i < 100; i++) {
            Note.Builder noteBuilder = new Note.Builder();
            String value = String.valueOf(i + 1);
            noteBuilder.setKey(value).setName(value).setNote(value);
            notes.put(value, noteBuilder.build());
        }

        return notes;
    }

    @Override public Note getByKey(String key) throws Exception {
        return notes.get(key);
    }

    @Override public Collection<Note> getAll() throws Exception {
        return notes.values();
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

    //@Override public Note addOrUpdate(Note note)   {
    //    return notes.put(note.getKey(), note);
    //}
    //
    //@Override public Collection<Note> addOrUpdateAll(Collection<Note> values)   {
    //    for (Note value : values) {
    //        notes.put(value.getKey(), value);
    //    }
    //
    //    return notes.values();
    //}
    //
    //@Override public void deleteByKey(String key)   {
    //    notes.remove(key);
    //}
    //
    //@Override public PaginatedCollection<Note> addOrUpdatePage(Page page, Collection<Note> values, boolean hasMore)
    //    throws Exception {
    //
    //    addOrUpdateAll(values);
    //
    //    return getPage(page);
    //}
    //
    //@Override public void deleteAll()   {
    //    notes.clear();
    //}
}
