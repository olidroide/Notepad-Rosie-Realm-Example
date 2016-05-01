package es.olidroide.notepad.notes.repository.datasource;

import com.karumi.rosie.repository.PaginatedCollection;
import com.karumi.rosie.repository.datasource.EmptyReadableDataSource;
import com.karumi.rosie.repository.datasource.paginated.Page;
import com.karumi.rosie.repository.datasource.paginated.PaginatedReadableDataSource;
import es.olidroide.Note.NoteApiClient;
import es.olidroide.Note.NoteDto;
import es.olidroide.Note.NotesDto;
import es.olidroide.notepad.notes.domain.Note;
import java.util.Collection;
import java.util.LinkedList;
import javax.inject.Inject;

public class NotesApiSource extends EmptyReadableDataSource<String, Note>
    implements PaginatedReadableDataSource<String, Note> {

    private final NoteApiClient noteApiClient;

    @Inject public NotesApiSource(NoteApiClient noteApiClient) {
        this.noteApiClient = noteApiClient;
    }

    @Override public Note getByKey(String key) throws Exception {
        NoteDto noteDto = noteApiClient.getNote(key);
        final Note note = new NoteToNoteDtoMapper().reverseMap(noteDto);

        return note;
    }

    @Override public PaginatedCollection<Note> getPage(Page page) throws Exception {
        int offset = page.getOffset();
        int limit = page.getLimit();

        Collection<Note> notesToReturn = new LinkedList<>();

        final NotesDto notesDto = noteApiClient.getAll(offset, limit);
        if (notesDto == null) {
            return null;
        }

        if (offset < notesDto.getCount()) {
            for (int i = offset; i - offset < limit && i < notesDto.getCount(); i++) {
                notesToReturn.add(new NoteToNoteDtoMapper().reverseMap(notesDto.getNotes().get(i)));
            }
        }

        PaginatedCollection<Note> notesPage = new PaginatedCollection<>(notesToReturn);
        notesPage.setPage(page);
        notesPage.setHasMore(offset + notesToReturn.size() < notesDto.getTotal());
        return notesPage;
    }
}
