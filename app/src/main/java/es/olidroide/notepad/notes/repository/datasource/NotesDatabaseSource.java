package es.olidroide.notepad.notes.repository.datasource;

import android.content.Context;
import com.karumi.rosie.repository.PaginatedCollection;
import com.karumi.rosie.repository.datasource.EmptyReadableDataSource;
import com.karumi.rosie.repository.datasource.paginated.Page;
import com.karumi.rosie.repository.datasource.paginated.PaginatedReadableDataSource;
import es.olidroide.notepad.notes.domain.Note;
import es.olidroide.notesdatabaseclient.Note.NoteDatabaseClient;
import es.olidroide.notesdatabaseclient.Note.NoteRealm;
import es.olidroide.notesdatabaseclient.Note.NotesRealm;
import java.util.Collection;
import java.util.LinkedList;
import javax.inject.Inject;

public class NotesDatabaseSource extends EmptyReadableDataSource<String, Note>
    implements PaginatedReadableDataSource<String, Note>
    //, PaginatedWriteableDataSource<String, Note>
{

    private final NoteDatabaseClient notesDatabaseClient;

    @Inject public NotesDatabaseSource(NoteDatabaseClient notesDatabaseClient) {
        this.notesDatabaseClient = notesDatabaseClient;
    }

    @Override public Note getByKey(String key) throws Exception {
        NoteRealm noteRealm = notesDatabaseClient.getNote(key);
        final Note note = new NoteToNoteRealmMapper().reverseMap(noteRealm);

        return note;
    }

    @Override public PaginatedCollection<Note> getPage(Page page) throws Exception {
        int offset = page.getOffset();
        int limit = page.getLimit();

        NotesRealm notesRealm = notesDatabaseClient.getAll(offset, limit);

        if (notesRealm == null) {
            return null;
        }

        Collection<Note> notesToReturn = new LinkedList<>();

        if (offset < notesRealm.getNotes().size()) {
            for (int i = offset; i - offset < limit && i < notesRealm.getNotes().size(); i++) {
                notesToReturn.add(new NoteToNoteRealmMapper().reverseMap(notesRealm.getNotes().get(i)));
            }
        }

        PaginatedCollection<Note> notesPage = new PaginatedCollection<>(notesToReturn);
        notesPage.setPage(page);
        notesPage.setHasMore(offset + notesToReturn.size() < notesRealm.getNotes().size());
        return notesPage;
    }
}
